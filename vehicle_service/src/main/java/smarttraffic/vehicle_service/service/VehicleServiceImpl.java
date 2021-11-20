package smarttraffic.vehicle_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.vehicle_service.dto.OwnerDTO;
import smarttraffic.vehicle_service.dto.VehicleDTO;
import smarttraffic.vehicle_service.dto.ViolationDTO;
import smarttraffic.vehicle_service.entity.Vehicle;
import smarttraffic.vehicle_service.mapper.VehicleMapper;
import smarttraffic.vehicle_service.repository.VehicleRepository;
import smarttraffic.vehicle_service.util.JwtTokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final OwnerService ownerService;
    @Value("${violationService}")
    private String violationServiceUrl;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, OwnerService ownerService) {
        this.vehicleRepository = vehicleRepository;
        this.ownerService = ownerService;
    }

    @Override
    public VehicleDTO getByNumber(String number) {
        Optional<Vehicle> vehicle = vehicleRepository.findByPlateNumber(number);
        return vehicle.map(VehicleMapper::mapToDto).orElse(null);
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

    @Override
    public void setStatusChecked(Long id) {
        VehicleDTO vehicle1 = getById(id);
        vehicle1.setChecked(true);
        update(vehicle1);
    }

    @Override
    public List<ViolationDTO> getViolationsOfVehicle(String authorization, VehicleDTO vehicleDTO) {
        List<ViolationDTO> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = JwtTokenUtil.getHeadersWithToken(authorization);
        HttpEntity entity = new HttpEntity(httpHeaders);
        ResponseEntity<List<ViolationDTO>> violationDTOS = restTemplate.exchange(violationServiceUrl + "/platenumber/" + vehicleDTO.getPlateNumber(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<ViolationDTO>>() {
        });
        if (violationDTOS.hasBody()) {
            for (ViolationDTO o : violationDTOS.getBody()) {

                result.add(o);
            }
        }
        return result;
    }

    @Override
    public List<ViolationDTO> getViolationsOfConcreteUser(String authorization) {
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
