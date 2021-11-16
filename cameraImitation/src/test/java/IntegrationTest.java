import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.cameraimitation.CameraImitationApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//   Base composite annotation for integration tests.

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = CameraImitationApplication.class)
public @interface IntegrationTest {
}
