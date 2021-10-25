package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.Detector;
import smarttraffic.detectors_analyzer.entity.Vehicle;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;

import java.time.Instant;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService{
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CaptureService captureService;

    @Override
    public Vehicle getByNumber(String number) {
        return vehicleRepository.getByPlateNumber(number);
    }

    @Override
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public boolean checkInsurance(Capture capture, Vehicle vehicle) {
        Instant date =  capture.getInstant();
        Instant dateOfExpiringInsurance = vehicle.getInsuranceExpiry();
        return date.isBefore(dateOfExpiringInsurance);
    }

    @Override
    public boolean checktechinspection(Capture capture, Vehicle vehicle) {
        Instant date =  capture.getInstant();
        Instant dateOfExpiringTechInspection = vehicle.getTechInspectionExpiry();
        return date.isBefore(dateOfExpiringTechInspection);
    }

    @Override
    public Capture checkSpeed(Capture capture) {
        Map<Detector, Integer> previousDet = getPreviousDetectors(capture);
        Capture prev = null;
        if(previousDet!=null) {
            for(Map.Entry<Detector, Integer> prevDet: previousDet.entrySet()) {
                int distance =prevDet.getValue();
                //todo
                Capture captureFromPreviousDetector = captureService.getByPlace(capture.getPlace());
                long secondsFrom =captureFromPreviousDetector.getInstant().getEpochSecond();
                long secondsTo =capture.getInstant().getEpochSecond();
                long duration = secondsTo-secondsFrom;
                int speed = (int) ((distance/duration)*3.6);
                if(speed>70)  {
                    return captureFromPreviousDetector;
                }
            }
        }
        return null;
    }

    private Map<Detector, Integer> getPreviousDetectors(Capture capture) {
        RestTemplate restTemplate =new RestTemplate();
        String place = capture.getPlace();
        Detector detector = restTemplate.getForObject("http://127.0.0.1:8080/api/camera-imitation-service/"+place,Detector.class);
        assert detector != null;
        return detector.getPreviousDetectorsDistance();
    }
}
