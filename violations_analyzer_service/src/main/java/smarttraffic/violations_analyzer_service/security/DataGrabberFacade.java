package smarttraffic.violations_analyzer_service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violations_analyzer_service.model.Capture;
import smarttraffic.violations_analyzer_service.model.Detector;
import smarttraffic.violations_analyzer_service.model.Vehicle;
import smarttraffic.violations_analyzer_service.model.Violation;
import smarttraffic.violations_analyzer_service.service.*;
import smarttraffic.violations_analyzer_service.util.JwtTokenUtil;

import java.time.Instant;
import java.util.List;

@Service
public class DataGrabberFacade {

    private final VehicleService vehicleService;
    private final DetectorService detectorService;
    private final CaptureService captureService;
    private final ViolationService violationService;

    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;

    @Value("${vehicleService}")
    private String vehicleServiceURL;

    @Value("${cameraImitationServise}")
    private String detectorImitationUrl;

    @Value("${violationService}")
    private String violationServiceUrl;

    @Autowired
    public DataGrabberFacade(VehicleService vehicleService, DetectorService detectorService, CaptureService captureService, ViolationService violationService, ViolationsAnalyzerService violationsAnalyzerService) {
        this.vehicleService = vehicleService;
        this.detectorService = detectorService;
        this.captureService = captureService;
        this.violationService = violationService;
    }

    public void grabData(String token) {
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
    }
}
