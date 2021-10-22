package com.example.cameraimitation.controller;

import com.example.cameraimitation.entity.Detector;
import com.example.cameraimitation.repository.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    DetectorRepository detectorRepository;
    @GetMapping("/api/test")
    public List<Detector> getAlldetectors() {
        return detectorRepository.findAll();
    }
}
