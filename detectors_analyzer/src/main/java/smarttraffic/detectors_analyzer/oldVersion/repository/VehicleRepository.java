package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByNumber(String number);

}
