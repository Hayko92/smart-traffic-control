package smarttraffic.notifiers.service;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public interface NotificationService {
    void sendMail(Map<String, String> info) throws MessagingException, IOException;

    void sendSMS(Map<String, String> info);

    File getFileFromURL(String photoURL) throws IOException;
}
