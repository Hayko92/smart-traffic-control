package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Detector;
import smarttraffic.detectors_analyzer.model.Vehicle;

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
    public boolean checkInsurance(Capture capture, Vehicle vehicle) {
        Instant date = capture.getInstant();
        Instant dateOfExpiringInsurance = vehicle.getInsuranceExpiry();
        return date.isBefore(dateOfExpiringInsurance);
    }

    @Override
    public boolean checkTechInspection(Capture capture, Vehicle vehicle) {
        Instant date = capture.getInstant();
        Instant dateOfExpiringTechInspection = vehicle.getTechInspectionExpiry();
        return date.isBefore(dateOfExpiringTechInspection);
    }

    @Override
    public Map<Capture, Integer> checkSpeed(Capture capture) {
        Map<Detector, Integer> previousDet = getPreviousDetectors(capture);
        Capture prev;
        Map<Capture, Integer> prevCaptureOverspeedMap = null;
        if (previousDet != null) {
            for (Map.Entry<Detector, Integer> prevDet : previousDet.entrySet()) {
                int distance = prevDet.getValue();
                //todo
                prev = captureService.getByPlace(prevDet.getKey().getPlace());
                long secondsFrom = prev.getInstant().getEpochSecond();
                long secondsTo = capture.getInstant().getEpochSecond();
                long duration = secondsTo - secondsFrom;
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

    private Map<Detector, Integer> getPreviousDetectors(Capture capture) {
        RestTemplate restTemplate = new RestTemplate();
        String place = capture.getPlace();
        Detector detector = restTemplate.getForObject(cameraImitationServiceUrl + "/" + place, Detector.class);
        if (detector != null)
            return detector.getPreviousDetectorsDistance();
        else return null;
    }
}
