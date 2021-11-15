package smarttraffic.vehicle_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.entity.Vehicle;
import smarttraffic.vehicle_service.mapper.VehicleMapper;
import smarttraffic.vehicle_service.repository.VehicleRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public VehicleDTO getByNumber(String number) {
        Vehicle vehicle = vehicleRepository.getByPlateNumber(number);
        if (vehicle != null) return VehicleMapper.mapToDto(vehicle);
        else return null;
    }

    @Override
    public void create(VehicleDTO vehicle) {
        Vehicle vehicle1 = VehicleMapper.mapToEntity(vehicle);
        vehicleRepository.save(vehicle1);
    }

    @Override
    public void delete(VehicleDTO vehicle) {
        Vehicle vehicleEn = VehicleMapper.mapToEntity(vehicle);
        vehicleRepository.delete(vehicleEn);
    }

    @Override
    public void update(VehicleDTO vehicle) {
        Vehicle vehicleEn = VehicleMapper.mapToEntity(vehicle);
        vehicleRepository.save(vehicleEn);
    }

    @Override
    public void delete(long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public VehicleDTO getById(long id) {
        Vehicle vehicle = vehicleRepository.getById(id);
        VehicleDTO vehicleDTO;
        vehicleDTO = VehicleMapper.mapToDto(vehicle);
        return vehicleDTO;
    }

    @Override
    public void save(VehicleDTO vehicle1) {
        Vehicle vehicle = VehicleMapper.mapToEntity(vehicle1);
        vehicleRepository.save(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles
                .stream()
                .map(VehicleMapper::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleFixedDelayTask() {
        vehicleRepository.setChekedToFalse();
    }
}
