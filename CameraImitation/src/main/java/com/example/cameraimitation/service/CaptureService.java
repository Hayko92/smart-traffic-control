package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Capture;

public interface CaptureService {
    void save(Capture capture);

    Capture getById(int parseInt);
}
