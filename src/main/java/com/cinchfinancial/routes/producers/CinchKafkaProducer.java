package com.cinchfinancial.routes.producers;

import com.cinchfinancial.routes.CinchMessageProducer;
import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.cinchfinancial.routes.routers.CinchRouter;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaConstants;

import java.util.UUID;

/**
 * Created by jbhambhani on 2/23/16.
 */
public class CinchKafkaProducer extends CinchRouter implements CinchMessageProducer {
    private RouteHelper routeHelper;
    private ProducerTemplate kafkaProducer;
    private String uuid;

    public CinchKafkaProducer(String topic, String groupId, ProducerTemplate kafkaProducer) {
        this.routeHelper = routeHelper;
        this.kafkaProducer = kafkaProducer;
        this.uuid = UUID.randomUUID().toString();

    }

    @Override
    public void send(Object object, String header, String headerValue) {
        this.kafkaProducer.sendBodyAndHeader("direct:" + this.uuid, object, header, headerValue);   //, KafkaConstants.PARTITION_KEY, "1");
    }

    @Override
    public void configure() {
        from("direct:" + this.uuid).marshal(getFormat()).convertBodyTo(String.class)
                .to(router(routeHelper))
                .to("log:sent?showAll=true");
    }


}
