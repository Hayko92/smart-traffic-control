package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import smarttraffic.violations_analyzer_service.model.VehicleDTO;

public interface VehicleRepository extends MongoRepository<VehicleDTO,Long> {
}
