package smarttraffic.violation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.violation_service.entity.Violation;

public interface ViolationRepository extends JpaRepository<Violation, String> {

}
