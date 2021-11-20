package util;

import org.junit.jupiter.api.Test;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.util.CaptureMapper;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureMapperTest {

    Capture capture = new Capture();
    CaptureDTO captureDTO = new CaptureDTO();

    @Test
    void checkMapToDTO() {
        String vehiclePlateNumber = "01AM123";
        capture.setPlateNumber(vehiclePlateNumber);
        capture.setPhotoUrl("INS");
        capture.setPlace("BestPlace");
        capture.setInstant(Instant.now());
        captureDTO = CaptureMapper.mapToDto(capture);
        assertThat(captureDTO.getPlateNumber()).isEqualTo(capture.getPlateNumber());
    }
}
