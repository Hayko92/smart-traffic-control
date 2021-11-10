package smarttraffic.violations_analyzer_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Detector;

import java.util.List;

@Service
public interface DetectorService {

    void saveAll(List<Detector> allVehicles);

    List<Detector> findAll();

}
