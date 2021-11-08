package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smarttraffic.vehicle_service.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle getByPlateNumber(String plateNumber);

    Vehicle getByOwnerId(long ownerId);
}