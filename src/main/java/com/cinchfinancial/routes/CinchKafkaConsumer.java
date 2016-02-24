package com.cinchfinancial.routes;

import com.cinchfinancial.messages.SimpleMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.RouteDefinition;

/**
 * Created by jbhambhani on 2/23/16.
 */

public class CinchKafkaConsumer extends CinchKafkaRouter {
    private String topic;
    private String groupId;
    private String toUri;


    public CinchKafkaConsumer(String topic, String groupId, String toUri) {
        this.topic = topic;
        this.groupId = groupId;
        this.toUri = toUri;
    }

    @Override
    public void configure() {
        from(router())
                .unmarshal(getFormat())
                .to(this.toUri)
                .to("log:sent?showAll=true");
    }
}
