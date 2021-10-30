package smarttraffic.violation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.violation_service.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByPlateNumber(String number);
}
