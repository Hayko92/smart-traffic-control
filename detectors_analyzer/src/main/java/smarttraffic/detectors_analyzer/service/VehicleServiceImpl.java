package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Vehicle;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;

 import java.time.Instant;
import java.util.Date;

@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    VehicleRepository vehicleRepository;
    @Override
    public Vehicle getByNumber(String number) {
        return vehicleRepository.getByPlateNumber(number);
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public boolean checkInsurance(Capture capture, Vehicle vehicle) {
        Instant date =  capture.getInstant();
        Instant dateOfExpiringInsurance = vehicle.getInsuranceExpiry();
        return date.isBefore(dateOfExpiringInsurance);
    }

    @Override
    public boolean checktechinspection(Capture capture, Vehicle vehicle) {
        Instant date =  capture.getInstant();
        Instant dateOfExpiringTechInspection = vehicle.getTechInspectionExpiry();
        return date.isBefore(dateOfExpiringTechInspection);
    }
}
