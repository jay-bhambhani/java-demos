package com.cinchfinancial.routes;

import com.cinchfinancial.messages.SimpleMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * Created by jbhambhani on 2/24/16.
 */
public class CinchKafkaRouter extends RouteBuilder {

    private String topic;
    private String groupId;

    public CinchKafkaRouter() {}

    public CinchKafkaRouter(String topic, String groupId) {
        this.topic = topic;
        this.groupId = groupId;
    }

    public void configure() {}

    public String router() {
        KafkaRouteHelper kafkaRouteHelper = new KafkaRouteHelper(this.topic, this.groupId);
        String routeString = kafkaRouteHelper.setRouteString();
        return routeString;
    }

    public JacksonDataFormat getFormat() {
        JacksonDataFormat format = new JacksonDataFormat(SimpleMessage.class);
        format.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return format;
    }
}
