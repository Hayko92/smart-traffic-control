package smarttraffic.detectors_analyzer.service;

import smarttraffic.detectors_analyzer.dto.CaptureDTO;

import java.util.List;

public interface CaptureService {
    CaptureDTO getByPlateNumber(String plateNumber);

    CaptureDTO getById(int id);

    int save(CaptureDTO capture);

    CaptureDTO getByPlaceAndNumber(String place, String plateNumber);

    List<CaptureDTO> findAll();

    void receiveCapture(CaptureDTO capture, String token);
}
