package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.dto.CaptureDTO;
import smarttraffic.detectors_analyzer.service.CaptureService;

import java.util.List;

@RestController
@RequestMapping("/api/detector-analyzer")
public class DetectorController {

   private final CaptureService captureService;
    @Autowired
    public DetectorController(CaptureService captureService) {
        this.captureService = captureService;
    }

    @GetMapping("/all")
    public List<CaptureDTO> getAllDetectors(@RequestHeader(name = "AUTHORIZATION") String token) {
        return captureService.findAll();
    }

    @GetMapping("/capture/{id}")
    public CaptureDTO sendCapture(@PathVariable String id) {
        return captureService.getById(Integer.parseInt(id));
    }

    @PostMapping
    public void receiveCapture(@RequestBody CaptureDTO capture, @RequestHeader(name = "AUTHORIZATION") String token) {
        captureService.receiveCapture(capture,token);
    }

}