package smarttraffic.violations_analyzer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Vehicle;
import smarttraffic.violations_analyzer_service.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public void saveAll(List<Vehicle> allVehicles) {
        vehicleRepository.saveAll(allVehicles);
    }

}
