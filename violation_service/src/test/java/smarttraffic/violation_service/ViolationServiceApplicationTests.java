package smarttraffic.violation_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.controller.ViolationController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ViolationServiceApplicationTests {

    @Autowired
    ViolationController violationController;

    @Autowired(required = false)
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
        assertThat(violationController).isNotNull();
//        assertThat(restTemplate).isNotNull();
    }

}
