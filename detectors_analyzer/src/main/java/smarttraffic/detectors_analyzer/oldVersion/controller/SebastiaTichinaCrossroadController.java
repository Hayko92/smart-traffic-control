package smarttraffic.detectors_analyzer.oldVersion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smarttraffic.detectors_analyzer.oldVersion.entity.CaptureSebTichCrossRoad;
import smarttraffic.detectors_analyzer.oldVersion.service.CaptureSebTichCrossroadService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sebastia_tichina")
public class SebastiaTichinaCrossroadController implements Camera {
    private final Map<Camera, Double> previousCameras = new HashMap<>();

    @Autowired
    private CaptureSebTichCrossroadService captureSebTichCrossroadService;

    @PostMapping
    public void receiveCapture(@RequestBody CaptureSebTichCrossRoad captureSebTichCrossRoad) {
        if (captureSebTichCrossroadService.hasValidNumber(captureSebTichCrossRoad)) {
            captureSebTichCrossroadService.checkAndSave(captureSebTichCrossRoad);
        } else captureSebTichCrossroadService.sendNotificationToPatrol(captureSebTichCrossRoad);
    }

    @GetMapping("/{number}")
    public CaptureSebTichCrossRoad getCapture(@PathVariable String number) {
        CaptureSebTichCrossRoad captureSebTichCrossRoad = captureSebTichCrossroadService.getCapture(number);
        return captureSebTichCrossRoad;
    }


}
