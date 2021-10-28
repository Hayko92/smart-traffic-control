package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.*;
import smarttraffic.violation_service.service.ViolationService;
import smarttraffic.violation_service.util.InfoExtractor;
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
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        Capture capturePrev = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idPrev, Capture.class);
        Capture captureCurrent = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" + idCurr, Capture.class);
        //todo add url handling in vehicle service (url must return owner of vehicle by vehicle number)
        Owner owner = restTemplate.getForObject("http://127.0.0.1:8084/api/vehicle-service/owner/" + captureCurrent.getPlateNumber(), Owner.class);
        SpeedViolation violation = new SpeedViolation();
        Vehicle vehicle = owner.getVehicleByPlateNUmber(captureCurrent.getPlateNumber());
        violation.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setNumber(capturePrev.getPlateNumber());
        violation.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violation.setPhotoUrl2(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(captureCurrent.getPlace());
        violation.setOwner(owner);
        violation.setVehicle(vehicle);
        //reducing owners points, sending notification to patrol if no points left for current owner
        HttpEntity<Long> ownerID =new HttpEntity<>(owner.getId());
        if(owner.getReducedPoint()==0) restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol/owner",ownerID);

         Map<String,String> speedViolationInfo = InfoExtractor.extractViolationInformation(violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email",speedViolationInfo);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms",speedViolationInfo);
        violationService.save(violation);
    }

}
