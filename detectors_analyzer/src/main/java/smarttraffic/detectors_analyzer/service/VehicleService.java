package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.model.Vehicle;

import java.util.Map;

@Service
public interface VehicleService {

    boolean checkInsurance(Capture capture, Vehicle vehicle);

    boolean checkTechInspection(Capture capture, Vehicle vehicle);

    Map<Capture, Integer> checkSpeed(Capture capture);
}
