package smarttraffic.notifiers.service;

import com.twilio.sdk.Twilio;
import com.twilio.sdk.creator.api.v2010.account.MessageCreator;
import com.twilio.sdk.resource.api.v2010.account.Message;
import com.twilio.sdk.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
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

@Service
public class NotificationServiceImpl implements NotificationService {
    @Value("${twillio.accaunt.sid}")
    String twillioAccaountSid;
    @Value("${twillio.accaunt.token}")
    String twillioAccauntToken;
    @Value("${twillio.accaunt.number}")
    String twillioAccauntNumber;
    @Value("${violationService}")
    String violationServiceURL;
    @Value("${spring.mail.username}")
    String mailUsername;

    private final JavaMailSender mailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(Map<String, String> info) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mailUsername);
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

    @Override
    public void sendSMS(Map<String, String> info) {
        Twilio.init(twillioAccaountSid, twillioAccauntToken);
        MessageCreator message = Message.create(twillioAccaountSid,
                new PhoneNumber("+37493191719"),
                new PhoneNumber(twillioAccauntNumber),
                creatSMStext(info));
      //  message.execute();
    }

    public File getFileFromURL(String photoUrl) throws IOException {
        URL uRl = new URL(photoUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(uRl.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream("photo.jpg");
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileOutputStream.getChannel()
                .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        return new File("photo.jpg");
    }

    @Override
    public void sendToPatrol(CaptureDto capture) throws MessagingException, IOException {
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

    @Override
    public void sendToPatrolIDofOwner(Long ownerID) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setFrom("SmartTrafficServiceArmenia@gmail.com");
        helper.setTo("asatryanhayko92@gmail.com");
        helper.setSubject("Driver with null points!");
        helper.setText(String.format("Driver with ID %d have 0 points left", ownerID));
        mailSender.send(message);
    }

    private String creatSMStext(Map<String, String> info) {
        String violationURL = violationServiceURL + "/violation/" + info.get("id");
        return "SMART TRAFFIC CONTROL ARMENIA\nYou have a new violation, for more information please visit " + violationURL;
    }

}
