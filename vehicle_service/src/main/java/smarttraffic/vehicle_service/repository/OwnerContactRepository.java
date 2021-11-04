package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.OwnerContact;


public interface OwnerContactRepository extends JpaRepository<OwnerContact, Long> {

}