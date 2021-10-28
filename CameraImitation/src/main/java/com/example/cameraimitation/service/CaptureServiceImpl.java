package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Capture;
import com.example.cameraimitation.repository.CaptureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
