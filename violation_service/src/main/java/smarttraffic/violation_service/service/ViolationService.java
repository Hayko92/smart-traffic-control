package smarttraffic.violation_service.service;

import org.springframework.stereotype.Service;
import smarttraffic.violation_service.dto.ViolationDTO;

import java.util.List;

@Service
public interface ViolationService {
    List<ViolationDTO> getAllViolations();

    long save(ViolationDTO violation);

    ViolationDTO getByNumber(String number);

    void delete(ViolationDTO violation);

    List<ViolationDTO> getAllByNumber(String number);

    List<ViolationDTO> getAllByOwnerID(Long ownerID);
}
