package smarttraffic.violations_analyzer_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Vehicle;

import java.util.List;

@Service
public interface VehicleService {

    void saveAll(List<Vehicle> allVehicles);

}
