package smarttraffic.cameraimitation.util;

import smarttraffic.cameraimitation.dto.CaptureDTO;
import smarttraffic.cameraimitation.entity.Capture;

public final class CaptureMapper {

    private CaptureMapper() {
    }

    public static CaptureDTO maptoDTO(Capture capture) {
        CaptureDTO captureDTO = new CaptureDTO();
        captureDTO.setId(capture.getId());
        captureDTO.setInstant(capture.getInstant());
        captureDTO.setPlace(capture.getPlace());
        captureDTO.setPhotoUrl(capture.getPhotoUrl());
        captureDTO.setPlateNumber(capture.getPlateNumber());
        return captureDTO;
    }

    public static Capture maptoCapture(CaptureDTO captureDTO) {
        Capture capture = new Capture();
        capture.setId(captureDTO.getId());
        capture.setInstant(captureDTO.getInstant());
        capture.setPlace(captureDTO.getPlace());
        capture.setPhotoUrl(captureDTO.getPhotoUrl());
        capture.setPlateNumber(captureDTO.getPlateNumber());
        return capture;
    }
}
