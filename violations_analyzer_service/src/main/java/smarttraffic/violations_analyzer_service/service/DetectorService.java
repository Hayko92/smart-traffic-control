package smarttraffic.violations_analyzer_service.service;

import smarttraffic.violations_analyzer_service.model.DetectorDto;

import java.util.List;

public interface DetectorService {
    void saveAll(List<DetectorDto> allVehicles);

}
