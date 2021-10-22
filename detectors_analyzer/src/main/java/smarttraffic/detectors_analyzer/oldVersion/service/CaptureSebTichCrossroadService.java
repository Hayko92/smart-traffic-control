package smarttraffic.detectors_analyzer.oldVersion.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureSebTichCrossRoad;
import smarttraffic.detectors_analyzer.oldVersion.entity.Violation;

@Service
public interface CaptureSebTichCrossroadService {
     
     void save(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     boolean hasValidInsurance(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     boolean hasValidTechInspection(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     void setStatusChecked(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     void createViolation(Violation violation, CaptureSebTichCrossRoad... captureSebTichCrossRoad);
     
     boolean hasValidNumber(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     void sendNotificationToPatrol(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     void checkAndSave(CaptureSebTichCrossRoad captureSebTichCrossRoad);

     CaptureSebTichCrossRoad getCapture(String number);
}
