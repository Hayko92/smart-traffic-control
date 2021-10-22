package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    //write method to get owner my vehicle
    //TODO
}
