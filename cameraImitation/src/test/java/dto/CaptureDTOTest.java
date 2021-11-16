package dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import smarttraffic.cameraimitation.dto.CaptureDto;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureDTOTest {

    @Autowired
    CaptureDto captureDTO = new CaptureDto();

    @Test
    void CaptureDTOTest() {
        String vehiclePlateNumber = "01AM123";
        captureDTO.setPlateNumber(vehiclePlateNumber);
        assertThat(captureDTO.getPlateNumber()).isEqualTo(vehiclePlateNumber);

        captureDTO.setPlace("Best_place");
        assertThat(captureDTO.getPlace()).isEqualTo("Best_place");
    }
}
