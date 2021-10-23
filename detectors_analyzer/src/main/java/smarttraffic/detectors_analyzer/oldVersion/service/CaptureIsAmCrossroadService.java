package smarttraffic.detectors_analyzer.oldVersion.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.oldVersion.controller.Camera;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureIsAmCrossRoad;
import smarttraffic.detectors_analyzer.oldVersion.entity.Violation;

import java.util.Map;

@Service
public interface CaptureIsAmCrossroadService {
     
     void save(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     boolean hasValidInsurance(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     boolean hasValidTechInspection(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     void setStatusChecked(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     void createViolation(Violation violation, CaptureIsAmCrossRoad... captureIsAmCrossRoad);
     
     boolean hasValidNumber(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     void sendNotificationToPatrol(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     void checkAndSave(CaptureIsAmCrossRoad captureIsAmCrossRoad);

     CaptureIsAmCrossRoad getCapture(String number);

    void checkSpeed(Map<Camera, Integer> previousCameras, CaptureIsAmCrossRoad captureIsAmCrossRoad);
}
