package smarttraffic.notifiers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import smarttraffic.notifiers.entity.Capture;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RestController
public class TestController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/api/notification-service/patrol")
    public String sendToPatrol(@RequestBody Capture capture) {
        return "OK...we have recevied";
    }

    @GetMapping("/api/notification-service/email")
    public String sendEmail(@RequestBody MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("YOU HAVE A NEW VIOLATION! CONGRATULATIONS");
        helper.setText("<b>HELLO WORD ,</b>");
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Hayk\\IdeaProjects\\smart-traffic-control\\CameraImitation\\src\\main\\resources\\car_numbers\\0.jpg"));
        helper.addAttachment("car_photo", file);
        mailSender.send(mimeMessage);
        return "OK...we have recevied";
    }

}
