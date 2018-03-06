package springbootapi.kafka.impl

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.Producer
import org.springframework.stereotype.Component
import springbootapi.kafka.KafkaConsumerWorker


@Component
class KafkaConsumerWorkerImpl implements KafkaConsumerWorker {


    private final static String TOPIC = "my-topic"
    private final static String BOOTSTRAP_SERVERS = "http://kafka-try-kafka-project-1.b9ad.pro-us-east-1.openshiftapps.com:9092"
    //private final static String BOOTSTRAP_SERVERS = "localhost:9092"




    boolean initializeConsumer() {


        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "40000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC))
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100)
            for (ConsumerRecord<String, String> record : records)
                System.out.println("CONSUMING MESSAGE: " + record)
            System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }


    }
}
