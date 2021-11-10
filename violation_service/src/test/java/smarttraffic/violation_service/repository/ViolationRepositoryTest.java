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

    @Test
    void saveViolation() {
        Violation violation = new Violation();
        violation.setNumber("14AR120");
        long countBeforeSave = violationRepository.count();
        violationRepository.save(violation);
        assertThat(violationRepository.count()).isEqualTo(countBeforeSave + 1);
    }

    @Test
    @Transactional
    void getViolation() {
        Violation violation = new Violation();
        violation.setNumber("14AR120");//
        violationRepository.save(violation);//
        assertThat(violationRepository.count()).isGreaterThan(0);
    }

}
