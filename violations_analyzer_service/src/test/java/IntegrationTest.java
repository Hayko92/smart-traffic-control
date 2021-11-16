import org.springframework.boot.test.context.SpringBootTest;
import smarttraffic.violations_analyzer_service.ViolationsAnalyzerServiceApplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//   Base composite annotation for integration tests.

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = ViolationsAnalyzerServiceApplication.class)
public @interface IntegrationTest {
}
