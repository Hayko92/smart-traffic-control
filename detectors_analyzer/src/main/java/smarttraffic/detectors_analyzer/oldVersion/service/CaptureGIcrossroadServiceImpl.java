package smarttraffic.detectors_analyzer.oldVersion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.oldVersion.entity.*;
import smarttraffic.detectors_analyzer.oldVersion.repository.CaptureGIcrossroadRepository;
import smarttraffic.detectors_analyzer.oldVersion.repository.VehicleRepository;
import smarttraffic.detectors_analyzer.oldVersion.util.NumberExtractor;

import java.util.Date;

@Service
public class CaptureGIcrossroadServiceImpl implements CaptureGIcrossroadService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    private final CaptureGIcrossroadRepository captureGIcrossroadRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CaptureGIcrossroadServiceImpl(CaptureGIcrossroadRepository captureGIcrossroadRepository, VehicleRepository vehicleRepository) {
        this.captureGIcrossroadRepository = captureGIcrossroadRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(CaptureGIcrossRoad captureGIcrossRoad) {
        captureGIcrossroadRepository.save(captureGIcrossRoad);
    }

    @Override
    public boolean hasValidInsurance(CaptureGIcrossRoad captureGIcrossRoad) {
        Date date = captureGIcrossRoad.getCaptureTime();
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getInsuranceExpiry());
    }

    @Override
    public boolean hasValidTechInspection(CaptureGIcrossRoad captureGIcrossRoad) {
        Date date = captureGIcrossRoad.getCaptureTime();
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getTechInspectionExpiry());
    }

    @Override
    public void setStatusChecked(CaptureGIcrossRoad captureGIcrossRoad) {
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        vehicle.setChecked(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createViolation(Violation insuranceViolation, CaptureGIcrossRoad... captureGIcrossRoad) {
        //fixme
    }

    public String extractNumber(String photoURL) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(photoURL));
        return NumberExtractor.extract(textFromImage);
    }

    public boolean hasValidNumber(CaptureGIcrossRoad captureGIcrossRoad) {
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if(vehicle!=null) captureGIcrossRoad.setNumber(number);
        return vehicle != null;
    }

    @Override
    public void sendNotificationToPatrol(CaptureGIcrossRoad captureGIcrossRoad) {
        //fixme
    }

    @Override
    //todo add place field to capture
    //todo add checking if this car is checked or not
    public void checkAndSave(CaptureGIcrossRoad captureGIcrossRoad) {
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
    public CaptureGIcrossRoad getCapture(String number) {
        return captureGIcrossroadRepository.getAllByNumber(number)
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

}
