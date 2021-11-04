package smarttraffic.cameraimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.cameraimitation.dto.CaptureDto;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.service.DetectorService;
import smarttraffic.cameraimitation.util.JwtTokenUtil;
import smarttraffic.cameraimitation.util.NumberExtractor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/detector-imitation-service")
public class StartService {

    private final String token = JwtTokenUtil.generateToken("Smart_traffic_control");
    @Autowired
    DetectorRepository detectorRepository;
    @Autowired
    DetectorService detectorService;
    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;
    @Value("${notificationService}")
    private String notifierServiceUrl;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @GetMapping()
    public void sendRequest() throws MalformedURLException, InterruptedException {

        while (true) {
            sendRandomPhotoFromRandomDetector(token);
            Thread.sleep(3000);
        }
    }


    private void sendRandomPhotoFromRandomDetector(String token) throws MalformedURLException {
        RestTemplate restTemplate = new RestTemplate();
        DetectorDto randomDetector = getRandomDetector();
        URL url = getRadnomUrl();
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(String.valueOf(url)));
        String plateNumber = NumberExtractor.extract(textFromImage);
        Instant instant = Instant.now().plus(4, ChronoUnit.HOURS);
        String place = randomDetector.getPlace();
        CaptureDto capture = new CaptureDto(plateNumber, url.toString(), place, instant);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDto> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(detectorAnalyzerUrl, httpEntity);
        if (plateNumber == null) {
            sendNotifocationToPatrol(capture);
        }
    }

    @GetMapping("/{detectorPlace}")
    public DetectorDto getDetector(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        return detectorService.getByPlace(detectorPlace);
    }

    @GetMapping("/previous_detectors/{detectorPlace}")
    public Map<String, Integer> getPreviousDetectors(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        DetectorDto detector = detectorService.getByPlace(detectorPlace);
        return detector.getPreviousDetectorsDistance();
    }

    private URL getRadnomUrl() throws MalformedURLException {
        Random random = new Random();
        String path = String.format("C:\\Users\\asatr\\OneDrive\\Рабочий стол\\SMART-TRAFFIC-CONTROL\\CameraImitation\\src\\main\\resources\\CAR_numbers\\%s.jpg", random.nextInt(30));
        File file = new File(path);
        return file.toURI().toURL();
    }

    private DetectorDto getRandomDetector() {
        Random random = new Random();
        List<DetectorDto> detectors = detectorService.findAll();
        return detectors.get(random.nextInt(detectors.size()));
    }

    private void sendNotifocationToPatrol(CaptureDto capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDto> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }

}
