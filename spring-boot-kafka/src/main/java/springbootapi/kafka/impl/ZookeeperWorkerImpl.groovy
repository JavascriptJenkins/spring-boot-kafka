package springbootapi.kafka.impl

import kafka.admin.AdminUtils
import kafka.admin.RackAwareMode
import kafka.utils.ZKStringSerializer$
import kafka.utils.ZkUtils
import org.I0Itec.zkclient.ZkClient
import org.I0Itec.zkclient.ZkConnection
import org.springframework.stereotype.Component
import springbootapi.kafka.ZookeeperWorker



@Component
class ZookeeperWorkerImpl implements ZookeeperWorker {













    boolean initializeZookeeperConnection(){



        // starts one zookeeper server on the zookeeper container
        // zookeeperConnect is HOSTNAME:PORT that the zookeeper instance is running on
        //String zookeeperConnect = "localhost:2181";
       String zookeeperConnect = "zookeeper-master.kafka-project-1.svc:2181";
        int sessionTimeoutMs = 20 * 1000;
        int connectionTimeoutMs = 20 * 1000;

        String topic = "my-topic";
        int partitions = 1;
        int replication = 1;
        Properties topicConfig = new Properties(); // add per-topic configurations settings here

        // Note: You must initialize the ZkClient with ZKStringSerializer.  If you don't, then
        // createTopic() will only seem to work (it will return without error).  The topic will exist in
        // only ZooKeeper and will be returned when listing topics, but Kafka itself does not create the
        // topic.
        ZkClient zkClient = new ZkClient(
                zookeeperConnect,
                sessionTimeoutMs,
                connectionTimeoutMs,
                ZKStringSerializer$.MODULE$);

        // Security for Kafka was added in Kafka 0.9.0.0
        boolean isSecureKafkaCluster = false;

        ZkUtils zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperConnect), isSecureKafkaCluster);
        AdminUtils.createTopic(zkUtils, topic, partitions, replication, topicConfig, RackAwareMode.Enforced$.MODULE$);
        zkClient.close();


    }




}
