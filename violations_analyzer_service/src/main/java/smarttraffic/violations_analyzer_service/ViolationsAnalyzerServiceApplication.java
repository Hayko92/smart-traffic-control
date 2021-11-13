package smarttraffic.violations_analyzer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ViolationsAnalyzerServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ViolationsAnalyzerServiceApplication.class, args);
    }

}
