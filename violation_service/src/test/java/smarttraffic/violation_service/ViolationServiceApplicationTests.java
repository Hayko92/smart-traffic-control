package smarttraffic.violation_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violation_service.controller.ViolationController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ViolationServiceApplicationTests {

    @Autowired
    ViolationController violationController;

    @Test
    void contextLoads() {
        assertThat(violationController).isNotNull();
    }
}