package dto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import smarttraffic.cameraimitation.dto.DetectorDto;

import static org.assertj.core.api.Assertions.assertThat;

public class DetectorDTOTest {

    @Autowired
    DetectorDto detectorDTO = new DetectorDto();

    @Test
    void DetectorDTOTest() {
        String place = "Best_place";
        detectorDTO.setPlace(place);
        assertThat(detectorDTO.getPlace()).isEqualTo(place);

        detectorDTO.setId(123456);
        assertThat(detectorDTO.getId()).isEqualTo(123456);
    }
}