package smarttraffic.violation_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.violation_service.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
