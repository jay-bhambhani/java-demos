package com.cinchfinancial.routes.routehelpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

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


}
