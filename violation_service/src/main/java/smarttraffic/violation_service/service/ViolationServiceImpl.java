package smarttraffic.violation_service.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.dto.CaptureDTO;
import smarttraffic.violation_service.dto.OwnerDTO;
import smarttraffic.violation_service.dto.VehicleDTO;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.mapper.ViolationMapper;
import smarttraffic.violation_service.repository.VehicleRepository;
import smarttraffic.violation_service.repository.ViolationRepository;
import smarttraffic.violation_service.util.JwtTokenUtil;
import smarttraffic.violation_service.util.ViolationCounter;
import smarttraffic.violation_service.util.ViolationInfoResolver;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {
    private final ViolationRepository violationRepository;
    private final VehicleRepository vehicleRepository;
    @Value("${sqs.url}")
    String sqsURL;
    @Value("${sqs.region}")
    String region;
    @Value("${access.key.id}")
    String accessKey;
    @Value("${access.key.secret}")
    private String secretKey;
    @Value("${cameraImitationServise}")
    private String detectorImitationUrl;
    @Value("${detectorsAnalyzer}")
    private String detectorAnalyzerUrl;
    @Value("${vehicleService}")
    private String vehicleServiceUrl;
    @Value("${notificationService}")
    private String notificationServiceUrl;

    @Autowired
    public ViolationServiceImpl(ViolationRepository violationRepository, VehicleRepository vehicleRepository) {
        this.violationRepository = violationRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<ViolationDTO> getAllViolations() {
        List<Violation> violations = violationRepository.findAll();
        return violations.stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public long save(ViolationDTO violation) {
        Violation violation1 = ViolationMapper.mapToEntity(violation);
        violationRepository.save(violation1);
        return violation1.getId();
    }

    @Override
    public List<ViolationDTO> getByNumber(String number) {
        List<Violation> violation = violationRepository.getAllByNumber(number);
        return violation.stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ViolationDTO violationDTO) {
        Violation violation = ViolationMapper.mapToEntity(violationDTO);
        violationRepository.delete(violation);
    }

    @Override
    public List<ViolationDTO> getAllByNumber(String number) {
        List<Violation> violationList = violationRepository.getAllByNumber(number);
        return violationList
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDTO> getAllByOwnerID(Long ownerID) {
        List<Violation> violationList = violationRepository.getAllByOwnerId(ownerID);
        return violationList
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDTO> findAll() {
        return violationRepository.findAll()
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleFixedDelayTask() {
        vehicleRepository.setChekedToFalse();
    }

    @Override
    public ViolationDTO findById(long id) {
        Violation violation = violationRepository.findById(id);
        return ViolationMapper.mapToDto(violation);
    }

    @Override
    public void createSpeedViolation(Map<String, Integer> info, String token) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        OwnerDTO owner = null;
        int idPrev = info.get("previousCapture");
        int idCurr = info.get("currentCapture");
        int speed = info.get("speed");
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        int price = ViolationCounter.countSpeedViolationBasePrice(speed);
        ResponseEntity<CaptureDTO> capturePrevResp = restTemplate.exchange(detectorAnalyzerUrl + "/capture/" + idPrev, HttpMethod.GET, httpEntity, CaptureDTO.class);
        ResponseEntity<CaptureDTO> captureCurrentResp = restTemplate.exchange(detectorAnalyzerUrl + "/capture/" + idCurr, HttpMethod.GET, httpEntity, CaptureDTO.class);
        CaptureDTO capturePrev = capturePrevResp.getBody();
        CaptureDTO captureCurrent = captureCurrentResp.getBody();
        ResponseEntity<VehicleDTO> vehicleResp = restTemplate.exchange(vehicleServiceUrl + "/" + captureCurrent.getPlateNumber(), HttpMethod.GET, httpEntity, VehicleDTO.class);
        VehicleDTO vehicle = vehicleResp.getBody();
        if (vehicle != null) owner = vehicle.getOwner();
        ViolationDTO violationDTO = createSpeedViolationDto(price, capturePrev, captureCurrent, vehicle);
        checkOwnerPoints(owner, token);
        long id = save(violationDTO);

        violationDTO.setId(id);
        sendNotifications(violationDTO, token);
    }

    @Override
    public void createViolation(Map<String, CaptureDTO> body, String token) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        CaptureDTO capture = getCapture(body);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<VehicleDTO> vehicleResp = restTemplate.exchange(vehicleServiceUrl + "/" + capture.getPlateNumber(), HttpMethod.GET, httpEntity, VehicleDTO.class);
        VehicleDTO vehicle = vehicleResp.getBody();
        ViolationDTO violationDTO = checkViolationTypeAndCreate(body, vehicle);
        checkOwnerPoints(vehicle.getOwner(), token);
        long id = save(violationDTO);
        violationDTO.setId(id);
        sendNotifications(violationDTO, token);
    }

    private ViolationDTO createViolationDTO(String type, CaptureDTO capture, VehicleDTO vehicle) {
        ViolationDTO violationDTO = new ViolationDTO(type);
        violationDTO.setCreationDate(capture.getInstant().truncatedTo(ChronoUnit.DAYS));
        violationDTO.setPhotoUrl1(capture.getPhotoUrl());
        violationDTO.setPrice(5000);
        violationDTO.setPlace(capture.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        violationDTO.setNumber(capture.getPlateNumber());
        return violationDTO;
    }

    private ViolationDTO createSpeedViolationDto(int price, CaptureDTO capturePrev, CaptureDTO captureCurrent, VehicleDTO vehicle) {
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setType("SPEED");
        violationDTO.setCreationDate(captureCurrent.getInstant().truncatedTo(ChronoUnit.MILLIS));
        violationDTO.setPhotoUrl1(captureCurrent.getPhotoUrl());
        violationDTO.setPhotoUrl2(capturePrev.getPhotoUrl());
        violationDTO.setPrice(price);
        violationDTO.setPlace(captureCurrent.getPlace());
        violationDTO.setOwner(vehicle.getOwner());
        violationDTO.setVehicle(vehicle);
        violationDTO.setNumber(captureCurrent.getPlateNumber());
        return violationDTO;
    }

    private void checkOwnerPoints(OwnerDTO owner, String token) {
        if (owner != null) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
            HttpEntity httpEntity = new HttpEntity(headers);
            if (owner.getRedusedPoint() < 0)
                restTemplate.exchange(notificationServiceUrl + "/patrol/owner/" + owner.getId(), HttpMethod.GET, httpEntity, Void.class);
            else {
                HttpEntity<OwnerDTO> httpEntityWithOwner = new HttpEntity<>(owner, headers);
                restTemplate.exchange(vehicleServiceUrl + "/owner/" + owner.getId(), HttpMethod.PUT, httpEntityWithOwner, Void.class);
            }
        }
    }

    private void sendNotifications(ViolationDTO violationDTO, String token) throws JsonProcessingException {
        AmazonSQS sqs = getAmazonSQS();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> speedViolationInfo = ViolationInfoResolver.resolve(violationDTO);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(speedViolationInfo, headers);
        String jsonToMap = objectMapper.writeValueAsString(speedViolationInfo);
        sqs.sendMessage(sqsURL, jsonToMap);
    }

    private AmazonSQS getAmazonSQS() {
        BasicAWSCredentials bAWSc = new BasicAWSCredentials(accessKey, secretKey);
        AmazonSQS sqs = AmazonSQSClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(bAWSc))
                .build();
        return sqs;
    }

    private CaptureDTO getCapture(Map<String, CaptureDTO> info) {
        CaptureDTO capture;
        if (info.containsKey("TECH")) {
            capture = info.get("TECH");
        } else {
            capture = info.get("INS");
        }
        return capture;
    }

    private ViolationDTO checkViolationTypeAndCreate(Map<String, CaptureDTO> body, VehicleDTO vehicle) {
        CaptureDTO capture;
        ViolationDTO violationDTO;
        if (body.containsKey("TECH")) {
            capture = body.get("TECH");
            violationDTO = createViolationDTO("TECH", capture, vehicle);
        } else {
            capture = body.get("INS");
            violationDTO = createViolationDTO("INS", capture, vehicle);
        }
        return violationDTO;
    }
}