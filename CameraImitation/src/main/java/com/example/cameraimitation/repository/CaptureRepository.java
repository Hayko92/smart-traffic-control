package com.example.cameraimitation.repository;

import com.example.cameraimitation.entity.Capture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptureRepository extends JpaRepository<Capture, Integer> {
}
