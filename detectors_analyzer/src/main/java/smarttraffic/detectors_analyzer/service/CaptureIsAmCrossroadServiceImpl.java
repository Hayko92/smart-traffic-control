package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.controller.Camera;
import smarttraffic.detectors_analyzer.entity.*;
import smarttraffic.detectors_analyzer.repository.CaptureIsAmCrossroadRepository;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;
import smarttraffic.detectors_analyzer.util.NumberExtractor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Service
public class CaptureIsAmCrossroadServiceImpl implements CaptureIsAmCrossroadService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    private final CaptureIsAmCrossroadRepository captureIsAmCrossroadRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CaptureIsAmCrossroadServiceImpl(CaptureIsAmCrossroadRepository captureIsAmCrossroadRepository, VehicleRepository vehicleRepository) {
        this.captureIsAmCrossroadRepository = captureIsAmCrossroadRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        captureIsAmCrossroadRepository.save(captureIsAmCrossRoad);
    }

    @Override
    public boolean hasValidInsurance(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        Date date = captureIsAmCrossRoad.getCaptureTime();
        String photoURL = captureIsAmCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if (vehicle != null) captureIsAmCrossRoad.setNumber(number);
        return date.before(vehicle.getInsuranceExpiry());
    }

    @Override
    public boolean hasValidTechInspection(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        Date date = captureIsAmCrossRoad.getCaptureTime();
        String photoURL = captureIsAmCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getTechInspectionExpiry());
    }

    @Override
    public void setStatusChecked(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        String photoURL = captureIsAmCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        vehicle.setChecked(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createViolation(Violation insuranceViolation, CaptureIsAmCrossRoad... captureIsAmCrossRoad) {
        //fixme
    }

    public String extractNumber(String photoURL) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(photoURL));
        return NumberExtractor.extract(textFromImage);
    }

    public boolean hasValidNumber(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        String photoURL = captureIsAmCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if(vehicle!=null) captureIsAmCrossRoad.setNumber(number);
        return vehicle != null;
    }

    @Override
    public void sendNotificationToPatrol(CaptureIsAmCrossRoad captureSebTichCrossRoad) {
        //fixme
    }

    @Override
    public void checkAndSave(CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        if (!hasValidInsurance(captureIsAmCrossRoad))
            createViolation(new InsuranceViolation(), captureIsAmCrossRoad);
        if (!hasValidTechInspection(captureIsAmCrossRoad))
            createViolation(new TechinspectionViolation(), captureIsAmCrossRoad);
        setStatusChecked(captureIsAmCrossRoad);
        String number = extractNumber(captureIsAmCrossRoad.getPhotoURL());
        captureIsAmCrossRoad.setNumber(number);
        save(captureIsAmCrossRoad);
    }

    @Override
    public CaptureIsAmCrossRoad getCapture(String number) {
        CaptureIsAmCrossRoad captureIsAmCrossRoad = captureIsAmCrossroadRepository.getAllByNumber(number)
                .stream().
                reduce((first, second) -> second)
                .orElse(null);
        return captureIsAmCrossRoad;
    }

    @Override
    public void checkSpeed(Map<Camera, Integer> previousCameras, CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        Timestamp timestampEnd = captureIsAmCrossRoad.getCaptureTime();
        for (Map.Entry<Camera, Integer> entry : previousCameras.entrySet()) {
            int maxValidTimeInSeconds = (int) (entry.getValue() / 19.44);
            Capture capture = entry.getKey().getCapture(captureIsAmCrossRoad.getNumber());
            if (capture != null) {
                Timestamp timestampStart = capture.getCaptureTime();
                long duration = (timestampEnd.getTime() - timestampStart.getTime()) / 1000;
                if (duration < maxValidTimeInSeconds)
                    createViolation(new SpeedViolation(), captureIsAmCrossRoad, (CaptureIsAmCrossRoad) capture);
                break;
            }
        }
    }

}
