package smarttraffic.violations_analyzer_service.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violations_analyzer_service.model.Capture;
import smarttraffic.violations_analyzer_service.model.Detector;
import smarttraffic.violations_analyzer_service.model.Vehicle;
import smarttraffic.violations_analyzer_service.model.Violation;
import smarttraffic.violations_analyzer_service.service.*;
import smarttraffic.violations_analyzer_service.util.JwtTokenUtil;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/violations_analyzer_service")
public class ViolationsAnalyzerController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    DetectorService detectorService;

    @Autowired
    CaptureService captureService;

    @Autowired
    ViolationService violationService;

    @Autowired
    ViolationsAnalyzerService violationsAnalyzerService;

    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;

    @Value("${vehicleService}")
    private String vehicleServiceURL;

    @Value("${cameraImitationServise}")
    private String detectorImitationUrl;

    @Value("${violationService}")
    private String violationServiceUrl;

    @GetMapping("/all_time")
    public void collectdata(@RequestHeader(name = "AUTHORIZATION") String token,
                            @RequestParam(required = false) Instant from,
                            @RequestParam(required = false) Instant to) throws FileNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<List<Vehicle>> allvehicles = restTemplate.exchange(vehicleServiceURL + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        ResponseEntity<List<Detector>> allDetectors = restTemplate.exchange(detectorImitationUrl + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        ResponseEntity<List<Capture>> allCaptures = restTemplate.exchange(detectorAnalyzerUrl + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        ResponseEntity<List<Violation>> allViolations = restTemplate.exchange(violationServiceUrl + "/all", HttpMethod.GET, httpEntity, new ParameterizedTypeReference<>() {
        });
        List<Vehicle> allVehicleslist = allvehicles.getBody();
        vehicleService.saveAll(allVehicleslist);

        List<Detector> alldetectorsList = allDetectors.getBody();
        detectorService.saveAll(alldetectorsList);

        List<Capture> allCaptureList = allCaptures.getBody();
        captureService.saveAll(allCaptureList);

        List<Violation> allViolationsList = allViolations.getBody();
        violationService.saveAll(allViolationsList);
        violationsAnalyzerService.startAnalyze(from, to);
    }

}
