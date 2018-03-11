package springbootapi.kafka

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
class KafkaListenerConfig {

    @KafkaListener(topics = "my-topic", groupId = "kafka-broker-test")
    void listen(String message) {

        System.out.println("Received Messasge: " + message);

    }
}