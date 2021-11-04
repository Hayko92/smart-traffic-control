package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.dto.CaptureDTO;
import smarttraffic.violation_service.dto.OwnerDTO;
import smarttraffic.violation_service.dto.VehicleDTO;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.InfoExtractor;
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
    @Autowired
    private ViolationService violationService;

    @PostMapping("/speed")
    public void createSpeedViolationDTO(@RequestBody Map<String, Integer> info) {
        RestTemplate restTemplate = new RestTemplate();
        OwnerDTO owner = null;
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        CaptureDTO capturePrev = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idPrev, CaptureDTO.class);
        CaptureDTO captureCurrent = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idCurr, CaptureDTO.class);
        VehicleDTO vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + captureCurrent.getPlateNumber(), VehicleDTO.class);
        if (vehicle != null) owner = vehicle.getOwner();
        ViolationDTO violationDTO = createSpeedViolation(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner);
        sendNotifications(violationDTO);
        violationService.save(violationDTO);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, CaptureDTO> body) {
        RestTemplate restTemplate = new RestTemplate();
        CaptureDTO capture = null;
        VehicleDTO vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + capture.getPlateNumber(), VehicleDTO.class);
        ViolationDTO violationDTO = checkViolationTypeAndCreate(body, vehicle);
        checkOwnerPoints(vehicle.getOwner());
        sendNotifications(violationDTO);
        violationService.save(violationDTO);
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
        violationDTO.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
        violationDTO.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violationDTO.setPhotoUrl2(capturePrev.getPhotoUrl());
        violationDTO.setPrice(price);
        violationDTO.setPlace(captureCurrent.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        return violationDTO;
    }

    private void checkOwnerPoints(OwnerDTO owner) {
        if (owner != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
            if (owner.getRedusedPoint() < 0)
                restTemplate.postForLocation(notificationServiceUrl + "/patrol/owner", ownerID);
        }
    }

    private void sendNotifications(ViolationDTO violationDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violationDTO);
        restTemplate.postForLocation(notificationServiceUrl + "/email", speedViolationInfo);
        restTemplate.postForLocation(notificationServiceUrl + "/sms", speedViolationInfo);
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