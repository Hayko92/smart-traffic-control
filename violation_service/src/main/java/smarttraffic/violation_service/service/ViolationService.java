package smarttraffic.violation_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violation_service.entity.Violation;

import java.util.List;
import java.util.Optional;

@Service
public interface ViolationService {
    List<Violation> getAllViolations();

    void saveViolation(Violation violation);

    Optional<Violation> getViolation(String number);

    void deleteViolation(Violation violation);
}
