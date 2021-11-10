package smarttraffic.violation_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violation_service.dto.ViolationDTO;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationDTOTest {

    ViolationDTO violationDTO = new ViolationDTO();

    @Test
    void violationDTOTest(){
        violationDTO.setNumber("01AM911");
        assertThat(violationDTO.getNumber()).isEqualTo("01AM911");

        violationDTO.setType("INS");
        assertThat(violationDTO.getType()).isEqualTo("INS");
    }
}
