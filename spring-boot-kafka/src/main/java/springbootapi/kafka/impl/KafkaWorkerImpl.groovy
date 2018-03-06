package springbootapi.kafka.impl

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers
import com.fasterxml.jackson.databind.deser.std.StringDeserializer
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.stereotype.Component
import springbootapi.kafka.KafkaWorker



@Component
class KafkaWorkerImpl implements KafkaWorker {


    // declare a kafka producer instance
    Producer<String, String> producer

    private final static String TOPIC = "my-topic"
    private final static String BOOTSTRAP_SERVERS = "kafka-try.kafka-project-1.svc:9092"






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




    boolean initializeConsumer(){




        runConsumer()







    }



    private static void runConsumer() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();

        final int giveUp = 100;   int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000)


            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }


//            for (String element : array) {
//                System.out.println("Element: " + element);
//            }


            System.println("found records: " +consumerRecords)

//            for(int i=0; i< consumerRecords.count(); i++){
//
//                String value = consumerRecords.
//                String key = consumerRecords
//
//            }
//
//
//            consumerRecords.forEach(record --> {
//                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
//                        record.key(), record.value(),
//                        record.partition(), record.offset());
//            })

            consumer.commitAsync()
        }




        consumer.close()
        System.out.println("CONSUMER DONE RUNNING")
    }


    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                NumberDeserializers.LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(props);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }








}
