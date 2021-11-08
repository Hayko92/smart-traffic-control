package smarttraffic.notifiers.controller;


import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import smarttraffic.notifiers.dto.CaptureDto;
import smarttraffic.notifiers.util.HTMLCreator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Map;

@RestController
@RequestMapping("/api/notification-service")
public class NotificationController {
    private static final String ACCOUNT_SID = "ACf8751052d983ad03251129ce8f0c7e98";
    private static final String AUTH_TOKEN = "c05e8d9b613cc613c533ad9d1000720e";
    private static final String TWILIO_NUMBER = "+12075013766";

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/patrol")
    public void sendToPatrol(@RequestBody CaptureDto capture) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("Unrecognized vehicle!");
        helper.setText(String.format("unrecognized vehicle fixed at %s in the place %s", capture.getInstant(), capture.getPlace()));
        File file = getFileFromURL(capture.getPhotoUrl());
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

    @PostMapping("/email")
    public void sendEmail(@RequestBody Map<String, String> info) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo(info.get("email"));
        helper.setSubject("YOU HAVE A NEW VIOLATION!");
        helper.setText(HTMLCreator.createSpeedViolationBlank(info));
        File file1 = getFileFromURL(info.get("photoURL1"));
        FileSystemResource fileSystemResource1 = new FileSystemResource(file1);
        helper.addAttachment("car_photo1.jpg", fileSystemResource1);
        if (info.get("type").equals("SPEED")) {
            File file2 = getFileFromURL(info.get("photoURL2"));
            FileSystemResource fileSystemResource2 = new FileSystemResource(file2);
            helper.addAttachment("car_photo2.jpg", fileSystemResource2);
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
        // message.execute();
        return "Sended";
    }

    private File getFileFromURL(String photoUrl) throws IOException {
        URL uRl = new URL(photoUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(uRl.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("photo.jpg");
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileOutputStream.getChannel()
                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        return new File("photo.jpg");
    }
}

