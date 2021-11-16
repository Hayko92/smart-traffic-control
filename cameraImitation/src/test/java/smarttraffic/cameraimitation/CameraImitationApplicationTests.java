package smarttraffic.cameraimitation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.cameraimitation.controller.StartService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CameraImitationApplicationTests {

    @Autowired
    StartService startService;

    @Test
    void contextLoads() {
        assertThat(startService).isNotNull();

    }
}