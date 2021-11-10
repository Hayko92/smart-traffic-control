package smarttraffic.violation_service.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import smarttraffic.violation_service.entity.Violation;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ViolationRepositoryTest {

    @Autowired
    private ViolationRepository violationRepository;

    private Violation violation;

    @Test
    void saveViolation() {
        String vehiclePlateNumber = "01AM123";
        violation.setNumber(vehiclePlateNumber);
        long countBeforeSave = violationRepository.count();
        violationRepository.save(violation);
        assertThat(violationRepository.count()).isEqualTo(countBeforeSave + 1);
    }

}
