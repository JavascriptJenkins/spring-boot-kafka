package springbootapi.kafka

import org.springframework.stereotype.Component
import springbootapi.kafka.impl.KafkaWorkerImpl


@Component
interface KafkaWorker {






    boolean initializeProducer()

    boolean initializeConsumer()


    boolean sendMessage(int messageAmount)








}