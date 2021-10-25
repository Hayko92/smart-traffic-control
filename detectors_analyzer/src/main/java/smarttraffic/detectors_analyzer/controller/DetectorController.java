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
    public void receiveCapture(@RequestBody Capture capture) {
        System.out.println(capture);
        String plateNumber = capture.getPlateNumber();
        Vehicle vehicle = vehicleService.getByNumber(plateNumber);
        if (vehicle == null) {
            sendNotifocationToPatrol(capture);
            return;
        }
        if (vehicle.isChecked()) {
            Capture prev = vehicleService.checkSpeed(capture);
            if (prev != null) createSpeedViolation(prev, capture);

        } else {
            boolean hasValidInsurance = vehicleService.checkInsurance(capture, vehicle);
            boolean hasValidTechInspection = vehicleService.checktechinspection(capture, vehicle);
            if (!hasValidInsurance) createViolation(capture, "INS");
            if (!hasValidTechInspection) createViolation(capture, "TECH");
            vehicle.setChecked(true);
            vehicleService.save(vehicle);
        }
    }

    private void createSpeedViolation(Capture prev, Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        List<Capture> captures = new ArrayList<>();
        captures.add(prev);
        captures.add(capture);
        HttpEntity<List<Capture>> httpEntity = new HttpEntity<>(captures);
        restTemplate.postForObject("http://127.0.0.1:8082/api/violationService/speed", httpEntity, String.class);
    }

    public void createViolation(Capture capture, String type) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Capture> body = new HashMap<>();
        body.put(type, capture);
        HttpEntity<Map<String, Capture>> httpEntity = new HttpEntity<>(body);
        restTemplate.postForObject("http://127.0.0.1:8082/api/violationService", httpEntity, String.class);
    }

    private void sendNotifocationToPatrol(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        ResponseEntity<String> result = restTemplate.postForEntity("http://127.0.0.1:8083/api/notification-service/patrol", httpEntity, String.class);
        System.out.println(result.getBody());
    }
}
