package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureGIcrossRoad;

import java.util.List;

public interface CaptureGIcrossroadRepository extends JpaRepository<CaptureGIcrossRoad,Long> {
    CaptureGIcrossRoad getByNumber(String number);
    List<CaptureGIcrossRoad> getAllByNumber(String number);

}
