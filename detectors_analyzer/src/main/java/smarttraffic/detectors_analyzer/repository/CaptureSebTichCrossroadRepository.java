package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.CaptureArArCrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureSebTichCrossRoad;

import java.util.List;

public interface CaptureSebTichCrossroadRepository extends JpaRepository<CaptureSebTichCrossRoad,Long> {
    CaptureSebTichCrossRoad getByNumber(String number);
    List<CaptureSebTichCrossRoad> getAllByNumber(String number);
}
