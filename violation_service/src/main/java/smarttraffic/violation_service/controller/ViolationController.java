package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Owner;
import smarttraffic.violation_service.entity.Vehicle;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.model.Capture;
import smarttraffic.violation_service.model.InsuranceViolation;
import smarttraffic.violation_service.model.SpeedViolation;
import smarttraffic.violation_service.model.TechinspectionViolation;
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
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        Capture capturePrev = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idPrev, Capture.class);
        Capture captureCurrent = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idCurr, Capture.class);
        Vehicle vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + captureCurrent.getPlateNumber(), Vehicle.class);
        Owner owner = vehicle.getOwner();
        ViolationDTO violationDTO = createSpeedViolation(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner);
        sendNotifications(violationDTO);
        Violation violation = null;
        try {
            violation = (Violation) violationDTO.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        violationService.save(violation);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, Capture> body) {
        RestTemplate restTemplate = new RestTemplate();
        Capture capture = null;
        Vehicle vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + capture.getPlateNumber(), Vehicle.class);
        ViolationDTO violationDTO = null;
        violationDTO = checkViolationType(body, vehicle);
        checkOwnerPoints(vehicle.getOwner());
        sendNotifications(violationDTO);
        Violation violation = null;
        try {
            violation = (Violation) violationDTO.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        violationService.save(violation);
    }

    private ViolationDTO checkViolationType(Map<String, Capture> body, Vehicle vehicle) {
        Capture capture;
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

    private ViolationDTO createViolationDTO(String type, Capture capture, Vehicle vehicle) {
        ViolationDTO violationDTO = null;
        if (type.equals("TECH")) violationDTO = new TechinspectionViolation();
        else violationDTO = new InsuranceViolation();
        violationDTO.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.DAYS));
        violationDTO.setPhotoUrl1(capture.getPhotoUrl());
        violationDTO.setPrice(5000);
        violationDTO.setPlace(capture.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        return violationDTO;
    }

    private ViolationDTO createSpeedViolation(int price, Capture capturePrev, Capture captureCurrent, Vehicle vehicle) {
        SpeedViolation violationDTO = new SpeedViolation();
        violationDTO.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
        violationDTO.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violationDTO.setPhotoUrl2(capturePrev.getPhotoUrl());
        violationDTO.setPrice(price);
        violationDTO.setPlace(captureCurrent.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        return violationDTO;
    }

    private void checkOwnerPoints(Owner owner) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
        if (owner.getRedusedPoint() == 0)
            restTemplate.postForLocation(notificationServiceUrl + "/patrol/owner", ownerID);
    }

    private void sendNotifications(ViolationDTO violationDTO) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violationDTO);
        restTemplate.postForLocation(notificationServiceUrl + "/email", speedViolationInfo);
        restTemplate.postForLocation(notificationServiceUrl + "/sms", speedViolationInfo);
    }

    @GetMapping("/platenumber/{vehiclenumber}")
    public List<Violation> sendViolationsByplatenumber(@PathVariable String vehiclenumber) {
        return violationService.getAllByNumber(vehiclenumber);
    }

    @GetMapping("/ownerID/{ownerID}")
    public List<Violation> createViolation(@RequestBody Long ownerID) {
        return violationService.getAllByOwnerID(ownerID);
    }
}