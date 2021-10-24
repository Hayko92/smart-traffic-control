package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Capture;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface CaptureService {
    void save(Capture capture, List<Integer> ids);
}
