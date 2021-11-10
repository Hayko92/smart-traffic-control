package smarttraffic.violation_service.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ViolationCounterTest {

    @Test
    void checkCountSpeedViolationBasePrice() {
        int currentSpeed80 = 80;
        int currentSpeed100 = 100;
        int currentSpeed120 = 120;
        int currentSpeed140 = 140;
        int currentSpeed200 = 200;
        assertThat(ViolationCounter.countSpeedViolationBasePrice(currentSpeed80)).isEqualTo(10000);
        assertThat(ViolationCounter.countSpeedViolationBasePrice(currentSpeed100)).isEqualTo(20000);
        assertThat(ViolationCounter.countSpeedViolationBasePrice(currentSpeed120)).isEqualTo(25000);
        assertThat(ViolationCounter.countSpeedViolationBasePrice(currentSpeed140)).isEqualTo(29000);
        assertThat(ViolationCounter.countSpeedViolationBasePrice(currentSpeed200)).isEqualTo(200000);
    }
}
