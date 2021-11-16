package util;

import org.junit.jupiter.api.Test;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.util.DetectorMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class DetectorMapperTest {

    Detector detector = new Detector();
    DetectorDto detectorDto = new DetectorDto();

    @Test
    void checkMapToDTO() {
        String place = "Best-place";
        detector.setPlace(place);
        detector.setId(12);
        detectorDto = DetectorMapper.mapToDetectorDTO(detector);
        assertThat(detectorDto.getPlace()).isEqualTo(detector.getPlace());
    }
}
