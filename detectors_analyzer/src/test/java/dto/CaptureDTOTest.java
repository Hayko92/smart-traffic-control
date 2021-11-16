package dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureDTOTest {

    CaptureDTO captureDTO = new CaptureDTO();

    @Test
    void CaptureDTOTest() {
        String vehiclePlateNumber = "01AM123";
        captureDTO.setPlateNumber(vehiclePlateNumber);
        assertThat(captureDTO.getPlateNumber()).isEqualTo(vehiclePlateNumber);

        captureDTO.setPlace("Best_place");
        assertThat(captureDTO.getPlace()).isEqualTo("Best_place");
    }
}
