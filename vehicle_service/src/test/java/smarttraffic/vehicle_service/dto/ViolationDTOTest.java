package smarttraffic.vehicle_service.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationDTOTest {

    ViolationDTO violationDTO = new ViolationDTO();

    @Test
    void violationDTOTest() {
        String vehiclePlateNumber = "01AM123";
        violationDTO.setNumber(vehiclePlateNumber);
        assertThat(violationDTO.getNumber()).isEqualTo(vehiclePlateNumber);

        violationDTO.setType("INS");
        assertThat(violationDTO.getType()).isEqualTo("INS");
    }
}
