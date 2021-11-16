package dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import smarttraffic.notifiers.dto.CaptureDto;

import java.time.Instant;


public class CaptureDTOTest {

    CaptureDto captureDto = new CaptureDto();

    @Test
    void violationDTOTest() {
        Instant instant = Instant.now();
        String vehiclePlateNumber = "01AM123";
        captureDto.setPlateNumber(vehiclePlateNumber);
        captureDto.setPhotoUrl("https://PhotoUrl");
        captureDto.setInstant(instant);
        assertThat(captureDto.getPlateNumber()).isEqualTo(vehiclePlateNumber);

        captureDto.setPlace("Best_place");
        assertThat(captureDto.getPlace()).isEqualTo("Best_place");
    }
}
