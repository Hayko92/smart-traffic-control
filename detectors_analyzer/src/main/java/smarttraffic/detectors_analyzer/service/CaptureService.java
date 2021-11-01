package smarttraffic.detectors_analyzer.service;

import smarttraffic.detectors_analyzer.entity.Capture;

public interface CaptureService {
    Capture getByPlateNumber(String plateNumber);

    Capture getById(int id);

    void save(Capture capture);

     Capture getByPlaceAndNumber(String place, String platenumber);
}
