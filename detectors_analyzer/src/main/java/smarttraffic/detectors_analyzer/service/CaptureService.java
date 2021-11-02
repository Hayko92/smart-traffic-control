package smarttraffic.detectors_analyzer.service;

import smarttraffic.detectors_analyzer.dto.CaptureDTO;

public interface CaptureService {
    CaptureDTO getByPlateNumber(String plateNumber);

    CaptureDTO getById(int id);

    void save(CaptureDTO capture);

    CaptureDTO getByPlaceAndNumber(String place, String platenumber);
}
