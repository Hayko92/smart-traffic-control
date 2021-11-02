package smarttraffic.vehicle_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.vehicle_service.entity.Owner;
import smarttraffic.vehicle_service.entity.Vehicle;
import smarttraffic.vehicle_service.service.OwnerService;
import smarttraffic.vehicle_service.service.VehicleService;
import smarttraffic.vehicle_service.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/vehicle-service")
public class VehicleServiceController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/vehicle/owner/{plateNumber}")
    public Owner sendOwnerByPlateNumber(@PathVariable String plateNumber, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            Vehicle vehicle = vehicleService.getByNumber(plateNumber);
            return vehicle.getOwner();
        }
        return null;
    }

    @GetMapping("/{plateNumber}")
    public Vehicle sendVehicleByPlateNumber(@PathVariable String plateNumber, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token))
            return vehicleService.getByNumber(plateNumber);
        else return null;
    }

    @GetMapping("/set-status-checked/{id}")
    public String setStatusCheched(@PathVariable Long id, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            Vehicle vehicle1 = vehicleService.getById(id);
            vehicle1.setChecked(true);
            vehicleService.update(vehicle1);
        }
        return null;
    }

    @GetMapping("/owner/{id}")
    public Owner sendOwnerByid(@PathVariable String id, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token)) {
            Owner owner = ownerService.getById(Long.parseLong(id));
            return owner;
        }
        return null;
    }

    @PostMapping("/owner/{id}")
    public Owner updateOwnerByid(@PathVariable long id, @RequestBody Owner owner, @RequestHeader(name = "AUTHORIZATION") String token) {
        if (jwtTokenUtil.checkTokenValidation(token))
            return ownerService.save(owner);
        else return null;
    }
}
