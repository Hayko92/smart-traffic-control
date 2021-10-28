package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Detector;
import org.springframework.stereotype.Service;

@Service
public interface DetectorService {
    Detector getById(long id);

    Detector getByPlace(String place);
}
