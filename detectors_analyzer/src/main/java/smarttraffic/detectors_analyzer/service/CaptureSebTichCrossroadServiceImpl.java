package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.*;
import smarttraffic.detectors_analyzer.repository.CaptureSebTichCrossroadRepository;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;
import smarttraffic.detectors_analyzer.util.NumberExtractor;

import java.util.Date;

@Service
public class CaptureSebTichCrossroadServiceImpl implements CaptureSebTichCrossroadService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    private final CaptureSebTichCrossroadRepository captureSebTichCrossroadRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CaptureSebTichCrossroadServiceImpl(CaptureSebTichCrossroadRepository captureSebTichCrossroadRepository, VehicleRepository vehicleRepository) {
        this.captureSebTichCrossroadRepository = captureSebTichCrossroadRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        captureSebTichCrossroadRepository.save(captureSebTichCrossRoad);
    }

    @Override
    public boolean hasValidInsurance(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        Date date = captureSebTichCrossRoad.getCaptureTime();
        String photoURL = captureSebTichCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if(vehicle!=null) captureSebTichCrossRoad.setNumber(number);
        return date.before(vehicle.getInsuranceExpiry());
    }

    @Override
    public boolean hasValidTechInspection(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        Date date = captureSebTichCrossRoad.getCaptureTime();
        String photoURL = captureSebTichCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getTechInspectionExpiry());
    }

    @Override
    public void setStatusChecked(CaptureSebTichCrossRoad captureGIcrossRoad) {
        String photoURL = captureGIcrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        vehicle.setChecked(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createViolation(Violation insuranceViolation, CaptureSebTichCrossRoad... captureSebTichCrossRoads) {
        //fixme
    }

    public String extractNumber(String photoURL) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(photoURL));
        return NumberExtractor.extract(textFromImage);
    }

    public boolean hasValidNumber(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        String photoURL = captureSebTichCrossRoad.getPhotoURL();
        String number = extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        if(vehicle!=null) captureSebTichCrossRoad.setNumber(number);
        return vehicle != null;
    }

    @Override
    public void sendNotificationToPatrol(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        //fixme
    }

    @Override
    public void checkAndSave(CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        if (!hasValidInsurance(captureSebTichCrossRoad))
            createViolation(new InsuranceViolation(), captureSebTichCrossRoad);
        if (!hasValidTechInspection(captureSebTichCrossRoad))
            createViolation(new TechinspectionViolation(), captureSebTichCrossRoad);
        setStatusChecked(captureSebTichCrossRoad);
        String number = extractNumber(captureSebTichCrossRoad.getPhotoURL());
        captureSebTichCrossRoad.setNumber(number);
        save(captureSebTichCrossRoad);
    }

    @Override
    public CaptureSebTichCrossRoad getCapture(String number) {
        return captureSebTichCrossroadRepository.getAllByNumber(number)
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

}
