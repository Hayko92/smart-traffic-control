package smarttraffic.vehicle_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.vehicle_service.dto.OwnerDTO;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.service.OwnerService;
import smarttraffic.vehicle_service.service.VehicleService;

@RestController
@RequestMapping("/api/vehicle-service")
public class VehicleServiceController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private OwnerService ownerService;

    @GetMapping("/vehicle/owner/{plateNumber}")
    public OwnerDTO sendOwnerByPlateNumber(@PathVariable String plateNumber) {
        VehicleDTO vehicle = vehicleService.getByNumber(plateNumber);
        if (vehicle != null) return vehicle.getOwner();
        else return null;
    }


    @GetMapping("/{plateNumber}")
    public VehicleDTO sendVehicleByPlateNumber(@PathVariable String plateNumber) {
        return vehicleService.getByNumber(plateNumber);
    }

    @GetMapping("/set-status-checked/{id}")
    public String setStatusCheched(@PathVariable Long id) {
        VehicleDTO vehicle1 = vehicleService.getById(id);
        vehicle1.setChecked(true);
        vehicleService.update(vehicle1);
        return null;
    }

    @GetMapping("/owner/{id}")
    public OwnerDTO sendOwnerByid(@PathVariable String id) {
        OwnerDTO owner = ownerService.getById(Long.parseLong(id));
        return owner;
    }

    @PostMapping("/owner/{id}")
    public long updateOwnerByid(@PathVariable long id, @RequestBody OwnerDTO owner) {
        return ownerService.save(owner);
    }

}
