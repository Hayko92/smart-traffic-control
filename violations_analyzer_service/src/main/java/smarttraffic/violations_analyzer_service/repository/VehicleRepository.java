package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.violations_analyzer_service.model.VehicleDTO;
@Repository
public interface VehicleRepository extends MongoRepository<VehicleDTO, Long> {

}
