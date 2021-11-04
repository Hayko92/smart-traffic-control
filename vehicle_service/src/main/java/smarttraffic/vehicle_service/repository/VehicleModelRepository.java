package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.VehicleModel;


public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {

}