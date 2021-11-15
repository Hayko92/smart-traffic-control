package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smarttraffic.vehicle_service.entity.VehicleMark;


public interface VehicleMarkRepository extends JpaRepository<VehicleMark, Integer> {
    @Query("update Vehicle set checked=false ")
    void setChekedToFalse();
}