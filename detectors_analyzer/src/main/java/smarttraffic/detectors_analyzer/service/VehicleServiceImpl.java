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
import smarttraffic.detectors_analyzer.entity.Detector;
import smarttraffic.detectors_analyzer.model.Vehicle;
import smarttraffic.detectors_analyzer.util.JwtTokenUtil;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    CaptureService captureService;
    @Value("${cameraImitationServise}")
    private String cameraImitationServiceUrl;

    @Override
    public boolean checkInsurance(CaptureDTO capture, Vehicle vehicle) {
        Instant date = capture.getInstant();
        Instant dateOfExpiringInsurance = vehicle.getInsuranceExpiry();
        return date.isBefore(dateOfExpiringInsurance);
    }

    @Override
    public boolean checkTechInspection(CaptureDTO capture, Vehicle vehicle) {
        Instant date = capture.getInstant();
        Instant dateOfExpiringTechInspection = vehicle.getTechInspectionExpiry();
        return date.isBefore(dateOfExpiringTechInspection);
    }

    @Override
    public Map<CaptureDTO, Integer> checkSpeed(CaptureDTO capture,String token) {
        Map<String, Integer> previousDet = getPreviousDetectors(capture,token);
        CaptureDTO prev;
        Map<CaptureDTO, Integer> prevCaptureOverspeedMap = null;
        if (previousDet != null) {
            long secondsFrom = 0;
            for (Map.Entry<String, Integer> prevDet : previousDet.entrySet()) {
                int distance = prevDet.getValue();
                prev = captureService.getByPlaceAndNumber(prevDet.getKey(), capture.getPlateNumber());
                if (prev == null) continue;
                secondsFrom = prev.getInstant().getEpochSecond();
                long secondsTo = capture.getInstant().getEpochSecond();
                long duration = secondsTo - secondsFrom + 1;
                int speedKMH = (int) ((distance / duration) * 3.6);
                if (speedKMH > 70) {
                    prevCaptureOverspeedMap = new HashMap<>();
                    prevCaptureOverspeedMap.put(prev, speedKMH);
                    break;
                }
            }
        }
        return prevCaptureOverspeedMap;
    }

    private Map<String, Integer> getPreviousDetectors(CaptureDTO capture,String token) {
        RestTemplate restTemplate = new RestTemplate();
        String place = capture.getPlace();
        HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Detector> response = restTemplate.exchange(cameraImitationServiceUrl + "/" + place, HttpMethod.GET,httpEntity, Detector.class);
        Detector detector  = response.getBody();
        if (detector != null) return detector.getPreviousDetectorsDistance();
        else return null;
    }
}
