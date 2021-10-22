package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;

import java.util.List;

public interface CaptureGIcrossroadRepository extends JpaRepository<CaptureGIcrossRoad,Long> {
    CaptureGIcrossRoad getByNumber(String number);
    List<CaptureGIcrossRoad> getAllByNumber(String number);

}
