package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.entity.Violation;

@Service
public interface CaptureGIcrossroadService {
     
     void save(CaptureGIcrossRoad captureGIcrossRoad);

     boolean hasValidInsurance(CaptureGIcrossRoad captureGIcrossRoad);

     boolean hasValidTechInspection(CaptureGIcrossRoad captureGIcrossRoad);

     void setStatusChecked(CaptureGIcrossRoad captureGIcrossRoad);

     void createViolation(Violation violation, CaptureGIcrossRoad... captureGIcrossRoad);
     
     boolean hasValidNumber(CaptureGIcrossRoad captureGIcrossRoad);

     void sendNotificationToPatrol(CaptureGIcrossRoad captureGIcrossRoad);

     void checkAndSave(CaptureGIcrossRoad captureGIcrossRoad);

     CaptureGIcrossRoad getCapture(String number);
}
