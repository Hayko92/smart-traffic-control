package smarttraffic.notifiers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.notifiers.controller.NotificationController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotifiersApplicationTests {

    @Autowired
    NotificationController notificationController;

    @Test
    void contextLoads() {
        assertThat(notificationController).isNotNull();
    }

}
