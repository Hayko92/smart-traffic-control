package smarttraffic.violation_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smarttraffic.violation_service.entity.Capture;

import java.util.Map;

@RestController
public class TestController {
    @PostMapping("/api/violationService")
    public Integer Test(@RequestBody Map<String, Capture> body) {
        return 1;
    }
}
