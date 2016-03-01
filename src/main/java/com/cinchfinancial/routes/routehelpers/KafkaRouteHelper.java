package com.cinchfinancial.routes.routehelpers;

import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.language.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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
        String baseString = "kafka:%s:%d?topic=%s&zookeeperHost=%s&zookeeperPort=%d" +
                "&groupId=%s&serializerClass=kafka.serializer.StringEncoder" +
                "&partitioner=com.cinchfinancial.partitioners.SimplePartitioner";
        String routeString = String.format(baseString, this.kafkaHost,
                                            this.kafkaPort, this.topic, this.zkHost,
                                            this.zkPort, this.groupId);
        return routeString;
    }
    /*
    @Bean
    @Override
    public HashMap<String, Object> setConstants(String fieldName, String fieldValue) throws Exception {
        HashMap<String, Object> headerMap = new HashMap<String, Object>();
        Field fieldKey = KafkaConstants.class.getDeclaredField(fieldName);
        String fieldString = fieldKey.getName();
        headerMap.put(fieldString, fieldValue);
        return headerMap;


        Constructor<KafkaConstants> constructor = KafkaConstants.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        KafkaConstants kafkaConstants = constructor.newInstance(new Object[0]);
        return kafkaConstants;

    }
    */
}
