package com.example.cameraimitation.controller;

import com.example.cameraimitation.entity.Capture;
import com.example.cameraimitation.entity.Detector;
import com.example.cameraimitation.repository.DetectorRepository;
import com.example.cameraimitation.service.CaptureService;
import com.example.cameraimitation.util.NumberExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Random;

@RestController
public class StartService {
    @Autowired
    DetectorRepository detectorRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    @Autowired
    CaptureService captureService;

    @GetMapping("/api/startCameras")
    public void sendRequest() throws MalformedURLException {
            RestTemplate restTemplate = new RestTemplate();
            Detector randomDetector = getRandomDetector();
            URL url = getRadnomUrl();
            String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(String.valueOf(url)));
            String plateNumber = NumberExtractor.extract(textFromImage);
            Instant instant = Instant.now();
            String place = randomDetector.getName();
            Capture capture = new Capture(plateNumber,url.toString(),place,instant);
            HttpEntity<Capture> httpEntity = new HttpEntity<>(capture);
            List<Integer> violationsID = restTemplate.postForObject("http://127.0.0.1:8081/api/detector_analyzer", httpEntity, List.class);
            captureService.save(capture,violationsID);
    }

    private URL getRadnomUrl() throws MalformedURLException {
       Random random = new Random();
        String path = String.format("C:\\Users\\asatr\\OneDrive\\Рабочий стол\\SMART-TRAFFIC-CONTROL\\CameraImitation\\src\\main\\resources\\CAR_numbers\\%d.jpg", random.nextInt(30));
        File file = new File(path);
        return file.toURI().toURL();
    }

    private Detector getRandomDetector() {
        Random random = new Random();
        List<Detector> detectors = detectorRepository.findAll();
        return detectors.get(random.nextInt(detectors.size()));
    }

}
