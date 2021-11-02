package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.model.Vehicle;
import smarttraffic.detectors_analyzer.service.CaptureService;
import smarttraffic.detectors_analyzer.service.VehicleService;
import smarttraffic.detectors_analyzer.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/detector-analyzer")
public class DetectorController {

    @Autowired
    CaptureService captureService;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Value("${violationService}")
    private String violationServiceUrl;
    @Value("${notificationService}")
    private String notifierServiceUrl;
    @Value("${vehicleService}")
    private String vehicleServiceUrl;

    @GetMapping("/capture/{id}")
    public CaptureDTO sendCapture(@PathVariable String id, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            CaptureDTO capture = captureService.getById(Integer.parseInt(id));
            return capture;
        } else return null;
    }

    @PostMapping
    public void receiveCapture(@RequestBody CaptureDTO capture, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            RestTemplate restTemplate = new RestTemplate();
            CaptureDTO prev = null;
            String plateNumber = capture.getPlateNumber();
            if (plateNumber != null) captureService.save(capture);
            HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<Vehicle> response = restTemplate.exchange(vehicleServiceUrl + "/" + plateNumber, HttpMethod.GET, httpEntity, Vehicle.class);
            Vehicle vehicle = response.getBody();
            if (vehicle == null) {
                sendNotificationToPatrol(capture, token);
                return;
            }
            if (vehicle.isChecked()) {
                int speed = 0;
                Map<CaptureDTO, Integer> prevCaptureOverspeed = vehicleService.checkSpeed(capture);
                if (prevCaptureOverspeed != null) {
                    for (Map.Entry<CaptureDTO, Integer> entry : prevCaptureOverspeed.entrySet()) {
                        prev = entry.getKey();
                        speed = entry.getValue();
                    }
                    createSpeedViolation(prev, capture, speed, token);
                }
            } else {
                boolean hasValidInsurance = vehicleService.checkInsurance(capture, vehicle);
                boolean hasValidTechInspection = vehicleService.checkTechInspection(capture, vehicle);
                if (!hasValidInsurance) createViolation(capture, "INS", token);
                if (!hasValidTechInspection) createViolation(capture, "TECH", token);
                setChecked(vehicle, token);
            }
            captureService.save(capture);
        }
    }

    private void setChecked(Vehicle vehicle, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        restTemplate.exchange(vehicleServiceUrl + "/set-status-checked/" + vehicle.getId(), HttpMethod.GET, httpEntity, String.class);
    }

    private void createSpeedViolation(CaptureDTO prev, CaptureDTO current, int speed, String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> info = new HashMap<>();
        info.put("previousCapture", prev.getId());
        info.put("currentCapture", current.getId());
        info.put("speed", speed);
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, Integer>> httpEntity = new HttpEntity<>(info, headers);
        restTemplate.postForLocation(violationServiceUrl + "/speed", httpEntity);
    }

    public void createViolation(CaptureDTO capture, String type, String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, CaptureDTO> body = new HashMap<>();
        body.put(type, capture);
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, CaptureDTO>> httpEntity = new HttpEntity<>(body, headers);
        restTemplate.postForLocation(violationServiceUrl, httpEntity);
    }

    private void sendNotificationToPatrol(CaptureDTO capture, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDTO> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }

}