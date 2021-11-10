package smarttraffic.violations_analyzer_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Violation;

import java.time.Instant;
import java.util.List;

@Service
public interface ViolationService {
    void saveAll(List<Violation> violations);

    List<Violation> getViolations(Instant from, Instant to);
}
