package smarttraffic.violations_analyzer_service;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
