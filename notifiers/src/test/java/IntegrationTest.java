import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.notifiers.NotifiersApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//   Base composite annotation for integration tests.

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = NotifiersApplication.class)
public @interface IntegrationTest {
}
