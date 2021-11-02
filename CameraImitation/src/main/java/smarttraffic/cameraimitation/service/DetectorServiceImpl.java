package smarttraffic.cameraimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.repository.DetectorRepository;
import smarttraffic.cameraimitation.util.DetectorMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetectorServiceImpl implements DetectorService {
    @Autowired
    DetectorRepository detectorRepository;

    public DetectorDto getById(long id) {
        Detector detector = detectorRepository.getById(id);
        return DetectorMapper.mapToDetectorDTO(detector);
    }

    public DetectorDto getByPlace(String place) {
        Detector detector = detectorRepository.getByPlace(place);
        return DetectorMapper.mapToDetectorDTO(detector);
    }

    @Override
    public List<DetectorDto> findAll() {
        List<Detector> detectors = detectorRepository.findAll();
        return detectors.stream()
                .map(DetectorMapper::mapToDetectorDTO)
                .collect(Collectors.toList());
    }
}
