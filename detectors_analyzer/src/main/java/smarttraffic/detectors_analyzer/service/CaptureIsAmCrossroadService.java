package smarttraffic.detectors_analyzer.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.controller.Camera;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;
import smarttraffic.detectors_analyzer.entity.Violation;

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

    void checkSpeed(Map<Camera, Double> previousCameras, CaptureIsAmCrossRoad captureIsAmCrossRoad);
}
