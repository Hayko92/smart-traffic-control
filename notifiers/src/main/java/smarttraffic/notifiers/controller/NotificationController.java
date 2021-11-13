package smarttraffic.notifiers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import smarttraffic.notifiers.dto.CaptureDto;
import smarttraffic.notifiers.service.NotificationService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/notification-service")
public class NotificationController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/patrol")
    public void sendToPatrol(@RequestBody CaptureDto capture) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("Unrecognized vehicle!");
        helper.setText(String.format("unrecognized vehicle fixed at %s in the place %s", capture.getInstant(), capture.getPlace()));
        File file = notificationService.getFileFromURL(capture.getPhotoUrl());
        FileSystemResource file1 = new FileSystemResource(file);
        helper.addAttachment("car_photo1.jpg", file1);
        mailSender.send(message);
    }

    @GetMapping("/patrol/owner/{ownerID}")
    public void sendToPatrolIDofOwner(@PathVariable Long ownerID) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("Driver with null points!");
        helper.setText(String.format("Driver with ID %d have 0 points left", ownerID));
        mailSender.send(message);
    }

}

