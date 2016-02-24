package com.cinchfinancial.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.text.MessageFormat;

/**
 * Created by jbhambhani on 2/19/16.
 */
public class KafkaRouteHelper {

    private String topic;

    private String groupId;


    //@Value("${kafka.broker.port}")
    @Value("9092")
    private int kafkaPort;

    //@Value("${kafka.broker.host}")
    @Value("localhost")
    private String kafkaHost;

    //@Value("${kafka.zookeeper.port}")
    @Value("2181")
    private int zkPort;

    //@Value("${kafka.zookeeper.host}")
    @Value("localhost")
    private String zkHost;



    public KafkaRouteHelper(String topic, String groupId) {
        this.topic = topic;
        this.groupId = groupId;
        this.kafkaHost = "localhost";
        this.kafkaPort = 9092;
        this.zkHost = "localhost";
        this.zkPort = 2181;

    }

    @Bean
    public String setRouteString () {
        System.out.println(kafkaPort);
        String baseString = "kafka:%s:%d?topic=%s&zookeeperConnect=%s:%d&groupId=%s&serializerClass=kafka.serializer.StringEncoder";//&partitionerClass=org.apache.camel.component.kafka.partitioner.SimplePartitioner";
        String routeString = String.format(baseString, this.kafkaHost,
                                            this.kafkaPort, this.topic, this.zkHost,
                                            this.zkPort, this.groupId);
        return routeString;
    }
}
