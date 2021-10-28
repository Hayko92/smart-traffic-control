package smarttraffic.notifiers.controller;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarttraffic.notifiers.entity.Capture;
import smarttraffic.notifiers.util.HTMLCreator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/notification-service")
public class NotificationController {
    private static final String ACCOUNT_SID = "ACf8751052d983ad03251129ce8f0c7e98";
    private static final String AUTH_TOKEN = "91e63ea44f0483b36048a1436ebcdafd";
    private static final String TWILIO_NUMBER = "+12075013766";
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/patrol")
    public String sendToPatrol(@RequestBody Capture capture) {
        return "OK...we have recevied";
    }

    @PostMapping("/patrol/owner")
    public String sendToPatrolIDofOwner(@RequestBody Long ownerID) {
        return "OK...we have recevied";
    }

    //todo must be formatted after violation service request
    @PostMapping("/email")
    public void sendEmail(@RequestBody Map<String, String> info) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo(info.get("email"));
        helper.setSubject("YOU HAVE A NEW VIOLATION!");
        helper.setText(HTMLCreator.createSpeedViolationBlank(info));
        FileSystemResource file1 = new FileSystemResource(new File(info.get("photoURL1")));
        helper.addAttachment("car_photo1", file1);
        if (info.get("type").equals("SPEED")) {
            FileSystemResource file2 = new FileSystemResource(new File(info.get("photoURL2")));
            helper.addAttachment("car_photo2", file2);
        }
        mailSender.send(message);
    }

    @PostMapping("/sms")
    public String sendSMS(@RequestBody Map<String, String> info) throws MessagingException {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        MessageCreator message = Message.create(ACCOUNT_SID,
                new PhoneNumber("+37493191719"),
                new PhoneNumber(TWILIO_NUMBER),
                "Ճանապարհային Ոստիկանություն \n Դուք ունեք նոր իրավախախտում,\n խնդրում ենք մուտք գործել https://roadpolice.am/ և վճարել");
        Message message1 = message.execute();
        return "Sended";
    }
}
