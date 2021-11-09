package smarttraffic.violations_analyzer_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.violations_analyzer_service.model.Capture;
import smarttraffic.violations_analyzer_service.repository.CaptureRepository;

import java.time.Instant;
import java.util.List;

@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    CaptureRepository captureRepository;

    @Override
    public void saveAll(List<Capture> allCaptureList) {
        captureRepository.saveAll(allCaptureList);
    }

    @Override
    public List<Capture> setCaptures(Instant from, Instant to) {
        if (from == null) {
            if (to == null) return captureRepository.findAll();
            else return captureRepository.findAllByInstantBefore(to);
        } else {
            if (to == null) return captureRepository.findAllByInstantAfter(from);
            else return captureRepository.findAllByInstantBetween(from, to);
        }
    }
}
