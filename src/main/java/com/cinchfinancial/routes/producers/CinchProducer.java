package com.cinchfinancial.routes.producers;

import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.cinchfinancial.routes.routers.CinchRouter;
import org.apache.camel.ProducerTemplate;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by jbhambhani on 2/23/16.
 */
public class CinchProducer extends CinchRouter {
    private RouteHelper routeHelper;
    private ProducerTemplate kafkaProducer;
    private String uuid;

    public CinchProducer(RouteHelper routeHelper, ProducerTemplate kafkaProducer) {
        super(routeHelper);
        this.kafkaProducer = kafkaProducer;
        this.uuid = UUID.randomUUID().toString();

    }

    public void send(Object object) {
        this.kafkaProducer.sendBody("direct:" + this.uuid, object);   //, KafkaConstants.PARTITION_KEY, "1");
    }

    public void send(Object object, HashMap<String, Object> headerMap) {
        this.kafkaProducer.sendBodyAndHeaders("direct:" + this.uuid, object, headerMap);   //, KafkaConstants.PARTITION_KEY, "1");
    }

    /**
     * hitches routehelper to router to get "to" string for routing purposes
     */
    @Override
    public void configure() {
        from("direct:" + this.uuid).marshal(getJsonFormat()).convertBodyTo(String.class)
                .to(router())
                .to("log:sent?showAll=true");
    }


}
