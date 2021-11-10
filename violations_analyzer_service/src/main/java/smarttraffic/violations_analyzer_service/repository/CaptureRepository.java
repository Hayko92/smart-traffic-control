package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.violations_analyzer_service.model.Capture;

import java.time.Instant;
import java.util.List;

@Repository
public interface CaptureRepository extends MongoRepository<Capture, Long> {

    List<Capture> findAllByInstantBetween(Instant from, Instant to);

    List<Capture> findAllByInstantAfter(Instant from);

    List<Capture> findAllByInstantBefore(Instant to);

}
