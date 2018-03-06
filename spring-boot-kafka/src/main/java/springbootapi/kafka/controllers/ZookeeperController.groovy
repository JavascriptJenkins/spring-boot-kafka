package springbootapi.kafka.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springbootapi.kafka.KafkaWorker
import springbootapi.kafka.ZookeeperWorker


@RestController
@RequestMapping("/Zookeeper/")
class ZookeeperController {





    @Autowired
    ZookeeperWorker zookeeperWorker







    @RequestMapping("initializeZookeeperConnection")
    boolean initializeZookeeperConnection(){


        System.out.println("zookeeperWorker.initializeZookeeperConnection(): ");

        return zookeeperWorker.initializeZookeeperConnection()
    }









}
