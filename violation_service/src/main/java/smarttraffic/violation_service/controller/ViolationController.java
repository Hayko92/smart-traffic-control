package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.Capture;
import smarttraffic.violation_service.entity.SpeedViolation;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.ViolationCounter;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/violationService")
public class ViolationController {


    @Autowired
    private ViolationService violationService;

    @PostMapping("/speed")
    public void createSpeedViolation(@RequestBody Map<String, Integer> info) {
        RestTemplate restTemplate = new RestTemplate();
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        Capture capturePrev = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idPrev, Capture.class);
        Capture captureCurrent = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idCurr, Capture.class);
        SpeedViolation violation = new SpeedViolation();
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        violation.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setNumber(capturePrev.getPlateNumber());
        violation.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violation.setPhotoUrl2(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(captureCurrent.getPlace());
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email",violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms",violation);
        violationService.save(violation);
    }

}
