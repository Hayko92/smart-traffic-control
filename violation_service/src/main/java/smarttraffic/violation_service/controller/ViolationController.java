package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.dto.CaptureDTO;
import smarttraffic.violation_service.dto.OwnerDTO;
import smarttraffic.violation_service.dto.VehicleDTO;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.InfoExtractor;
import smarttraffic.violation_service.util.JwtTokenUtil;
import smarttraffic.violation_service.util.ViolationCounter;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/violation-service")
public class ViolationController {

    @Value("${cameraImitationServise}")
    private String detectorImitationUrl;

    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;

    @Value("${vehicleService}")
    private String vehicleServiceUrl;

    @Value("${notificationService}")
    private String notificationServiceUrl;
    @Autowired
    private ViolationService violationService;

    @PostMapping("/speed")
    public void createSpeedViolationDTO(@RequestBody Map<String, Integer> info, @RequestHeader(name = "AUTHORIZATION") String token) {
        RestTemplate restTemplate = new RestTemplate();
        OwnerDTO owner = null;
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        ResponseEntity<CaptureDTO> capturePrevResp = restTemplate.exchange(detectorAnalyzerUrl + "/capture/" + idPrev, HttpMethod.GET, httpEntity, CaptureDTO.class);
        ResponseEntity<CaptureDTO> captureCurrentResp = restTemplate.exchange(detectorAnalyzerUrl + "/capture/" + idCurr, HttpMethod.GET, httpEntity, CaptureDTO.class);
        CaptureDTO capturePrev = capturePrevResp.getBody();
        CaptureDTO captureCurrent = captureCurrentResp.getBody();
        ResponseEntity<VehicleDTO> vehicleResp = restTemplate.exchange(vehicleServiceUrl + "/" + captureCurrent.getPlateNumber(), HttpMethod.GET, httpEntity, VehicleDTO.class);
        VehicleDTO vehicle = vehicleResp.getBody();
        if (vehicle != null) owner = vehicle.getOwner();
        ViolationDTO violationDTO = createSpeedViolation(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner, token);
        long id = violationService.save(violationDTO);

        violationDTO.setId(id);
        sendNotifications(violationDTO, token);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, CaptureDTO> body, @RequestHeader(name = "AUTHORIZATION") String token) {
        RestTemplate restTemplate = new RestTemplate();
        CaptureDTO capture = getCapture(body);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<VehicleDTO> vehicleResp = restTemplate.exchange(vehicleServiceUrl + "/" + capture.getPlateNumber(), HttpMethod.GET, httpEntity, VehicleDTO.class);
        VehicleDTO vehicle = vehicleResp.getBody();
        ViolationDTO violationDTO = checkViolationTypeAndCreate(body, vehicle);
        checkOwnerPoints(vehicle.getOwner(), token);
        long id = violationService.save(violationDTO);
        violationDTO.setId(id);
        sendNotifications(violationDTO, token);
    }

    private ViolationDTO checkViolationTypeAndCreate(Map<String, CaptureDTO> body, VehicleDTO vehicle) {
        CaptureDTO capture;
        ViolationDTO violationDTO;
        if (body.containsKey("TECH")) {
            capture = body.get("TECH");
            violationDTO = createViolationDTO("TECH", capture, vehicle);
        } else {
            capture = body.get("INS");
            violationDTO = createViolationDTO("INS", capture, vehicle);
        }
        return violationDTO;
    }

    private CaptureDTO getCapture(Map<String, CaptureDTO> info) {
        CaptureDTO capture;
        if (info.containsKey("TECH")) {
            capture = info.get("TECH");
        } else {
            capture = info.get("INS");
        }
        return capture;
    }

    private ViolationDTO createViolationDTO(String type, CaptureDTO capture, VehicleDTO vehicle) {
        ViolationDTO violationDTO = new ViolationDTO(type);
        violationDTO.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.DAYS));
        violationDTO.setPhotoUrl1(capture.getPhotoUrl());
        violationDTO.setPrice(5000);
        violationDTO.setPlace(capture.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        return violationDTO;
    }

    private ViolationDTO createSpeedViolation(int price, CaptureDTO capturePrev, CaptureDTO captureCurrent, VehicleDTO vehicle) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setType("SPEED");
        violationDTO.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.MILLIS));
        violationDTO.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violationDTO.setPhotoUrl2(capturePrev.getPhotoUrl());
        violationDTO.setPrice(price);
        violationDTO.setPlace(captureCurrent.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        violationDTO.setNumber(captureCurrent.getPlateNumber());
        return violationDTO;
    }

    private void checkOwnerPoints(OwnerDTO owner, String token) {
        if (owner != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
            HttpEntity httpEntity = new HttpEntity(headers);
            if (owner.getRedusedPoint() < 0)
                restTemplate.exchange(notificationServiceUrl + "/patrol/owner/" + owner.getId(), HttpMethod.GET, httpEntity, Void.class);
            else {
                HttpEntity<OwnerDTO> httpEntityWithOwner = new HttpEntity<>(owner, headers);
                restTemplate.exchange(vehicleServiceUrl + "/owner/" + owner.getId(), HttpMethod.PUT, httpEntityWithOwner, Void.class);
            }
        }

    }

    private void sendNotifications(ViolationDTO violationDTO, String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violationDTO);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(speedViolationInfo, headers);
        restTemplate.exchange(notificationServiceUrl + "/email", HttpMethod.POST, httpEntity, Void.class);
        restTemplate.exchange(notificationServiceUrl + "/sms", HttpMethod.POST, httpEntity, Void.class);
    }

    @GetMapping("/platenumber/{vehiclenumber}")
    public List<ViolationDTO> sendViolationsByplatenumber(@PathVariable String vehiclenumber) {
        return violationService.getAllByNumber(vehiclenumber);
    }

    @GetMapping("/ownerID/{ownerID}")
    public List<ViolationDTO> getAllViolationsByOwnerId(@RequestBody Long ownerID) {
        return violationService.getAllByOwnerID(ownerID);
    }
}