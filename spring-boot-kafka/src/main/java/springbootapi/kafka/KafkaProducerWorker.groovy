package springbootapi.kafka

import org.springframework.stereotype.Component


@Component
interface KafkaProducerWorker {






    boolean initializeProducer()


    boolean sendMessage(int messageAmount)








}