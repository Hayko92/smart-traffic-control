package smarttraffic.cameraimitation.util;

import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.entity.Detector;

public final class DetectorMapper {
    private DetectorMapper() {
    }

    public static DetectorDto mapToDetectorDTO(Detector detector) {
        DetectorDto detectorDTO = new DetectorDto();
        if (detector != null) {
            detectorDTO.setId(detector.getId());
            detectorDTO.setPlace(detector.getPlace());
            detectorDTO.setPreviousDetectorsDistance(detector.getPreviousDetectorsDistance());
        }
        return detectorDTO;
    }
}
