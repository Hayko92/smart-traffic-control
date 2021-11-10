package smarttraffic.violation_service.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationMapperTest {

    Violation violation;
    ViolationDTO violationDTO;

    @Test
    void checkMapToDTO() {
        String vehiclePlateNumber = "01AM123";
        violation.setNumber(vehiclePlateNumber);
        violationDTO.setNumber(violation.getNumber());
        assertThat(violationDTO.getNumber()).isEqualTo(violation.getNumber());

    }
}
