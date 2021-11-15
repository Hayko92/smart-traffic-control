package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smarttraffic.vehicle_service.entity.Vehicle;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByPlateNumber(String plateNumber);

    List<Vehicle> findAll();

    @Query("update Vehicle set checked=false ")
    void setChekedToFalse();
}