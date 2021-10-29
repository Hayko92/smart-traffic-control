package smarttraffic.cameraimitation.service;

import smarttraffic.cameraimitation.entity.Detector;

public interface DetectorService {
    Detector getById(long id);

    Detector getByPlace(String place);
}
