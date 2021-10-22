package smarttraffic.detectors_analyzer.oldVersion.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureArArCrossRoad;
import smarttraffic.detectors_analyzer.oldVersion.entity.Violation;

@Service
public interface CaptureArArCrossroadService {
     
     void save(CaptureArArCrossRoad captureArArCrossRoad);

     boolean hasValidInsurance(CaptureArArCrossRoad captureArArCrossRoad);

     boolean hasValidTechInspection(CaptureArArCrossRoad captureArArCrossRoad);

     void setStatusChecked(CaptureArArCrossRoad captureArArCrossRoad);

     void createViolation(Violation violation, CaptureArArCrossRoad... captureArArCrossRoad);
     
     boolean hasValidNumber(CaptureArArCrossRoad captureArArCrossRoad);

     void sendNotificationToPatrol(CaptureArArCrossRoad captureArArCrossRoad);

     void checkAndSave(CaptureArArCrossRoad captureArArCrossRoad);

     CaptureArArCrossRoad getCapture(String number);
}
