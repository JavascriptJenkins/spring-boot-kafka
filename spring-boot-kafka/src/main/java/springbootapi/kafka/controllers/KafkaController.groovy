package springbootapi.kafka.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springbootapi.kafka.KafkaWorker


@RestController
@RequestMapping("/Kafka/")
class KafkaController {





    @Autowired
    KafkaWorker kafkaWorker




    @RequestMapping("initializeProducer")
    boolean initializeProducer(){


        System.out.println("kafkaWorker.initializeProducer(): ");

        return kafkaWorker.initializeProducer()
    }



    @RequestMapping("sendMessage")
    boolean sendMessage(){


        System.out.println("kafkaWorker.sendMessage(): ");

        return kafkaWorker.sendMessage(10)
    }



    @RequestMapping("initializeConsumer")
    boolean initializeConsumer(){


        System.out.println("kafkaWorker.initializeConsumer(): ");

        return kafkaWorker.initializeConsumer()
    }













}
