package smarttraffic.violation_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarttraffic.violation_service.entity.Capture;
import smarttraffic.violation_service.entity.Violation;
import smarttraffic.violation_service.service.ViolationService;


@RestController
@RequestMapping()
public class ViolationController {
    private Capture capture;
    private Violation violation;

    @Autowired
    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }

    @GetMapping("/api/vehicle-service/")
    public ResponseEntity<Violation> getViolation(@PathVariable String number) {
        return ResponseEntity.of(violationService.getViolation(number));
    }

    @PostMapping("/api/detectors-analyzer-service/")
    public void createViolation(@RequestBody Capture capture, String type) {
        violation.setNumber(capture.getPlateNumber());
        violation.setPhotoUrl1(capture.getPhotoUrl());
        violation.setCreationDate(capture.getInstant());
        violationService.saveViolation(violation);
        //call notifier service
    }
}
