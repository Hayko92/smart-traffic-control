package smarttraffic.violation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.violation_service.entity.Violation;

import java.util.List;

public interface ViolationRepository extends JpaRepository<Violation, String> {
    List<Violation> getAllByNumber(String number);

    List<Violation> getAllByOwnerId(Long id);
}
