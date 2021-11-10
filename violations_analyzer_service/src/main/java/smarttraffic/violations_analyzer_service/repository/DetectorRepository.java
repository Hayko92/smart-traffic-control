package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.violations_analyzer_service.model.Detector;

@Repository
public interface DetectorRepository extends MongoRepository<Detector, Long> {

}
