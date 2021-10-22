package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureIsAmCrossRoad;

import java.util.List;

public interface CaptureIsAmCrossroadRepository extends JpaRepository<CaptureIsAmCrossRoad,Long> {
    CaptureIsAmCrossRoad getByNumber(String number);
    List<CaptureIsAmCrossRoad> getAllByNumber(String number);
}
