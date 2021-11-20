package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.dto.ViolationDTO;

import java.util.List;

@Service
public interface VehicleService {
    VehicleDTO getByNumber(String number);

    void create(VehicleDTO vehicle);

    void delete(VehicleDTO vehicle);

    void update(VehicleDTO vehicle);

    void delete(long id);

    VehicleDTO getById(long id);

    void save(VehicleDTO vehicle1);

    List<VehicleDTO> getAllVehicles();

    void scheduleFixedDelayTask();

    void setStatusChecked(Long id);

    List<ViolationDTO> getViolationsOfVehicle(String authorization, VehicleDTO vehicleDTO);

    List<ViolationDTO> getViolationsOfConcreteUser(String authorization);
}