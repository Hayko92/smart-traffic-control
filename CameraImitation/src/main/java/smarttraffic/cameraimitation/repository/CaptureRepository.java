package smarttraffic.cameraimitation.repository;

import smarttraffic.cameraimitation.entity.Capture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptureRepository extends JpaRepository<Capture, Integer> {
}
