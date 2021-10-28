package smarttraffic.violation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.violation_service.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByNumber(String number);

    List<Vehicle> getAllByNumber(String number);

}