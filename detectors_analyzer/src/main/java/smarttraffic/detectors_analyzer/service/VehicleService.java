package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.model.Vehicle;

import java.util.Map;

@Service
public interface VehicleService {

    boolean checkInsurance(CaptureDTO capture, Vehicle vehicle);

    boolean checkTechInspection(CaptureDTO capture, Vehicle vehicle);

    Map<CaptureDTO, Integer> checkSpeed(CaptureDTO capture);
}
