package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.entity.Capture;
import smarttraffic.detectors_analyzer.entity.InsuranceViolation;
import smarttraffic.detectors_analyzer.entity.TechinspectionViolation;
import smarttraffic.detectors_analyzer.service.CameraServise;
import smarttraffic.detectors_analyzer.service.CaptureService;

@RestController
@RequestMapping("/api/gasparyan_isakov")
public class GasparyanIsakovCrossroadController {
    @Autowired
    private CameraServise cameraServise;
    @Autowired
    private CaptureService captureService;

    @PostMapping
    public void receiveCapture(@RequestBody Capture capture) {
       if(!captureService.hasValidInsurance(capture)) captureService.createViolation(new InsuranceViolation(), capture)
           ;
       if(!captureService.hasValidTechInspection(capture)) captureService.createViolation(new TechinspectionViolation(), capture);
        captureService.setStatusChecked(capture);
        captureService.save(capture);
    }


}
