package smarttraffic.violations_analyzer_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.VehicleDTO;

import java.util.List;

@Service
public interface VehicleService {
    void saveAll(List<VehicleDTO> allVehicles);
}
