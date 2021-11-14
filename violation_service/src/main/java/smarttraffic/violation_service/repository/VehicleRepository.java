package smarttraffic.violation_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smarttraffic.violation_service.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByPlateNumber(String number);

    @Query("update Vehicle set checked=false ")
    void setChekedToFalse();
}
