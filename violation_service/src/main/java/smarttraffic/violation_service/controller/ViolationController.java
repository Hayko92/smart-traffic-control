package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.entity.*;
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
    private Capture capture;

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
        violation.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violation.setPhotoUrl2(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(captureCurrent.getPlace());
        violation.setOwner(owner);
        violation.setVehicle(vehicle);
        //reducing owners points, sending notification to patrol if no points left for current owner
        HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
        if (owner.getReducedPoint() == 0)
            restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol/owner", ownerID);

        Map<String, String> speedViolationInfo = InfoExtractor.extractViolationInformation(violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email", speedViolationInfo);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms", speedViolationInfo);
        violationService.save(violation);
    }

    @PostMapping("/techinspection")
    public void createTechinspection(@RequestBody Map<String, Capture> body) {
        RestTemplate restTemplate = new RestTemplate();
        Capture idPrev = body.get("TECH");
        int price = ViolationCounter.techInspection();
        Capture capturePrev = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" +
                idPrev, Capture.class);
        //todo add url handling in vehicle service (url must return owner of vehicle by vehicle number)
        Owner owner = restTemplate.getForObject("http://127.0.0.1:8084/api/vehicle-service/owner/" +
                capturePrev.getPlateNumber(), Owner.class);
        TechinspectionViolation violation = new TechinspectionViolation();
        Vehicle vehicle = owner.getVehicleByPlateNUmber(capture.getPlateNumber());
        violation.setCreationDate(capturePrev.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setPhotoUrl1(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(capturePrev.getPlace());
        violation.setOwner(owner);
        violation.setVehicle(vehicle);
        //reducing owners points, sending notification to patrol if no points left for current owner
        HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
        if (owner.getReducedPoint() == 0)
            restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol/owner", ownerID);

        Map<String, String> techinspectionViolationInfo = InfoExtractor.extractViolationInformation(violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email", techinspectionViolationInfo);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms", techinspectionViolationInfo);
        violationService.save(violation);
    }

    @PostMapping("/insurance")
    public void createInsurance(@RequestBody Map<String, Capture> body) {
        RestTemplate restTemplate = new RestTemplate();
        Capture idPrev = body.get("INS");
        int price = ViolationCounter.countInsurancePrice();
        Capture capturePrev = restTemplate.getForObject("http://127.0.0.1:8080/api/detector-imitation-service/capture/" +
                idPrev, Capture.class);
        //todo add url handling in vehicle service (url must return owner of vehicle by vehicle number)
        Owner owner = restTemplate.getForObject("http://127.0.0.1:8084/api/vehicle-service/owner/" +
                capturePrev.getPlateNumber(), Owner.class);
        InsuranceViolation violation = new InsuranceViolation();
        Vehicle vehicle = owner.getVehicleByPlateNUmber(capture.getPlateNumber());
        violation.setCreationDate(capturePrev.getInstant().truncatedTo(ChronoUnit.DAYS));
        violation.setPhotoUrl1(capturePrev.getPhotoUrl());
        violation.setPrice(price);
        violation.setPlace(capturePrev.getPlace());
        violation.setOwner(owner);
        violation.setVehicle(vehicle);
        //reducing owners points, sending notification to patrol if no points left for current owner
        HttpEntity<Long> ownerID = new HttpEntity<>(owner.getId());
        if (owner.getReducedPoint() == 0)
            restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol/owner", ownerID);

        Map<String, String> insuranceViolationInfo = InfoExtractor.extractViolationInformation(violation);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/email", insuranceViolationInfo);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/sms", insuranceViolationInfo);
        violationService.save(violation);
    }
}