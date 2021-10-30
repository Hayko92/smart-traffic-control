package smarttraffic.violation_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violation_service.entity.Violation;

import java.util.List;
import java.util.Optional;

@Service
public interface ViolationService {
    List<Violation> getAllViolations();

    void save(Violation violation);

    Optional<Violation> getByNumber(String number);

    void delete(Violation violation);

    List<Violation> getAllByNumber(String number);

    List<Violation> getAllByOwnerID(Long ownerID);
}
