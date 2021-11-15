package smarttraffic.violation_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.mapper.ViolationMapper;
import smarttraffic.violation_service.repository.VehicleRepository;
import smarttraffic.violation_service.repository.ViolationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationRepository violationRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<ViolationDTO> getAllViolations() {
        List<Violation> violations = violationRepository.findAll();
        return violations.stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public long save(ViolationDTO violation) {
        Violation violation1 = ViolationMapper.mapToEntity(violation);
        violationRepository.save(violation1);
        return violation1.getId();
    }

    @Override
    public List<ViolationDTO> getByNumber(String number) {
        List<Violation> violation = violationRepository.getAllByNumber(number);
        return violation.stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ViolationDTO violationDTO) {
        Violation violation = ViolationMapper.mapToEntity(violationDTO);
        violationRepository.delete(violation);
    }

    @Override
    public List<ViolationDTO> getAllByNumber(String number) {
        List<Violation> violationList = violationRepository.getAllByNumber(number);
        return violationList
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDTO> getAllByOwnerID(Long ownerID) {
        List<Violation> violationList = violationRepository.getAllByOwnerId(ownerID);
        return violationList
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ViolationDTO> findAll() {
        return violationRepository.findAll()
                .stream()
                .map(ViolationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleFixedDelayTask() {
        vehicleRepository.setChekedToFalse();
    }
}