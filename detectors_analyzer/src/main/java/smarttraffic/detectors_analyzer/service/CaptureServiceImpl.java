package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.dto.VehicleDTO;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.repository.CaptureRepository;
import smarttraffic.detectors_analyzer.util.CaptureMapper;
import smarttraffic.detectors_analyzer.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    private VehicleService vehicleService;
    private final CaptureRepository captureRepository;
    @Value("${violationService}")
    private String violationServiceUrl;
    @Value("${notificationService}")
    private String notifierServiceUrl;
    @Value("${vehicleService}")
    private String vehicleServiceUrl;

    @Autowired
    public CaptureServiceImpl(CaptureRepository captureRepository) {
        this.captureRepository = captureRepository;
    }

    @Override
    public CaptureDTO getByPlateNumber(String plateNumber) {
        Capture capture = captureRepository.findFirstByPlateNumberOrderByIdDesc(plateNumber);
        if (capture != null) return CaptureMapper.mapToDto(capture);
        else return null;
    }

    @Override
    public CaptureDTO getByPlaceAndNumber(String place, String platenumber) {
        Capture capture = captureRepository.findFirstByPlaceAndPlateNumberOrderByIdDesc(place, platenumber);
        if (capture != null) return CaptureMapper.mapToDto(capture);
        else return null;
    }

    @Override
    public List<CaptureDTO> findAll() {
        return captureRepository.findAll()
                .stream()
                .map(CaptureMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void receiveCapture(CaptureDTO capture, String token) {
        RestTemplate restTemplate = new RestTemplate();
        CaptureDTO prev = null;
        String plateNumber = capture.getPlateNumber();
        if (plateNumber != null) {
            int id = save(capture);
            capture.setId(id);
        }
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<VehicleDTO> response = restTemplate.exchange(vehicleServiceUrl + "/" + plateNumber, HttpMethod.GET, httpEntity, VehicleDTO.class);
        VehicleDTO vehicleDTO = response.getBody();
        if (vehicleDTO == null) {
            sendNotificationToPatrol(capture, token);
            return;
        }
        if (vehicleDTO.isChecked()) {
            int speed = 0;
            Map<CaptureDTO, Integer> prevCaptureOverspeed = vehicleService.checkSpeed(capture, token);
            if (prevCaptureOverspeed != null) {
                for (Map.Entry<CaptureDTO, Integer> entry : prevCaptureOverspeed.entrySet()) {
                    prev = entry.getKey();
                    speed = entry.getValue();
                }
                createSpeedViolation(prev, capture, speed, token);
            }
        } else {
            boolean hasValidInsurance = vehicleService.checkInsurance(capture, vehicleDTO);
            boolean hasValidTechInspection = vehicleService.checkTechInspection(capture, vehicleDTO);
            if (!hasValidInsurance) createViolation(capture, "INS", token);
            if (!hasValidTechInspection) createViolation(capture, "TECH", token);
            setChecked(vehicleDTO, token);
        }
        save(capture);
    }

    @Override
    public CaptureDTO getById(int id) {
        Capture capture = captureRepository.getById(id);
        if (capture != null) return CaptureMapper.mapToDto(capture);
        else return null;
    }

    @Override
    public int save(CaptureDTO captureDTO) {
        if (captureDTO != null) {
            Capture capture = CaptureMapper.mapToCapture(captureDTO);
            captureRepository.save(capture);
            return capture.getId();
        }
        return 0;
    }

    private void sendNotificationToPatrol(CaptureDTO capture, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<CaptureDTO> httpEntity = new HttpEntity<>(capture, headers);
        restTemplate.postForLocation(notifierServiceUrl + "/patrol", httpEntity);
    }

    private void createSpeedViolation(CaptureDTO prev, CaptureDTO current, int speed, String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> info = new HashMap<>();
        info.put("previousCapture", prev.getId());
        info.put("currentCapture", current.getId());
        info.put("speed", speed);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, Integer>> httpEntity = new HttpEntity<>(info, headers);
        restTemplate.exchange(violationServiceUrl + "/speed", HttpMethod.POST, httpEntity, Void.class);
    }

    public void createViolation(CaptureDTO capture, String type, String token) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, CaptureDTO> body = new HashMap<>();
        body.put(type, capture);
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, CaptureDTO>> httpEntity = new HttpEntity<>(body, headers);
        restTemplate.exchange(violationServiceUrl, HttpMethod.POST, httpEntity, Void.class);
    }

    private void setChecked(VehicleDTO vehicleDTO, String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        restTemplate.exchange(vehicleServiceUrl + "/set-status-checked/" + vehicleDTO.getId(), HttpMethod.GET, httpEntity, Void.class);
    }
}
