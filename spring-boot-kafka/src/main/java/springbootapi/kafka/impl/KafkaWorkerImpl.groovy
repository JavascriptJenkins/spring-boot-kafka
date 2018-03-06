package springbootapi.kafka.impl

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import springbootapi.kafka.KafkaWorker



@Component
class KafkaWorkerImpl implements KafkaWorker {


    // declare a kafka producer instance
    Producer<String, String> producer





    boolean initializeProducer() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092")
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
                producer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(i), Integer.toString(i)))
            }
            System.out.println("Kafka Producer sent messages")

            producer.close()


            return true


        } catch(Exception ex){



            System.out.println("Caught exception sending messages: "+ex)
            return false
        }

    }








}
