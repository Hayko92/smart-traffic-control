package smarttraffic.notifiers.controller;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
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
public class NotificationController {
    private static final String ACCOUNT_SID ="ACf8751052d983ad03251129ce8f0c7e98" ;
    private static final String AUTH_TOKEN = "91e63ea44f0483b36048a1436ebcdafd";
    private static final String TWILIO_NUMBER = "+12075013766";
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/api/notification-service/patrol")
    public String sendToPatrol(@RequestBody Capture capture) {
        return "OK...we have recevied";
    }
//todo must be formatted after violation service request
    @PostMapping ("/api/notification-service/email")
    public String sendEmail(@RequestBody MimeMessage mimeMessage) throws MessagingException {
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("YOU HAVE A NEW VIOLATION! CONGRATULATIONS");
        helper.setText("<b>HELLO WORD</b>");
        FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Hayk\\IdeaProjects\\smart-traffic-control\\CameraImitation\\src\\main\\resources\\car_numbers\\0.jpg"));
        helper.addAttachment("car_photo", file);
        mailSender.send(mimeMessage);
        return "OK...we have recevied";
    }
    @GetMapping ("/api/notification-service/sms")
    public String sendSMS() throws MessagingException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        MessageCreator message = Message.create(ACCOUNT_SID,
                new PhoneNumber("+37493191719"),
                new PhoneNumber(TWILIO_NUMBER),
                        "SMART TRAFFIC ARMENIA-\n չգիտեի՞ր որ կամերա կա ուր ես տենց արագ քշու՞մ,\n լավ եղավ՞");
         Message message1 = message.execute();
         return "Sended";
    }
}
