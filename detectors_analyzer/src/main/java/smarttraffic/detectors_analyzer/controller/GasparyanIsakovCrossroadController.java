package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.entity.CaptureGIcrossRoad;
import smarttraffic.detectors_analyzer.service.CaptureGIcrossroadService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/gasparyan_isakov")
public class GasparyanIsakovCrossroadController implements Camera {
    private final Map<Camera,Double> previousCameras = new HashMap<>();

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
