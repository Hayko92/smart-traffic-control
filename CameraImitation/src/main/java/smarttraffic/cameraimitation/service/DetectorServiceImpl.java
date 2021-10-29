package smarttraffic.cameraimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.cameraimitation.entity.Detector;
import smarttraffic.cameraimitation.repository.DetectorRepository;

@Service
public class DetectorServiceImpl implements DetectorService {
    @Autowired
    DetectorRepository detectorRepository;

    public Detector getById(long id) {
        return detectorRepository.getById(id);
    }

    public Detector getByPlace(String place) {
        return detectorRepository.getByPlace(place);
    }
}
