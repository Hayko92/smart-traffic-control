package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
    //write method to get owner my vehicle
    //TODO
}
