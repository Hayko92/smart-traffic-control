package smarttraffic.detectors_analyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.repository.CaptureRepository;
import smarttraffic.detectors_analyzer.util.CaptureMapper;

@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    CaptureRepository captureRepository;

    @Override
    public CaptureDTO getByPlateNumber(String plateNumber) {
        Capture capture = captureRepository.findFirstByPlateNumberOrderByIdDesc(plateNumber);
        if (capture != null) return CaptureMapper.maptoDTO(capture);
        else return null;
    }

    @Override
    public CaptureDTO getByPlaceAndNumber(String place, String platenumber) {
        Capture capture = captureRepository.findFirstByPlaceAndPlateNumberOrderByIdDesc(place, platenumber);
        if (capture != null) return CaptureMapper.maptoDTO(capture);
        else return null;
    }

    @Override
    public CaptureDTO getById(int id) {
        Capture capture = captureRepository.getById(id);
        if (capture != null) return CaptureMapper.maptoDTO(capture);
        else return null;
    }

    @Override
    public void save(CaptureDTO captureDTO) {
        if (captureDTO != null) {
            Capture capture = CaptureMapper.maptoCapture(captureDTO);
            captureRepository.save(capture);
        }

    }
}
