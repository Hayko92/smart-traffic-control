package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.InsuranceViolation;
import smarttraffic.detectors_analyzer.entity.Vehicle;
import smarttraffic.detectors_analyzer.repository.CaptureRepository;
import smarttraffic.detectors_analyzer.repository.VehicleRepository;
import smarttraffic.detectors_analyzer.util.NumberExtractor;

import java.util.Date;

public class CaptureServiceImp implements CaptureService {
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CloudVisionTemplate cloudVisionTemplate;

    private final CaptureRepository captureRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public CaptureServiceImp(CaptureRepository captureRepository, VehicleRepository vehicleRepository) {
        this.captureRepository = captureRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void save(Capture capture) {
        captureRepository.save(capture);
    }

    @Override
    public boolean hasValidInsurance (Capture capture) {
        Date date = capture.getCaptureTime();
        String photoURL =capture.getPhotoURL();
        String number =extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getInsuranceExpiry());
    }

    @Override
    public boolean hasValidTechInspection(Capture capture) {
        Date date = capture.getCaptureTime();
        String photoURL =capture.getPhotoURL();
        String number =extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        return date.before(vehicle.getTechInspectionExpiry());
    }

    @Override
    public void setStatusChecked(Capture capture) {
        String photoURL =capture.getPhotoURL();
        String number =extractNumber(photoURL);
        Vehicle vehicle = vehicleRepository.getByNumber(number);
        vehicle.setChecked(true);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createViolation(InsuranceViolation insuranceViolation, Capture capture) {
        //fixme
    }

    public String extractNumber(String photoURL) {
        String textFromImage = this.cloudVisionTemplate.extractTextFromImage(this.resourceLoader.getResource(photoURL));
        return NumberExtractor.extract(textFromImage);
    }

}
