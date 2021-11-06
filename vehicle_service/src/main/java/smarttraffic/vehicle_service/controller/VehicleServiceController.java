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

    @Value("${violationService}")
    private String violationServiceUrl;
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
    public void setStatusCheched(@PathVariable Long id) {
        VehicleDTO vehicle1 = vehicleService.getById(id);
        vehicle1.setChecked(true);
        vehicleService.update(vehicle1);
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
            result = getViolationsOfVehicle(Authorization, vehicleDTO);
        }
        return result;
    }

    private List<ViolationDTO> getViolationsOfVehicle(String token, VehicleDTO vehicleDTO) {
        List<ViolationDTO> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<List<ViolationDTO>> violationDTOS = restTemplate.exchange(violationServiceUrl + "/platenumber/" + vehicleDTO.getPlateNumber(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<ViolationDTO>>() {
        });
        if (violationDTOS.hasBody()) {
            for (ViolationDTO o : violationDTOS.getBody()) {
                ;
                result.add(o);
            }
        }
        return result;
    }

    //todo
    @GetMapping("/user/violations")
    public List<ViolationDTO> getViolationsOfConcreteUser(@RequestHeader String authorization) {
        List<ViolationDTO> violationDTOS = new ArrayList<>();
        String email = JwtTokenUtil.getLoginFromToken(authorization);
        OwnerDTO ownerDTO = ownerService.getOwnerByMail(email);
        if (ownerDTO != null) {
            Set<VehicleDTO> vehicleDTOSet = ownerDTO.getVehicleDTOSet();
            for (VehicleDTO vehicleDTO : vehicleDTOSet) {
                List<ViolationDTO> violationList = getViolationsOfVehicle(authorization, vehicleDTO);
                violationDTOS.addAll(violationList);
            }
        }
        return violationDTOS;
    }
}
