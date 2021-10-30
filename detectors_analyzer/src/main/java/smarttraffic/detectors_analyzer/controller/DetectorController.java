package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.model.Vehicle;
import smarttraffic.detectors_analyzer.service.CaptureService;
import smarttraffic.detectors_analyzer.service.VehicleService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/detector-analyzer")
public class DetectorController {

    @Autowired
    CaptureService captureService;
    @Autowired
    VehicleService vehicleService;

    @Value("${violationService}")
    private String violationServiceUrl;
    @Value("${notificationService}")
    private String notifierServiceUrl;
    @Value("${vehicleService}")
    private String vehicleServiceUrl;

    @GetMapping("/capture/{id}")
    public void sendCapture(@PathVariable String id) {
        Capture capture = captureService.getById(Integer.parseInt(id));
    }
    @PostMapping
    public void receiveCapture(@RequestBody Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        Capture prev = null;
        String plateNumber = capture.getPlateNumber();
        if(plateNumber!=null) captureService.save(capture);
        Vehicle vehicle = restTemplate.getForObject(vehicleServiceUrl + "/" + plateNumber, Vehicle.class);
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
            setChecked(vehicle);
        }
        captureService.save(capture);
    }

    private void setChecked(Vehicle vehicle) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(vehicleServiceUrl + "/set-status-checked", vehicle);
    }

    private void createSpeedViolation(Capture prev, Capture current, int speed) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> info = new HashMap<>();
        info.put("previousCapture", prev.getId());
        info.put("currentCapture", current.getId());
        info.put("speed", speed);
        HttpEntity<Map<String, Integer>> httpEntity = new HttpEntity<>(info);
        restTemplate.postForLocation(violationServiceUrl + "/speed", httpEntity);
    }

    public void createViolation(Capture capture, String type) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Capture> body = new HashMap<>();
        body.put(type, capture);
        HttpEntity<Map<String, Capture>> httpEntity = new HttpEntity<>(body);
        restTemplate.postForLocation(violationServiceUrl, httpEntity);
    }

    private void sendNotificationToPatrol(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }
}