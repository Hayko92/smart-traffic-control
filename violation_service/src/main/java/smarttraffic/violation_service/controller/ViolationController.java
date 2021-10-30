package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.Owner;
import smarttraffic.violation_service.entity.Vehicle;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.model.Capture;
import smarttraffic.violation_service.model.SpeedViolation;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.InfoExtractor;
import smarttraffic.violation_service.util.ViolationCounter;

import javax.transaction.Transactional;
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
    public void createSpeedViolation(@RequestBody Map<String, Integer> info) {
        RestTemplate restTemplate = new RestTemplate();
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        Capture capturePrev = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idPrev, Capture.class);
        Capture captureCurrent = restTemplate.getForObject(detectorImitationUrl + "/capture/" + idCurr, Capture.class);
        Vehicle vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + captureCurrent.getPlateNumber(), Vehicle.class);
        Owner owner = vehicle.getOwner();
        Violation violation = createSpeedViolation(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner);
        sendNotifications(violation);
        violationService.save(violation);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, Capture> body) {
        RestTemplate restTemplate = new RestTemplate();
        Violation violation = checkViolationType(body, restTemplate);
        violationService.save(violation);
        sendNotifications(violation);
    }

    private Violation checkViolationType(Map<String, Capture> body, RestTemplate restTemplate) {
        String type;
        Violation violation;
        if (body.containsKey("TECH")) {
            type = "TECH";
        } else {
            type = "INS";
        }
        Capture capture = body.get(type);
        Vehicle vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + capture.getPlateNumber(), Vehicle.class);
        violation = createViolation(type, capture, vehicle);
        return violation;
    }

    private Violation createViolation(String type, Capture capture, Vehicle vehicle) {
        Violation violation = new Violation(type);
        violation.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setPhotoUrl1(capture.getPhotoUrl());
        violation.setPlace(capture.getPlace());
        violation.setNumber(capture.getPlateNumber());
        violation.setPrice(5000);
        violation.setPlace(capture.getPlace());
        violation.setOwner(vehicle.getOwner());
        violation.setVehicle(vehicle);
        checkOwnerPoints(violation.getOwner());
        return violation;
    }

    private Violation createSpeedViolation(int price, Capture capturePrev, Capture captureCurrent, Vehicle vehicle) {
        Violation violation = new Violation();
        violation.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setPlace(captureCurrent.getPlace());
        violation.setNumber(captureCurrent.getPlateNumber());
        violation.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violation.setPhotoUrl2(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(captureCurrent.getPlace());
        violation.setOwner(vehicle.getOwner());
        violation.setVehicle(vehicle);
        return violation;
    }

    private void checkOwnerPoints(Owner owner) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
        if (owner.getRedusedPoint() == 0)
            restTemplate.postForLocation(notificationServiceUrl + "/patrol/owner", ownerID);
    }

    private void sendNotifications(Violation violation) {
        RestTemplate restTemplate = new RestTemplate();
        // violationService.save(violation);
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violation);
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