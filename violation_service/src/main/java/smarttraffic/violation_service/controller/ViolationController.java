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
import smarttraffic.violation_service.entity.Owner;
import smarttraffic.violation_service.entity.Vehicle;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.model.Capture;
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
    @Value("${vehicleService}")
    private String vehicleServiceUrl;
    @Value("${notificationService}")
    private String notificationServiceUrl;
    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerServiceUrl;
    @Autowired
    private ViolationService violationService;

    @PostMapping("/speed")
    public void createSpeedViolation(@RequestBody Map<String, Integer> info, @RequestHeader(name = "AUTHORIZATION") String token) {
        RestTemplate restTemplate = new RestTemplate();
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<CaptureDTO> capturePrevResp = restTemplate.exchange(detectorAnalyzerServiceUrl + "/capture/" + idPrev, HttpMethod.GET, httpEntity, CaptureDTO.class);
        ResponseEntity<CaptureDTO> captureCurrentResp = restTemplate.exchange(detectorAnalyzerServiceUrl + "/capture/" + idCurr, HttpMethod.GET, httpEntity, CaptureDTO.class);
        ResponseEntity<Vehicle> vehicleEnt = restTemplate.exchange(vehicleServiceUrl + "/" + captureCurrentResp.getBody().getPlateNumber(), HttpMethod.GET, httpEntity, Vehicle.class);
        Owner owner = vehicleEnt.getBody().getOwner();
        Violation violation = createSpeedViolation(price, capturePrevResp.getBody(), captureCurrentResp.getBody(), vehicleEnt.getBody(),token);
        checkOwnerPoints(owner, token);
        violationService.save(violation);
        violationService.reduceOwnerPoints(owner,token);
        sendNotifications(violation, token);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, Capture> body, @RequestHeader(name = "AUTHORIZATION") String token) {
        RestTemplate restTemplate = new RestTemplate();
        Violation violation = checkViolationType(body, token);
        checkOwnerPoints(violation.getOwner(), token);
        violationService.save(violation);
        sendNotifications(violation, token);
    }

    private Violation checkViolationType(Map<String, Capture> body, String token) {
        RestTemplate restTemplate = new RestTemplate();
        String type;
        Violation violation;
        if (body.containsKey("TECH")) {
            type = "TECH";
        } else {
            type = "INS";
        }
        Capture capture = body.get(type);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Vehicle> response = restTemplate.exchange(vehicleServiceUrl + "/" + capture.getPlateNumber(), HttpMethod.GET, httpEntity, Vehicle.class);
        Vehicle vehicle = response.getBody();
        violation = createViolation(type, capture, vehicle,token);
        return violation;
    }

    private Violation createViolation(String type, Capture capture, Vehicle vehicle,String token) {
        Violation violation = new Violation(type);
        violation.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.MILLIS));
        violation.setPhotoUrl1(capture.getPhotoUrl());
        violation.setPlace(capture.getPlace());
        violation.setNumber(capture.getPlateNumber());
        violation.setPrice(5000);
        violation.setPlace(capture.getPlace());
        violation.setOwner(vehicle.getOwner());
        violation.setVehicle(vehicle);
        violationService.reduceOwnerPoints(vehicle.getOwner(),token);
        return violation;
    }

    private Violation createSpeedViolation(int price, CaptureDTO capturePrev, CaptureDTO captureCurrent, Vehicle vehicle,String token) {
        Violation violation = new Violation();
        violation.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.MILLIS));
        violation.setPlace(captureCurrent.getPlace());
        violation.setNumber(captureCurrent.getPlateNumber());
        violation.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violation.setPhotoUrl2(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(captureCurrent.getPlace());
        violation.setOwner(vehicle.getOwner());
        violation.setVehicle(vehicle);
        violation.setType("SPEED");
        violationService.reduceOwnerPoints(vehicle.getOwner(),token);
        return violation;
    }

    private void checkOwnerPoints(Owner owner, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity  httpEntity = new HttpEntity<>(headers);
        if (owner.getRedusedPoint() <1)
            restTemplate.exchange(notificationServiceUrl + "/patrol/owner/"+owner.getId(),HttpMethod.GET,httpEntity,Void.class);
    }

    private void sendNotifications(Violation violation, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violation);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(speedViolationInfo, headers);
        restTemplate.postForLocation(notificationServiceUrl + "/email", httpEntity);
        restTemplate.postForLocation(notificationServiceUrl + "/sms", httpEntity);
    }

    @GetMapping("/platenumber/{vehiclenumber}")
    public List<Violation> sendViolationsByplatenumber(@PathVariable String vehiclenumber) {
        return violationService.getAllByNumber(vehiclenumber);
    }

    @GetMapping("/ownerID/{ownerID}")
    public List<Violation> createViolation(@PathVariable Long ownerID) {
        return violationService.getAllByOwnerID(ownerID);
    }
}