package springbootapi.kafka

import org.springframework.stereotype.Component


@Component
interface ZookeeperWorker {





    boolean initializeZookeeperConnection()

}