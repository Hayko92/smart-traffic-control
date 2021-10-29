package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.InsuranceViolation;
import smarttraffic.violation_service.entity.SpeedViolation;
import smarttraffic.violation_service.entity.TechinspectionViolation;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.model.Capture;
import smarttraffic.violation_service.model.Owner;
import smarttraffic.violation_service.model.Vehicle;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.InfoExtractor;
import smarttraffic.violation_service.util.ViolationCounter;

import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/api/violationService")
public class ViolationController {

    @Autowired
    private ViolationService violationService;
//    private Capture capture;

    @PostMapping("/speed")
    public void createSpeedViolation(@RequestBody Map<String, Integer> info) {
        RestTemplate restTemplate = new RestTemplate();
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        Capture capturePrev = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idPrev, Capture.class);
        Capture captureCurrent = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idCurr, Capture.class);
        Vehicle vehicle = restTemplate.getForObject("http://127.0.0.1:8084/api/vehicle-service/" + captureCurrent.getPlateNumber(), Vehicle.class);
        Owner owner = vehicle.getOwner();
        Violation violation = createSpeedViolation(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner);
        sendNotifications(violation);
        violationService.save(violation);
    }


    @PostMapping
    public void createViolation(@RequestBody Map<String, Capture> body) {
        RestTemplate restTemplate = new RestTemplate();
        Capture capture = null;
        Vehicle vehicle = restTemplate.getForObject("http://127.0.0.1:8084/api/vehicle-service/" + capture.getPlateNumber(), Vehicle.class);
        Violation violation = null;
        violation = checkViolationType(body,vehicle);
        checkOwnerPoints(vehicle.getOwner());
        sendNotifications(violation);
        violationService.save(violation);
    }

    private Violation checkViolationType(Map<String, Capture> body, Vehicle vehicle) {
        Capture capture;
        Violation violation;
        if (body.containsKey("TECH")) {
            capture = body.get("TECH");
            violation = createViolation("TECH", capture, vehicle);
        } else {
            capture = body.get("INS");
            violation = createViolation("INS", capture, vehicle);
        }
        return violation;
    }

    private Violation createViolation(String type, Capture capture, Vehicle vehicle) {
        Violation violation = null;
        if (type.equals("TECH")) violation = new TechinspectionViolation();
        else violation = new InsuranceViolation();
        violation.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setPhotoUrl1(capture.getPhotoUrl());
        violation.setPrice(5000);
        violation.setPlace(capture.getPlace());
        violation.setOwner(vehicle.getOwner());
        violation.setVehicle(vehicle);
        return violation;
    }

    private Violation createSpeedViolation(int price, Capture capturePrev, Capture captureCurrent, Vehicle vehicle) {
        SpeedViolation violation = new SpeedViolation();
        violation.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
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
        if (owner.getReducedPoint() == 0)
            restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol/owner", ownerID);
    }

    private void sendNotifications(Violation violation) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email", speedViolationInfo);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms", speedViolationInfo);
    }
}