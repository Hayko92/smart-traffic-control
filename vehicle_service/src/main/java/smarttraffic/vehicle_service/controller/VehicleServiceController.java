package smarttraffic.vehicle_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import smarttraffic.vehicle_service.dto.OwnerDTO;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.dto.ViolationDTO;
import smarttraffic.vehicle_service.service.OwnerService;
import smarttraffic.vehicle_service.service.VehicleService;
import smarttraffic.vehicle_service.util.JwtTokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/vehicle-service")
public class VehicleServiceController {

    private final VehicleService vehicleService;
    private final OwnerService ownerService;

    @Autowired
    public VehicleServiceController(VehicleService vehicleService, OwnerService ownerService) {
        this.vehicleService = vehicleService;
        this.ownerService = ownerService;
    }

    @GetMapping("/vehicle/owner/{plateNumber}")
    public OwnerDTO sendOwnerByPlateNumber(@PathVariable String plateNumber) {
        VehicleDTO vehicle = vehicleService.getByNumber(plateNumber);
        if (vehicle != null) return vehicle.getOwner();
        else return null;
    }

    @GetMapping("/all")
    public List<VehicleDTO> sendAllVehicled(@RequestHeader(name = "AUTHORIZATION") String token) {
        return vehicleService.getAllVehicles();

    }

    @GetMapping("/{plateNumber}")
    public VehicleDTO sendVehicleByPlateNumber(@PathVariable String plateNumber) {
        return vehicleService.getByNumber(plateNumber);
    }

    @GetMapping("/set-status-checked/{id}")
    public void setStatusCheched(@PathVariable Long id) {
       vehicleService.setStatusChecked(id);
    }

    @GetMapping("/owner/{id}")
    public OwnerDTO sendOwnerByid(@PathVariable String id) {
        return ownerService.getById(Long.parseLong(id));
    }

    @PutMapping("/owner/{id}")
    public void updateOwner(@PathVariable long id, @RequestBody OwnerDTO ownerDTO) {
        ownerService.save(ownerDTO);
    }

    @PostMapping("/owner/{id}")
    public long updateOwnerByid(@PathVariable long id, @RequestBody OwnerDTO owner) {
        return ownerService.save(owner);
    }

    @GetMapping("/violations")
    public List<ViolationDTO> getViolations(@RequestParam String plateNumber, @RequestParam String vinNumber, @RequestHeader String Authorization) {
        VehicleDTO vehicleDTO = vehicleService.getByNumber(plateNumber);
        List<ViolationDTO> result = new ArrayList<>();
        if (vehicleDTO.getVinNumber().equals(vinNumber)) {
            result = vehicleService.getViolationsOfVehicle(Authorization, vehicleDTO);
        }
        return result;
    }

    @GetMapping("/user/violations")
    public List<ViolationDTO> getViolationsOfConcreteUser(@RequestHeader String authorization) {
       return vehicleService.getViolationsOfConcreteUser(authorization);
    }

}
