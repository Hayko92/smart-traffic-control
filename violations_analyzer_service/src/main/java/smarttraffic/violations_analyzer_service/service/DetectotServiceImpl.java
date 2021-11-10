package smarttraffic.violations_analyzer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Detector;
import smarttraffic.violations_analyzer_service.repository.DetectorRepository;

import java.util.List;

@Service
public class DetectotServiceImpl implements DetectorService {

    @Autowired
    DetectorRepository detectorRepository;

    @Override
    public void saveAll(List<Detector> alldetectors) {
        detectorRepository.saveAll(alldetectors);
    }

    @Override
    public List<Detector> findAll() {
        return detectorRepository.findAll();
    }

}
