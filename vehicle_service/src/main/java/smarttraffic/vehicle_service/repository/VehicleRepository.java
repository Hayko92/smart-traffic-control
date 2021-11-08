package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import smarttraffic.vehicle_service.entity.OwnerContact;

import java.util.List;


public interface VehicleRepository extends JpaRepository<OwnerContact, Long> {
    OwnerContact getByPlateNumber(String plateNumber);

    @Query("from OwnerContact as v")
    List<OwnerContact> getAll();
}