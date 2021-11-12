package smarttraffic.violation_service.kafkaService;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import smarttraffic.violation_service.util.InfoExtractor;
import smarttraffic.violation_service.util.JwtTokenUtil;

import java.util.Map;

@Component
public class KafkaListeners {
    @Value("${notificationService}")
    private String notificationServiceUrl;
    @KafkaListener(topics = "notification")
    void listener(Map<String,String> info) {
        RestTemplate restTemplate = new RestTemplate();
        //HttpHeaders headers = JwtTokenUtil.getHeadersWithToken(token);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(info);
        restTemplate.exchange(notificationServiceUrl + "/email", HttpMethod.POST, httpEntity, Void.class);
        restTemplate.exchange(notificationServiceUrl + "/sms", HttpMethod.POST, httpEntity, Void.class);
    }
}
