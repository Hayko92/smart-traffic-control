package smarttraffic.detectors_analyzer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.detectors_analyzer.controller.DetectorController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DetectorsAnalyzerApplicationTests {

    @Autowired
    DetectorController detectorController;

    @Test
    void contextLoads() {
        assertThat(detectorController).isNotNull();
    }

}
