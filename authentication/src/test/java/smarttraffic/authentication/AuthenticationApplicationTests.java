package smarttraffic.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.authentication.controller.AuthController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AuthenticationApplicationTests {

    @Autowired
    AuthController authController;

    @Test
    void contextLoads() {
        assertThat(authController).isNotNull();
    }

}
