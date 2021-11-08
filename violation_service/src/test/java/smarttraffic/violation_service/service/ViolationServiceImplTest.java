package smarttraffic.violation_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import smarttraffic.violation_service.entity.Owner;
import smarttraffic.violation_service.entity.Vehicle;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.repository.ViolationRepository;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationServiceImplTest {

    @Autowired
    ViolationServiceImpl violationServiceImpl;

    @Autowired
    private ViolationRepository violationRepository;

    @Test
    void checkGetByNumber(){
        Violation violation = new Violation();
        violation.setNumber("01AM123");
        violation.setType("INS");
        violation.setId(1234567L);
        violation.setCreationDate(Instant.now());
        violation.setPhotoUrl1("testUrl1");
        violation.setPlace("Arshakunyac");
        violation.setPrice(20000);
        if((violationServiceImpl.getByNumber("01AM123")) == null){
            violationRepository.save(violation);
            violationServiceImpl.getByNumber("01AM123");
        }
        assertThat(violationServiceImpl.getByNumber("01AM123")).isNotNull();
    }
}