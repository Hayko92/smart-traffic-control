package smarttraffic.notifiers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smarttraffic.notifiers.entity.Capture;

@RestController
public class TestController {
    @PostMapping("/api/notification-service/patrol")
    public String Test(@RequestBody Capture capture) {
        return "OK...we have recevied";
    }

}
