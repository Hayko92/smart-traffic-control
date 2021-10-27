package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.Vehicle;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByPlateNumber(String plateNumber);
}