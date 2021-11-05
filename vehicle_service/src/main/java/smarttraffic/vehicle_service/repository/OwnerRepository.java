package smarttraffic.vehicle_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.vehicle_service.entity.Owner;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner getById(long id);

    Owner getByOwnerContact_EmailAddress(String email);

}