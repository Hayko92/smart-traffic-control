package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.service.CaptureGIcrossroadService;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/gasparyan_isakov")
public class ArinberdArcaxCrossroadController implements Camera {
    private final Set<Camera> previousCameras = new HashSet<>();

    @Autowired
    private CaptureGIcrossroadService captureGIcrossroadService;

    @PostMapping
    public void receiveCapture(@RequestBody CaptureGIcrossRoad captureGIcrossRoad) {
        if (captureGIcrossroadService.hasValidNumber(captureGIcrossRoad)) {
            captureGIcrossroadService.checkAndSave(captureGIcrossRoad);
        } else captureGIcrossroadService.sendNotificationToPatrol(captureGIcrossRoad);
    }
    @GetMapping("/{number}")
    public CaptureGIcrossRoad getCapture(@PathVariable String number) {
        CaptureGIcrossRoad captureGIcrossRoad = captureGIcrossroadService.getCapture(number);
        return captureGIcrossRoad;
    }


}
