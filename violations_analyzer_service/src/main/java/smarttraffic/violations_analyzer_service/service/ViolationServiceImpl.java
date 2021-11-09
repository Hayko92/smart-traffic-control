package smarttraffic.violations_analyzer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Violation;
import smarttraffic.violations_analyzer_service.repository.ViolationRepository;

import java.time.Instant;
import java.util.List;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationRepository violationRepository;

    @Override
    public void saveAll(List<Violation> violations) {
        violationRepository.saveAll(violations);
    }

    @Override
    public List<Violation> getViolations(Instant from, Instant to) {
        if (from == null) {
            if (to == null) return violationRepository.findAll();
            else return violationRepository.findAllByCreationDateBefore(to);
        } else {
            if (to == null) return violationRepository.findAllByCreationDateAfter(from);
            else return violationRepository.findAllByCreationDateBetween(from, to);
        }
    }
}