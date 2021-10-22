package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.Violation;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

}
