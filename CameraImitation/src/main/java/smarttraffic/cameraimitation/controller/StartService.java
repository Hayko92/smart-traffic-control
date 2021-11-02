package smarttraffic.cameraimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.cameraimitation.entity.Capture;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.service.DetectorService;
import smarttraffic.cameraimitation.util.JwtTokenUtil;
import smarttraffic.cameraimitation.util.NumberExtractor;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/detector-imitation-service")
public class StartService {

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

    JwtTokenUtil jwtTokenUtil ;
    private final   String token = jwtTokenUtil.generateToken("Smart_traffic_control");
    @GetMapping()
    public void sendRequest() throws MalformedURLException, InterruptedException {

        while (true) {
            sendRandomPhotoFromRandomDetector(token);
            Thread.sleep(3000);
        }
    }


    private void sendRandomPhotoFromRandomDetector(String token) throws MalformedURLException {
        RestTemplate restTemplate = new RestTemplate();
        Detector randomDetector = getRandomDetector();
        URL url = getRadnomUrl();
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(String.valueOf(url)));
        String plateNumber = NumberExtractor.extract(textFromImage);
        Instant instant = Instant.now();
        String place = randomDetector.getPlace();
        Capture capture = new Capture(plateNumber, url.toString(), place, instant);
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(detectorAnalyzerUrl, httpEntity);
        if (plateNumber == null) {
            sendNotifocationToPatrol(capture);
        }
    }

    @GetMapping("/{detectorPlace}")
    public Detector getDetector(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token))
            return detectorService.getByPlace(detectorPlace);
        else return null;
    }

    @GetMapping("/previous_detectors/{detectorPlace}")
    public Map<String, Integer> getPreviousDetectors(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            Detector detector = detectorService.getByPlace(detectorPlace);
            return detector.getPreviousDetectorsDistance();
        } else return null;
    }

    private URL getRadnomUrl() throws MalformedURLException {
        Random random = new Random();
        String path = String.format("C:\\Users\\asatr\\OneDrive\\Рабочий стол\\SMART-TRAFFIC-CONTROL\\CameraImitation\\src\\main\\resources\\CAR_numbers\\%s.jpg", random.nextInt(30));
        File file = new File(path);
        return file.toURI().toURL();
    }

    private Detector getRandomDetector() {
        Random random = new Random();
        List<Detector> detectors = detectorRepository.findAll();
        return detectors.get(random.nextInt(detectors.size()));
    }

    private void sendNotifocationToPatrol(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = jwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }

}
