package smarttraffic.vehicle_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.vehicle_service.entity.Owner;
import smarttraffic.vehicle_service.entity.Vehicle;
import smarttraffic.vehicle_service.service.VehicleService;

@RestController
@RequestMapping("api/vehicle-service")
public class VehicleServiceController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/owner/{plateNumber}")
    public Owner sendOwnerByPlateNumber(@PathVariable String plateNumber) {
        Vehicle vehicle = vehicleService.getByNumber(plateNumber);
        return vehicle.getOwner();
    }

    @GetMapping("/{plateNumber}")
    public Vehicle sendVehicleByPlateNumber(@PathVariable String plateNumber) {
        return vehicleService.getByNumber(plateNumber);
    }
    @PostMapping("/set-status-checked")
    public void setStatusCheched(@RequestBody Vehicle vehicle) {
        long id = vehicle.getId();
        Vehicle vehicle1 =vehicleService.getById(id);
        vehicle1.setChecked(true);
        vehicleService.update(vehicle1);
    }
}
