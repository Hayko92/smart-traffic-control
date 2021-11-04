package smarttraffic.vehicle_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.entity.Vehicle;
import smarttraffic.vehicle_service.repository.VehicleRepository;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;


    @Override
    public Vehicle getByNumber(String number) {
        return vehicleRepository.getByPlateNumber(number);
    }

    @Override
    public Vehicle getByOwnerId(long ownerId) {
        return vehicleRepository.getByOwnerId(ownerId);
    }

    @Override
    public void create(Vehicle vehicle) {
        vehicleRepository.save(vehicle);

    }

    @Override
    public void update(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle getById(long id) {
        return vehicleRepository.getById(id);
    }



}
