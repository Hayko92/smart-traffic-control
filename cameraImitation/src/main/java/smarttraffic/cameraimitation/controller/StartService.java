package smarttraffic.cameraimitation.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.cameraimitation.dto.CaptureDto;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.exception.SmartTrafficControlException;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.service.DetectorService;
import smarttraffic.cameraimitation.util.Constants;
import smarttraffic.cameraimitation.util.JwtTokenUtil;
import smarttraffic.cameraimitation.util.NumberExtractor;

import java.io.File;
import java.io.IOException;
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
    private final DetectorService detectorService;
    private final String token = JwtTokenUtil.generateToken("${username}");

    @Autowired
    public StartService(DetectorService detectorService) {
        this.detectorService = detectorService;
    }

    @GetMapping()
    public void sendRequest() {
        while (true) {
            detectorService.sendRandomPhotoFromRandomDetector(token);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new SmartTrafficControlException(e.getMessage(), HttpStatus.valueOf(500));
            }
        }
    }

    @GetMapping("/{detectorPlace}")
    public DetectorDto getDetector(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        return detectorService.getByPlace(detectorPlace);
    }

    @GetMapping("/all")
    public List<DetectorDto> getAllDetectors(@RequestHeader(name = "AUTHORIZATION") String token) {
        return detectorService.findAll();
    }

    @GetMapping("/previous_detectors/{detectorPlace}")
    public Map<String, Integer> getPreviousDetectors(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        DetectorDto detector = detectorService.getByPlace(detectorPlace);
        return detector.getPreviousDetectorsDistance();
    }

}
