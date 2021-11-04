package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.dto.VehicleDTO;

import java.util.Map;

@Service
public interface VehicleService {

    boolean checkInsurance(CaptureDTO capture, VehicleDTO vehicleDTO);

    boolean checkTechInspection(CaptureDTO capture, VehicleDTO vehicleDTO);

    Map<CaptureDTO, Integer> checkSpeed(CaptureDTO capture, String token);
}
