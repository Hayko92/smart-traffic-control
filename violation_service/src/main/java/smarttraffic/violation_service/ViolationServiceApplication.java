package smarttraffic.violation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ViolationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViolationServiceApplication.class, args);
    }

}