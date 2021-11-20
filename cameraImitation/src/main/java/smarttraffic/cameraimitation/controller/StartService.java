package smarttraffic.cameraimitation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import smarttraffic.cameraimitation.dto.DetectorDto;
import smarttraffic.cameraimitation.exception.SmartTrafficControlException;
import smarttraffic.cameraimitation.service.DetectorService;
import smarttraffic.cameraimitation.util.JwtTokenUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/detector-imitation-service")
public class StartService {
    private final DetectorService detectorService;
    private final String token = JwtTokenUtil.generateToken("${username}");

    @Autowired
    public StartService(DetectorService detectorService) {
        this.detectorService = detectorService;
    }

    @GetMapping()
    public void sendRequest() {
        while (true) {
            detectorService.sendRandomPhotoFromRandomDetector(token);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new SmartTrafficControlException(e.getMessage(), HttpStatus.valueOf(500));
            }
        }
    }

    @GetMapping("/{detectorPlace}")
    public DetectorDto getDetector(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        return detectorService.getByPlace(detectorPlace);
    }

    @GetMapping("/all")
    public List<DetectorDto> getAllDetectors(@RequestHeader(name = "AUTHORIZATION") String token) {
        return detectorService.findAll();
    }

    @GetMapping("/previous_detectors/{detectorPlace}")
    public Map<String, Integer> getPreviousDetectors(@PathVariable String detectorPlace, @RequestHeader(name = "AUTHORIZATION") String token) {
        DetectorDto detector = detectorService.getByPlace(detectorPlace);
        return detector.getPreviousDetectorsDistance();
    }

}
