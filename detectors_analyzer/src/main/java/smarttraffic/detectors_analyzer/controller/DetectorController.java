package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Vehicle;
import smarttraffic.detectors_analyzer.service.CaptureService;
import smarttraffic.detectors_analyzer.service.VehicleService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DetectorController {
    @Autowired
    CaptureService captureService;

    @Autowired
    VehicleService vehicleService;

    @PostMapping("/api/detector_analyzer")
    public void receiveCapture(@RequestBody Capture capture) {
        Capture prev = null;

        System.out.println(capture);
        String plateNumber = capture.getPlateNumber();
        Vehicle vehicle = vehicleService.getByNumber(plateNumber);
        if (vehicle == null) {
            sendNotificationToPatrol(capture);
            return;
        }
        if (vehicle.isChecked()) {
            int speed = 0;
            Map<Capture, Integer> prevCaptureOverspeed = vehicleService.checkSpeed(capture);
            if (prevCaptureOverspeed != null) {
                for (Map.Entry<Capture, Integer> entry : prevCaptureOverspeed.entrySet()) {
                    prev = entry.getKey();
                    speed = entry.getValue();
                }
                createSpeedViolation(prev, capture, speed);
            }
        } else {
            boolean hasValidInsurance = vehicleService.checkInsurance(capture, vehicle);
            boolean hasValidTechInspection = vehicleService.checkTechInspection(capture, vehicle);
            if (!hasValidInsurance) createViolation(capture, "INS");
            if (!hasValidTechInspection) createViolation(capture, "TECH");
            vehicle.setChecked(true);
            vehicleService.save(vehicle);
        }
    }

    private void createSpeedViolation(Capture prev, Capture current, int speed) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> info = new HashMap<>();
        info.put("previousCapture", prev.getId());
        info.put("currentCapture", current.getId());
        info.put("speed", speed);
        HttpEntity<Map<String, Integer>> httpEntity = new HttpEntity<>(info);
        restTemplate.postForLocation("http://127.0.0.1:8082/api/violationService/speed", httpEntity);
    }

    public void createViolation(Capture capture, String type) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Capture> body = new HashMap<>();
        body.put(type, capture);
        HttpEntity<Map<String, Capture>> httpEntity = new HttpEntity<>(body);
        restTemplate.postForLocation("http://127.0.0.1:8082/api/violationService", httpEntity);
    }

    private void sendNotificationToPatrol(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol", httpEntity);
    }
}