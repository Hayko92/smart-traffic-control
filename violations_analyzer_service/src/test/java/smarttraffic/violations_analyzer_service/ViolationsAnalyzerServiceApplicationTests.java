package smarttraffic.violations_analyzer_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violations_analyzer_service.controller.ViolationsAnalyzerController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ViolationsAnalyzerServiceApplicationTests {

    @Autowired
    ViolationsAnalyzerController violationsAnalyzerController;

    @Test
    void contextLoads() {
        assertThat(violationsAnalyzerController).isNotNull();
    }

}