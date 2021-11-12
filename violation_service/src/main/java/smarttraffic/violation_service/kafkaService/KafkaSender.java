package smarttraffic.violation_service.kafkaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public  class KafkaSender {

    private final KafkaTemplate<String, Map<String,String>> kafkaTemplate;


    @Autowired
    KafkaSender(KafkaTemplate<String, Map<String,String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   public void sendMessage(String topicName, Map<String,String> message) {
        kafkaTemplate.send(topicName, message);
    }

}