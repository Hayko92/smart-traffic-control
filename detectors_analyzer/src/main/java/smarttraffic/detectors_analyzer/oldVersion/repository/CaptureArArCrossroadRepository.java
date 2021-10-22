package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureArArCrossRoad;

import java.util.List;

public interface CaptureArArCrossroadRepository extends JpaRepository<CaptureArArCrossRoad,Long> {
    CaptureArArCrossRoad getByNumber(String number);
    List<CaptureArArCrossRoad> getAllByNumber(String number);

}
