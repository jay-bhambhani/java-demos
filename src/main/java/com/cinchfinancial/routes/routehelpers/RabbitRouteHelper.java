package com.cinchfinancial.routes.routehelpers;

import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by jbhambhani on 2/26/16.
 */
public class RabbitRouteHelper implements RouteHelper {
    private String exchangeName;
    private String exchangeType;
    private String routingKey;
    private String username;
    private String password;

    @Value("localhost")
    private String hostname;

    @Value("5672")
    private int port;


    public RabbitRouteHelper(String exchangeName, String exchangeType, String routingKey,
                             String username, String password) {
        this.exchangeName = exchangeName;
        this.exchangeType = exchangeType;
        this.routingKey = routingKey;
        this.username = username;
        this.password = password;
        this.hostname = "localhost";
        this.port = 5672;
    }

    @Bean
    @Override
    public String setRouteString() {
        String baseString = "rabbitmq://%s:%d/%s?exchangeType=%s&routingKey=%s" +
                "&username=%s&password=%s";
        String routeString = String.format(baseString, this.hostname,
                this.port, this.exchangeName, this.exchangeType, this.routingKey,
                this.username, this.password);
        return routeString;
    }

    /*
    @Bean
    @Override
    public HashMap<String, Object> setConstants(String fieldName, String fieldValue) throws Exception {
        HashMap<String, Object> headerMap = new HashMap<String, Object>();
        Field fieldKey = RabbitMQConstants.class.getDeclaredField(fieldName);
        String fieldString = fieldKey.getName();
        headerMap.put(fieldString, fieldValue);
        return headerMap;
    }
    */
}
