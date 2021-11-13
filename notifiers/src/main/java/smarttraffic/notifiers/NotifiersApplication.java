package smarttraffic.notifiers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NotifiersApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotifiersApplication.class, args);

    }

}
