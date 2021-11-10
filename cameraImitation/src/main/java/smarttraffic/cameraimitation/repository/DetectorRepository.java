package smarttraffic.cameraimitation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.cameraimitation.entity.Detector;

public interface DetectorRepository extends JpaRepository<Detector, Long> {
    Detector getByPlace(String place);
}
