package dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.detectors_analyzer.dto.DetectorDTO;

import static org.assertj.core.api.Assertions.assertThat;

public class DetectorDTOTest {
    DetectorDTO detectorDTO = new DetectorDTO();

    @Test
    void VehicleDTOTest() {
        String place = "Best_place";
        detectorDTO.setPlace(place);
        assertThat(detectorDTO.getPlace()).isEqualTo(place);

        detectorDTO.setId(123456);
        assertThat(detectorDTO.getId()).isEqualTo(123456);
    }
}