package smarttraffic.vehicle_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import smarttraffic.vehicle_service.entity.Capture;

@Controller
public class testController {
    @PostMapping("/api/notification-service/patrol")
    public String test(@RequestBody Capture capture) {
        return "notification to patrol have been send...";
    }
}
