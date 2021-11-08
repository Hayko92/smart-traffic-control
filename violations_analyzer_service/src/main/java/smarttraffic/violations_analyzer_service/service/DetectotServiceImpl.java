package smarttraffic.violations_analyzer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import smarttraffic.violations_analyzer_service.model.DetectorDto;
import smarttraffic.violations_analyzer_service.repository.DetectorRepository;

import java.util.List;

public class DetectotServiceImpl implements DetectorService{
    @Autowired
    DetectorRepository detectorRepository;

    @Override
    public void saveAll(List<DetectorDto> alldetectors) {
        detectorRepository.saveAll(alldetectors);
    }
}
