package smarttraffic.cameraimitation.util;

import smarttraffic.cameraimitation.dto.CaptureDto;
import smarttraffic.cameraimitation.entity.Capture;

public final class CaptureMapper {

    private CaptureMapper() {
    }

    public static CaptureDto maptoDTO(Capture capture) {
        CaptureDto captureDTO = new CaptureDto();
        captureDTO.setId(capture.getId());
        captureDTO.setInstant(capture.getInstant());
        captureDTO.setPlace(capture.getPlace());
        captureDTO.setPhotoUrl(capture.getPhotoUrl());
        captureDTO.setPlateNumber(capture.getPlateNumber());
        return captureDTO;
    }

    public static Capture maptoCapture(CaptureDto captureDTO) {
        Capture capture = new Capture();
        capture.setId(captureDTO.getId());
        capture.setInstant(captureDTO.getInstant());
        capture.setPlace(captureDTO.getPlace());
        capture.setPhotoUrl(captureDTO.getPhotoUrl());
        capture.setPlateNumber(captureDTO.getPlateNumber());
        return capture;
    }
}
