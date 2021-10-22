package smarttraffic.detectors_analyzer.oldVersion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureSebTichCrossRoad;

import java.util.List;

public interface CaptureSebTichCrossroadRepository extends JpaRepository<CaptureSebTichCrossRoad,Long> {
    CaptureSebTichCrossRoad getByNumber(String number);
    List<CaptureSebTichCrossRoad> getAllByNumber(String number);
}
