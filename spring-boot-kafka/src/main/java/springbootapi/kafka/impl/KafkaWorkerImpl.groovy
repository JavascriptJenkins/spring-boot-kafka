package springbootapi.kafka.impl

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.common.serialization.Deserializer.*
import org.springframework.stereotype.Component
import springbootapi.kafka.KafkaWorker



@Component
class KafkaWorkerImpl implements KafkaWorker {


    // declare a kafka producer instance
    Producer<String, String> producer

    private final static String TOPIC = "my-topic"
    private final static String BOOTSTRAP_SERVERS = "kafka-try.kafka-project-1.svc:9092"
    //private final static String BOOTSTRAP_SERVERS = "localhost:9092"






    boolean initializeProducer() {

        Properties props = new Properties();
        // Connect to the kafka host HOST:PORT  (9092 is likely the port)
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS)
        props.put("acks", "all")
        props.put("retries", 3)
        props.put("batch.size", 16384)
        props.put("linger.ms", 1)
        props.put("buffer.memory", 33554432)
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")





        try{
            producer = new KafkaProducer<>(props)
            System.out.println("Kafka Producer Initialized")
            return true
        } catch(Exception ex){
            System.out.println("Caught exception initializing kafka producer: "+ex)
            return false
        }



    }


    boolean sendMessage(int messageAmount) {



        try{


            for(int i = 0; i < messageAmount; i++){

                ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(TOPIC, Integer.toString(i), Integer.toString(i))

                System.out.println("Sending message: " +producerRecord)

                producer.send(producerRecord)
            }
            System.out.println("Kafka Producer sent messages")

            producer.close()


            return true


        } catch(Exception ex){



            System.out.println("Caught exception sending messages: "+ex)
            return false
        }

    }




    boolean initializeConsumer(){



        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "3000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC))
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100)
            for (ConsumerRecord<String, String> record : records)
                System.out.println("CONSUMING MESSAGE: "+record)
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }






    }










}
