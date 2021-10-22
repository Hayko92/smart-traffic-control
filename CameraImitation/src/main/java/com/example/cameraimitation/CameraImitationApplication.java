package com.example.cameraimitation;

import com.example.cameraimitation.entity.Detector;
import com.example.cameraimitation.repository.DetectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CameraImitationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CameraImitationApplication.class, args);
    }

}
