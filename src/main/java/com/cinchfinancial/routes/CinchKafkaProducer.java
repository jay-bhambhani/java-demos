package com.cinchfinancial.routes;

import com.cinchfinancial.messages.SimpleMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.RouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Created by jbhambhani on 2/23/16.
 */
public class CinchKafkaProducer extends RouteBuilder implements CinchMessageProducer{
    private String topic;
    private String groupId;
    private ProducerTemplate kafkaProducer;
    private String uuid;

    public CinchKafkaProducer(String topic, String groupId, ProducerTemplate kafkaProducer) {
        this.topic = topic;
        this.groupId = groupId;
        this.kafkaProducer = kafkaProducer;
        this.uuid = UUID.randomUUID().toString();

    }

    @Override public void send(Object object) {
        this.kafkaProducer.sendBodyAndHeader("direct:" + this.uuid, object, KafkaConstants.PARTITION_KEY, "1");
    }

    @Override
    public void configure() {
        from("direct:" + this.uuid).marshal(getFormat()).convertBodyTo(String.class)
                .to(router())
                .to("log:sent?showAll=true");
    }


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
