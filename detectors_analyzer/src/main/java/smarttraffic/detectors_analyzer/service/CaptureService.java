package smarttraffic.detectors_analyzer.service;

import smarttraffic.detectors_analyzer.entity.Capture;

public interface CaptureService {
    Capture getByPlateNumber(String plateNumber);

    Capture getByPlace(String place);
    Capture getById(int id);
}
