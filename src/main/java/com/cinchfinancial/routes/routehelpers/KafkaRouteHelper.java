package com.cinchfinancial.routes.routehelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.text.MessageFormat;

/**
 * Created by jbhambhani on 2/19/16.
 */
public class KafkaRouteHelper implements RouteHelper{

    private String topic;

    private String groupId;


    //@Value("${routers.broker.port}")
    @Value("9092")
    private int kafkaPort;

    //@Value("${routers.broker.host}")
    @Value("localhost")
    private String kafkaHost;

    //@Value("${routers.zookeeper.port}")
    @Value("2181")
    private int zkPort;

    //@Value("${routers.zookeeper.host}")
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
    @Override
    public String setRouteString () {
        String baseString = "kafka:%s:%d?topic=%s&zookeeperHost=%s&zookeeperPort%d" +
                "&groupId=%s&serializerClass=routers.serializer.StringEncoder" +
                "&partitionerClass=org.apache.camel.component.routers.partitioner.SimplePartitioner";
        String routeString = String.format(baseString, this.kafkaHost,
                                            this.kafkaPort, this.topic, this.zkHost,
                                            this.zkPort, this.groupId);
        return routeString;
    }
}
