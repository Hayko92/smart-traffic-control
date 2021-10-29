package smarttraffic.cameraimitation.util;

import smarttraffic.cameraimitation.dto.DetectorDTO;
import smarttraffic.cameraimitation.entity.Detector;

import java.util.HashMap;
import java.util.Map;

public final class DetectorMapper {
    private DetectorMapper() {
    }

    public static DetectorDTO mapToDetectorDTO(Detector detector) {
        DetectorDTO detectorDTO = new DetectorDTO();
        detectorDTO.setId(detector.getId());
        detectorDTO.setPlace(detector.getPlace());
        detectorDTO.setPreviousDetectorsDistance(previousDetectorsMapper(detector));
        return detectorDTO;
    }

    private static Map<DetectorDTO, Integer> previousDetectorsMapper(Detector detector) {
        Map<DetectorDTO, Integer> prevDetectors = new HashMap<>();
        for (Map.Entry<Detector, Integer> entry : detector.getPreviousDetectorsDistance().entrySet()) {
            prevDetectors.put(mapToDetectorDTO(entry.getKey()), entry.getValue());
        }
        return prevDetectors;
    }
}
