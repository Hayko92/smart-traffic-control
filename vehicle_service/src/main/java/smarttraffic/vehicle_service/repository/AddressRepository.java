package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
