package smarttraffic.cameraimitation.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.cameraimitation.dto.CaptureDto;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.exception.SmartTrafficControlException;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.util.Constants;
import smarttraffic.cameraimitation.util.DetectorMapper;
import smarttraffic.cameraimitation.util.JwtTokenUtil;
import smarttraffic.cameraimitation.util.NumberExtractor;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DetectorServiceImpl implements DetectorService {
    private final DetectorRepository detectorRepository;
    private final AmazonS3 amazonS3;
    private final ResourceLoader resourceLoader;
    private final CloudVisionTemplate cloudVisionTemplate;
    private final String token = JwtTokenUtil.generateToken("${username}");
    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;
    @Value("${notificationService}")
    private String notifierServiceUrl;
    @Value("${s3.bucket.name}")
    private String s3BucketName;

    @Autowired
    public DetectorServiceImpl(DetectorRepository detectorRepository,
                               AmazonS3 amazonS3,
                               ResourceLoader resourceLoader,
                               CloudVisionTemplate cloudVisionTemplate) {
        this.detectorRepository = detectorRepository;
        this.amazonS3 = amazonS3;
        this.resourceLoader = resourceLoader;
        this.cloudVisionTemplate = cloudVisionTemplate;
    }

    public DetectorDto getById(long id) {
        Detector detector = detectorRepository.getById(id);
        return DetectorMapper.mapToDetectorDTO(detector);
    }

    public DetectorDto getByPlace(String place) {
        Detector detector = detectorRepository.getByPlace(place);
        return DetectorMapper.mapToDetectorDTO(detector);
    }

    @Override
    public List<DetectorDto> findAll() {
        List<Detector> detectors = detectorRepository.findAll();
        return detectors.stream()
                .map(DetectorMapper::mapToDetectorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void sendRandomPhotoFromRandomDetector(String token) {
        RestTemplate restTemplate = new RestTemplate();
        DetectorDto randomDetector = getRandomDetector();
        URL urlLocalFile = getRandomURL();
        URL uploadedfile = uploadPhotoAndGetURLBack(urlLocalFile, randomDetector.getPlace());
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(uploadedfile.toString()));
        String plateNumber = NumberExtractor.extract(textFromImage);
        Instant instant = Instant.now().plus(4, ChronoUnit.HOURS);
        String place = randomDetector.getPlace();
        CaptureDto capture = new CaptureDto(plateNumber, uploadedfile.toString(), place, instant);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDto> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.exchange(detectorAnalyzerUrl, HttpMethod.POST, httpEntity, Void.class);
        if (plateNumber == null) {
            sendNotificationToPatrol(capture);
        }
    }

    private DetectorDto getRandomDetector() {
        Random random = new Random();
        List<DetectorDto> detectors = findAll();
        return detectors.get(random.nextInt(detectors.size()));
    }

    private URL getRandomURL() {
        try {
            Random random = new Random();
            String vehiclePhotoUrl = String.format(Constants.VEHICLE_PHOTO_URL, random.nextInt(30));
            return new URL(vehiclePhotoUrl);
        } catch (MalformedURLException e) {
            throw new SmartTrafficControlException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private URL uploadPhotoAndGetURLBack(URL url, String place) {
        try {
            File file = new File("filename.jpg");
            FileUtils.copyURLToFile(url, file);
            String fileName = place + "-" + Instant.now().plus(4, ChronoUnit.HOURS).truncatedTo(ChronoUnit.MILLIS) + ".jpg";
            final PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketName, fileName, file);
            amazonS3.putObject(putObjectRequest);
            return amazonS3.getUrl(s3BucketName, fileName);
        } catch (IOException e) {
            throw new SmartTrafficControlException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void sendNotificationToPatrol(CaptureDto capture) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDto> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }
}
