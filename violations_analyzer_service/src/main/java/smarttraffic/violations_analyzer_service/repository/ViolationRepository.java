package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.violations_analyzer_service.model.Violation;

import java.time.Instant;
import java.util.List;

@Repository
public interface ViolationRepository extends MongoRepository<Violation, Long> {
    List<Violation> findAllByCreationDateBetween(Instant from, Instant to);

    List<Violation> findAllByCreationDateAfter(Instant from);

    List<Violation> findAllByCreationDateBefore(Instant to);
}
