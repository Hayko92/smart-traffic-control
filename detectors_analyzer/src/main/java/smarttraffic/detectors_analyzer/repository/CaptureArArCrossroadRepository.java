package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.CaptureArArCrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;

import java.util.List;

public interface CaptureArArCrossroadRepository extends JpaRepository<CaptureArArCrossRoad,Long> {
    CaptureArArCrossRoad getByNumber(String number);
    List<CaptureArArCrossRoad> getAllByNumber(String number);

}
