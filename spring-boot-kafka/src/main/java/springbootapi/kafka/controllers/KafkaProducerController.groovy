package springbootapi.kafka.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springbootapi.kafka.KafkaProducerWorker


@RestController
@RequestMapping("/KafkaProducer/")
class KafkaProducerController {



    @Autowired
    KafkaProducerWorker kafkaProducerWorker




    @RequestMapping("initializeProducer")
    boolean initializeProducer(){


        System.out.println("kafkaWorker.initializeProducer(): ");

        return kafkaProducerWorker.initializeProducer()
    }



    @RequestMapping("sendMessage")
    boolean sendMessage(){


        System.out.println("kafkaWorker.sendMessage(): ");

        return kafkaProducerWorker.sendMessage(10)
    }














}
