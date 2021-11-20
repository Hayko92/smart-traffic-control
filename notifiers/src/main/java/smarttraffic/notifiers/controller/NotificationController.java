package smarttraffic.notifiers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import smarttraffic.notifiers.dto.CaptureDto;
import smarttraffic.notifiers.service.NotificationService;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/api/notification-service")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/patrol")
    public void sendToPatrol(@RequestBody CaptureDto capture) throws MessagingException, IOException {
        notificationService.sendToPatrol(capture);
    }

    @GetMapping("/patrol/owner/{ownerID}")
    public void sendToPatrolIDofOwner(@PathVariable Long ownerID) throws MessagingException {
        notificationService.sendToPatrolIDofOwner(ownerID);
    }

}

