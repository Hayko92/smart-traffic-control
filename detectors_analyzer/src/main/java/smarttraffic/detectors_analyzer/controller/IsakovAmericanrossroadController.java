package smarttraffic.detectors_analyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.entity.CaptureArArCrossRoad;
import smarttraffic.detectors_analyzer.entity.CaptureIsAmCrossRoad;
import smarttraffic.detectors_analyzer.repository.CaptureArArCrossroadRepository;
import smarttraffic.detectors_analyzer.repository.CaptureGIcrossroadRepository;
import smarttraffic.detectors_analyzer.repository.CaptureSebTichCrossroadRepository;
import smarttraffic.detectors_analyzer.service.CaptureIsAmCrossroadService;
import smarttraffic.detectors_analyzer.service.CaptureSebTichCrossroadService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/isakov-american")
public class IsakovAmericanrossroadController implements Camera {
    private final Map<Camera, Double> previousCameras = new HashMap<>();
    @Autowired
    ArinberdArcaxCrossroadController captureArArCrossroadRepository;
    @Autowired
    GasparyanIsakovCrossroadController captureSebTichCrossroadRepository;
    @Autowired
    SebastiaTichinaCrossroadController captureGIcrossroadRepository;
    {
        previousCameras.put(captureArArCrossroadRepository,7400D);
        previousCameras.put(captureSebTichCrossroadRepository,7100D);
        previousCameras.put(captureGIcrossroadRepository,4900D);
    }

    @Autowired
    private CaptureIsAmCrossroadService captureIsAmCrossroadService;

    @PostMapping
    public void receiveCapture(@RequestBody CaptureIsAmCrossRoad captureIsAmCrossRoad) {
        if (captureIsAmCrossroadService.hasValidNumber(captureIsAmCrossRoad)) {
            captureIsAmCrossroadService.checkAndSave(captureIsAmCrossRoad);
            captureIsAmCrossroadService.checkSpeed(previousCameras, captureIsAmCrossRoad);
        } else captureIsAmCrossroadService.sendNotificationToPatrol(captureIsAmCrossRoad);
    }

    @GetMapping("/{number}")
    public CaptureIsAmCrossRoad getCapture(@PathVariable String number) {
        CaptureIsAmCrossRoad captureIsAmCrossRoad = captureIsAmCrossroadService.getCapture(number);
        return captureIsAmCrossRoad;
    }

}
