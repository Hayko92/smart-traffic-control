package smarttraffic.violations_analyzer_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.violations_analyzer_service.model.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, Long> {

}
