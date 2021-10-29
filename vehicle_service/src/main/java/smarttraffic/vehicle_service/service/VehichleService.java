package smarttraffic.vehicle_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.vehicle_service.entity.Vehicle;

@Service
interface VehicleService {
    Vehicle getByNumber(String number);

    void create (Vehicle vehicle);

    void delete(Vehicle vehicle);

    void update(Vehicle vehicle);


}