package smarttraffic.violation_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;
import smarttraffic.violation_service.dto.CaptureDTO;
import smarttraffic.violation_service.dto.ViolationDTO;

import java.util.List;
import java.util.Map;

@Service
public interface ViolationService {
    List<ViolationDTO> getAllViolations();

    long save(ViolationDTO violation);

    List<ViolationDTO> getByNumber(String number);

    void delete(ViolationDTO violation);

    List<ViolationDTO> getAllByNumber(String number);

    List<ViolationDTO> getAllByOwnerID(Long ownerID);

    List<ViolationDTO> findAll();

    void scheduleFixedDelayTask();

    ViolationDTO findById(long id);

    void createSpeedViolation(Map<String, Integer> info, String token) throws JsonProcessingException;

    void createViolation(Map<String, CaptureDTO> body, String token) throws JsonProcessingException;
}
