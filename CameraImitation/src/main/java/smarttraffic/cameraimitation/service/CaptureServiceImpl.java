package smarttraffic.cameraimitation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarttraffic.cameraimitation.entity.Capture;
import smarttraffic.cameraimitation.repository.CaptureRepository;

@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    CaptureRepository captureRepository;

    @Override
    public void save(Capture capture) {
        captureRepository.save(capture);
    }

    @Override
    public Capture getById(int parseInt) {
        return captureRepository.getById(parseInt);
    }
}
