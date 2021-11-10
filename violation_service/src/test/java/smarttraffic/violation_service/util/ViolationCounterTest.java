package smarttraffic.violation_service.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationCounterTest {

    ViolationCounter violationCounter;

    @Test
    void checkCountSpeedViolationBasePrice() {

        assertThat(ViolationCounter.countSpeedViolationBasePrice(80)).isEqualTo(10000);

        assertThat(violationCounter.countSpeedViolationBasePrice(100)).isEqualTo(02000);

        assertThat(violationCounter.countSpeedViolationBasePrice(120)).isEqualTo(25000);

        assertThat(violationCounter.countSpeedViolationBasePrice(140)).isEqualTo(29000);

        assertThat(violationCounter.countSpeedViolationBasePrice(200)).isEqualTo(200000);
    }
}
