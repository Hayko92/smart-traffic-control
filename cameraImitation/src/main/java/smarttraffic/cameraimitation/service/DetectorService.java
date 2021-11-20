package smarttraffic.cameraimitation.service;

import smarttraffic.cameraimitation.dto.DetectorDto;

import java.util.List;

public interface DetectorService {

    DetectorDto getByPlace(String place);

    List<DetectorDto> findAll();

    void sendRandomPhotoFromRandomDetector(String token);
}
