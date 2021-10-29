package smarttraffic.cameraimitation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.cameraimitation.entity.Capture;

public interface CaptureRepository extends JpaRepository<Capture, Integer> {
}
