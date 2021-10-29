package smarttraffic.cameraimitation.service;

import smarttraffic.cameraimitation.entity.Capture;

public interface CaptureService {
    void save(Capture capture);

    Capture getById(int parseInt);
}
