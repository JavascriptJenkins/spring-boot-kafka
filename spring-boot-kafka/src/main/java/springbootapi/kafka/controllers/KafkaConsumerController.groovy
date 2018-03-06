package springbootapi.kafka.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springbootapi.kafka.KafkaConsumerWorker




@RestController
@RequestMapping("/KafkaConsumer/")
class KafkaConsumerController {


    @Autowired
    KafkaConsumerWorker kafkaConsumerWorker


    @RequestMapping("initializeConsumer")
    boolean initializeConsumer() {


        System.out.println("kafkaWorker.initializeConsumer(): ");

        return kafkaConsumerWorker.initializeConsumer()
    }



}