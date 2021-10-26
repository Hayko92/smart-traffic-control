package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Detector;
import com.example.cameraimitation.repository.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetectorService {
    @Autowired
    DetectorRepository detectorRepository;

    public Detector getById(long id) {
        return detectorRepository.getById(id);
    }

    public Detector getByPlace(String place) {
        return detectorRepository.getByPlace(place);
    }
}
