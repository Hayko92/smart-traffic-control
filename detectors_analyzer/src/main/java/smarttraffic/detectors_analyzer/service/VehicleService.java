package smarttraffic.detectors_analyzer.service;

import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Vehicle;

import java.util.Map;

@Service
public interface VehicleService {
    Vehicle getByNumber(String number);

    void save(Vehicle vehicle);

    boolean checkInsurance(Capture capture, Vehicle vehicle);

    boolean checktechinspection(Capture capture, Vehicle vehicle);

    Map<Capture,Integer> checkSpeed(Capture capture);
}
