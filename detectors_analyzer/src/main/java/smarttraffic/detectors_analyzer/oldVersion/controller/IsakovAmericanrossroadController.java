package smarttraffic.detectors_analyzer.oldVersion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureIsAmCrossRoad;
import smarttraffic.detectors_analyzer.oldVersion.service.CaptureIsAmCrossroadService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/isakov-american")
public class IsakovAmericanrossroadController implements Camera {
    private final Map<Camera, Integer> previousCameras = new HashMap<>();
    @Autowired
    ArinberdArcaxCrossroadController captureArArCrossroadRepository;
    @Autowired
    GasparyanIsakovCrossroadController captureSebTichCrossroadRepository;
    @Autowired
    SebastiaTichinaCrossroadController captureGIcrossroadRepository;
    {
        previousCameras.put(captureArArCrossroadRepository,7400);
        previousCameras.put(captureSebTichCrossroadRepository,7100);
        previousCameras.put(captureGIcrossroadRepository,4900);
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
