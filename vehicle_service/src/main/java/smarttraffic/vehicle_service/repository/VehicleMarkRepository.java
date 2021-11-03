package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.VehicleMark;


public interface VehicleMarkRepository extends JpaRepository<VehicleMark, Integer> {

}