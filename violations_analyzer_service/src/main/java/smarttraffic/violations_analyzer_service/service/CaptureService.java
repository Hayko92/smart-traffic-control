package smarttraffic.violations_analyzer_service.service;


import smarttraffic.violations_analyzer_service.model.Capture;

import java.time.Instant;
import java.util.List;

public interface CaptureService {

    void saveAll(List<Capture> allCaptureList);

    List<Capture> setCaptures(Instant from, Instant to);
}
