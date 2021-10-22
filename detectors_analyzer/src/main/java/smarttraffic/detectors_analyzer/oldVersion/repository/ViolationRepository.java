package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.Violation;

public interface ViolationRepository extends JpaRepository<Violation, Long> {

}
