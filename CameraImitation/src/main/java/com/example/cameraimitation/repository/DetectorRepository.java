package com.example.cameraimitation.repository;

import com.example.cameraimitation.entity.Detector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetectorRepository extends JpaRepository<Detector, Long> {
    Detector getByPlace(String place);
}
