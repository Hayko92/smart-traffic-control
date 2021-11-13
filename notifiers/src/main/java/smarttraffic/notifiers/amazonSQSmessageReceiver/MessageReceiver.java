package smarttraffic.notifiers.amazonSQSmessageReceiver;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import smarttraffic.notifiers.service.NotificationService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessageReceiver {
    @Value("${sqs.region}")
    String region;
    @Value("${access.key.id}")
    String accessKey;
    @Autowired
    NotificationService notificationService;
    @Value("${sqs.url}")
    private String sqsURL;
    @Value("${access.key.secret}")
    private String secretKey;

    @Scheduled(fixedRate = 1000)
    public void getMessage() throws IOException, MessagingException {
        AmazonSQS sqs = getAmazonSQS();
        while (true) {
            final ReceiveMessageRequest receiveMessageRequest =
                    new ReceiveMessageRequest(sqsURL)
                            .withMaxNumberOfMessages(1)
                            .withWaitTimeSeconds(3);
            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (final Message message : messages) {
                Map<String, String> info = getInfoFromMessage(sqs, message);
                notificationService.sendSMS(info);
                notificationService.sendMail(info);
            }
        }
    }

    private Map<String, String> getInfoFromMessage(AmazonSQS sqs, Message message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<>() {
        };
        Map<String, String> info = objectMapper.readValue(message.getBody(), typeRef);
        DeleteMessageRequest deleteMessageRequest = new DeleteMessageRequest()
                .withQueueUrl(sqsURL)
                .withReceiptHandle(message.getReceiptHandle());
        sqs.deleteMessage(deleteMessageRequest);
        return info;
    }

    private AmazonSQS getAmazonSQS() {
        BasicAWSCredentials bAWSc = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSQSClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(bAWSc))
                .build();
    }

}
