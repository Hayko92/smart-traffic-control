package smarttraffic.notifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NotifiersApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotifiersApplication.class, args);
    }

}
