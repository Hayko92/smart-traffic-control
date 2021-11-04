package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.entity.Vehicle;

@Service
public interface VehicleService {
    Vehicle getByNumber(String number);

    Vehicle getByOwnerId(long ownerId);

    void create(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(long id);

    Vehicle getById(long id);

}