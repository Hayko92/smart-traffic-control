package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Vehicle;
import smarttraffic.detectors_analyzer.service.CaptureService;
import smarttraffic.detectors_analyzer.service.VehicleService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
public class DetectorController {
    @Autowired
    CaptureService captureService;

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/api/detector_analyzer")
    public List<Integer> receiveCapture(@RequestBody Capture capture) {
        System.out.println(capture);
        String plateNumber = capture.getNumber();
        //Vehicle vehicle = vehicleService.getByNumber(plateNumber);
//        if(vehicle==null) {
//            sendNotifocationToPatrol(capture);
//            return null;
//        }
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setChecked(false);
        vehicle1.setId(2L);
        vehicle1.setPlateNumber(plateNumber);
        vehicle1.setInsuranceExpiry(Instant.now().minus(1, ChronoUnit.DAYS));
        vehicle1.setTechInspectionExpiry(Instant.now().plus(1, ChronoUnit.DAYS));
        if (vehicle1.isChecked()) {
            //todo method overspeed
            //      boolean isOverspeed =  vehicleService.checkSpeed(capture)
        } else {
            boolean hasValidInsurance = vehicleService.checkInsurance(capture, vehicle1);
            boolean hasValidTechInspection = vehicleService.checktechinspection(capture, vehicle1);
            if (!hasValidInsurance) createViolation(capture, "INS");
            if (!hasValidTechInspection) createViolation(capture, "TECH");
            vehicle1.setChecked(true);
            vehicleService.save(vehicle1);
        }
        List<Integer> violationsId = new ArrayList<>();
        violationsId.add(5);
        violationsId.add(1);

        return violationsId;
    }

    public Integer createViolation(Capture capture, String type) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Capture> body = new HashMap<>();
        body.put(type, capture);
        HttpEntity<Map<String, Capture>> httpEntity = new HttpEntity<>(body);
        return restTemplate.postForObject("http://127.0.0.1:8082/api/violationService", httpEntity, Integer.class);
    }
    private void sendNotifocationToPatrol(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        ResponseEntity<String> result =  restTemplate.postForEntity("http://127.0.0.1:8083/api/notification-service/patrol",httpEntity,String.class);
        System.out.println(result.getBody());
    }
}
