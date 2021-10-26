package com.example.cameraimitation.util;

import com.example.cameraimitation.dto.CaptureDTO;
import com.example.cameraimitation.entity.Capture;

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
