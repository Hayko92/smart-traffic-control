package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;

import java.util.List;

public interface CaptureIsAmCrossroadRepository extends JpaRepository<CaptureIsAmCrossRoad,Long> {
    CaptureIsAmCrossRoad getByNumber(String number);
    List<CaptureIsAmCrossRoad> getAllByNumber(String number);
}
