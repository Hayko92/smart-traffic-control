package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import smarttraffic.detectors_analyzer.entity.*;
import smarttraffic.detectors_analyzer.repository.CaptureArArCrossroadRepository;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;
import smarttraffic.detectors_analyzer.util.NumberExtractor;

import java.util.Date;

public class CaptureArArCrossroadServiceImpl implements CaptureArArCrossroadService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    private final CaptureArArCrossroadRepository captureArArCrossroadRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CaptureArArCrossroadServiceImpl(CaptureArArCrossroadRepository captureArArCrossroadRepository, VehicleRepository vehicleRepository) {
        this.captureArArCrossroadRepository = captureArArCrossroadRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(CaptureArArCrossRoad captureArArCrossRoad) {
        captureArArCrossroadRepository.save(captureArArCrossRoad);
    }

    @Override
    public boolean hasValidInsurance(CaptureArArCrossRoad captureArArCrossRoad) {
        Date date = captureArArCrossRoad.getCaptureTime();
        String photoURL = captureArArCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if(vehicle!=null) captureArArCrossRoad.setNumber(number);
        return date.before(vehicle.getInsuranceExpiry());
    }

    @Override
    public boolean hasValidTechInspection(CaptureArArCrossRoad captureGIcrossRoad) {
        Date date = captureGIcrossRoad.getCaptureTime();
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getTechInspectionExpiry());
    }

    @Override
    public void setStatusChecked(CaptureArArCrossRoad captureGIcrossRoad) {
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        vehicle.setChecked(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createViolation(Violation insuranceViolation, CaptureArArCrossRoad... captureGIcrossRoad) {
        //fixme
    }

    public String extractNumber(String photoURL) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(photoURL));
        return NumberExtractor.extract(textFromImage);
    }

    public boolean hasValidNumber(CaptureArArCrossRoad captureGIcrossRoad) {
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return vehicle != null;
    }

    @Override
    public void sendNotificationToPatrol(CaptureArArCrossRoad captureGIcrossRoad) {
        //fixme
    }

    @Override
    public void checkAndSave(CaptureArArCrossRoad captureGIcrossRoad) {
        if (!hasValidInsurance(captureGIcrossRoad))
            createViolation(new InsuranceViolation(), captureGIcrossRoad);
        if (!hasValidTechInspection(captureGIcrossRoad))
            createViolation(new TechinspectionViolation(), captureGIcrossRoad);
        setStatusChecked(captureGIcrossRoad);
        String number = extractNumber(captureGIcrossRoad.getPhotoURL());
        captureGIcrossRoad.setNumber(number);
        save(captureGIcrossRoad);
    }

    @Override
    public CaptureArArCrossRoad getCapture(String number) {
        return captureArArCrossroadRepository.getAllByNumber(number)
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

}
