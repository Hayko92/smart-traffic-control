package smarttraffic.notifiers.amazonSQSmessageReceiver;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageReceiver {
    @Value("${sqs.url}")
    private String sqsURL;


    @Value("${rest.endpoint.notification}")
    private String notificationUrl;


    @Scheduled(fixedRate = 1000)
    public void getMessage() {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        while(true) {
            final ReceiveMessageRequest receiveMessageRequest =
                    new ReceiveMessageRequest(sqsURL)
                            .withMaxNumberOfMessages(1)
                            .withWaitTimeSeconds(3);
            final List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (final Message message : messages) {
                System.out.println(message.getBody());
            }
        }
    }

}
