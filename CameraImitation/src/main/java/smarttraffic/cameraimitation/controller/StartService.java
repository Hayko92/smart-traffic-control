package smarttraffic.cameraimitation.controller;

import smarttraffic.cameraimitation.dto.DetectorDTO;
import smarttraffic.cameraimitation.entity.Capture;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.service.CaptureService;
import smarttraffic.cameraimitation.service.DetectorService;
import smarttraffic.cameraimitation.util.DetectorMapper;
import smarttraffic.cameraimitation.util.NumberExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/detector-imitation-service")
public class StartService {
    @Autowired
    DetectorRepository detectorRepository;
    @Autowired
    CaptureService captureService;
    @Autowired
    DetectorService detectorService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @GetMapping()
    public void sendRequest() throws MalformedURLException, InterruptedException {
        while (true) {
            sendRandomPhotoFromRandomDetector();
            Thread.sleep(5000);
        }
    }

    @GetMapping("/capture/{id}")
    public void sendCapture(@PathVariable String id) {
        Capture capture = captureService.getById(Integer.parseInt(id));
    }

    private void sendRandomPhotoFromRandomDetector() throws MalformedURLException {
        RestTemplate restTemplate = new RestTemplate();
        Detector randomDetector = getRandomDetector();
        URL url = getRadnomUrl();
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(String.valueOf(url)));
        String plateNumber = NumberExtractor.extract(textFromImage);

        Instant instant = Instant.now();
        String place = randomDetector.getPlace();
        Capture capture = new Capture(plateNumber, url.toString(), place, instant);
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        restTemplate.postForLocation("http://127.0.0.1:8081/api/detector_analyzer", httpEntity);
        if (plateNumber == null) {
            //todo che this method
            sendNotifocationToPatrol(capture);
        }
        captureService.save(capture);
    }

    @GetMapping("/api/camera-imitation-service/{detectorPlace}")
    public DetectorDTO getDetector(@PathVariable String detectorPlace) {
        Detector detector = detectorService.getByPlace(detectorPlace);
        DetectorDTO detectorDTO = null;
        if (detector != null) {
            detectorDTO = DetectorMapper.mapToDetectorDTO(detector);
        }
        return detectorDTO;
    }

    private URL getRadnomUrl() throws MalformedURLException {
        Random random = new Random();
        String path = String.format("C:\\Users\\Hayk\\IdeaProjects\\smart-traffic-control\\CameraImitation\\src\\main\\resources\\car_numbers\\%d.jpg", random.nextInt(30));
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
        HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
        restTemplate.postForLocation("http://127.0.0.1:8083/api/notification-service/patrol", httpEntity);
    }
}
