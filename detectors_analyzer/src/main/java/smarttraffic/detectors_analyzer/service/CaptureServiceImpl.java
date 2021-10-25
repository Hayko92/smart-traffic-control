package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.repository.CaptureRepository;

@Service
public class CaptureServiceImpl implements CaptureService{
    @Autowired
    CaptureRepository captureRepository;

    @Override
    public Capture getByPlateNumber(String plateNumber) {
        return captureRepository.findFirstByPlateNumberOrderByIdDesc(plateNumber);
    }
}
