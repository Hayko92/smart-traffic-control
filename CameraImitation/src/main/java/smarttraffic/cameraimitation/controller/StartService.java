package smarttraffic.cameraimitation.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/detector-imitation-service")
public class StartService {

    private final String token = JwtTokenUtil.generateToken("${username}");
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

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket.name}")
    private String s3BucketName;

    @GetMapping()
    public void sendRequest() throws MalformedURLException, InterruptedException, URISyntaxException {

        while (true) {
            sendRandomPhotoFromRandomDetector(token);
            Thread.sleep(3000);
        }

    }

    private void sendRandomPhotoFromRandomDetector(String token) throws MalformedURLException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        DetectorDto randomDetector = getRandomDetector();
        URL urlLocalFile = getRadnomUrl();
        URL uploadedfile = uploadPhotoAndgetURLback(urlLocalFile, randomDetector.getPlace());
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(String.valueOf(uploadedfile)));
        String plateNumber = NumberExtractor.extract(textFromImage);
        Instant instant = Instant.now().plus(4, ChronoUnit.HOURS);
        String place = randomDetector.getPlace();
        CaptureDto capture = new CaptureDto(plateNumber, uploadedfile.toString(), place, instant);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDto> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.exchange(detectorAnalyzerUrl, HttpMethod.POST, httpEntity, Void.class);
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
        String path = String.format("C:\\Users\\asatr\\OneDrive\\Рабочий стол\\smart_traffic_control_\\CameraImitation\\src\\main\\resources\\CAR_numbers\\%s.jpg", random.nextInt(30));
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

    private URL uploadPhotoAndgetURLback(URL url, String place) throws URISyntaxException {
        File file = Paths.get(url.toURI()).toFile();
        String fileName = place + "-" + Instant.now().plus(4, ChronoUnit.HOURS).truncatedTo(ChronoUnit.MILLIS) + ".jpg";
        final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
        amazonS3.putObject(putObjectRequest);
        URL s3Url = amazonS3.getUrl(s3BucketName, fileName);
        return s3Url;
    }

}
