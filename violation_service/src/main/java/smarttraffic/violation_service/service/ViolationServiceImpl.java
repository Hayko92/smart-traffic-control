package smarttraffic.violation_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violation_service.dto.ViolationDTO;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.repository.ViolationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationRepository violationRepository;

    @Override
    public List<Violation> getAllViolations() {
        return violationRepository.findAll();
    }

    @Override
    public void save(Violation violation) {
        violationRepository.save(violation);
    }

    @Override
    public Optional<Violation> getByNumber(String number) {
        return violationRepository.findById(number);
    }

    @Override
    public void delete(Violation violation) {
        violationRepository.delete(violation);
    }

    @Override
    public List<Violation> getAllByNumber(String number) {
        return violationRepository.getAllByNumber(number);
    }

    @Override
    public List<Violation> getAllByOwnerID(Long ownerID) {
        return violationRepository.getAllByOwnerId(ownerID);
    }
}