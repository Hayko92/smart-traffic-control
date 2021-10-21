package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByNumber(String number);
}
