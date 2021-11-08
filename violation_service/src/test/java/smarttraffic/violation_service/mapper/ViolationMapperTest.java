package smarttraffic.violation_service.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationMapperTest {

    @Test
    void checkMapToDTO(){

        Violation violation = new Violation();
        violation.setNumber("test");
        ViolationDTO violationDTO = new ViolationDTO();
        violationDTO.setNumber(violation.getNumber());

        assertThat(violationDTO.getNumber()).isEqualTo(violation.getNumber());

    }
}
