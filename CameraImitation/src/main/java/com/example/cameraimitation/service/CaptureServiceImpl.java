package com.example.cameraimitation.service;

import com.example.cameraimitation.entity.Capture;
import com.example.cameraimitation.repository.CaptureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CaptureServiceImpl implements CaptureService {
    @Autowired
    CaptureRepository captureRrepository;
    @Override
    @Transactional
    public void save(Capture capture, List<Integer> ids) {
        capture.addToViolationList(ids);
        captureRrepository.save(capture);

    }
}
