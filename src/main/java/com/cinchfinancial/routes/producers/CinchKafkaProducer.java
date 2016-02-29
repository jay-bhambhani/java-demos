package com.cinchfinancial.routes.producers;

import com.cinchfinancial.routes.CinchMessageProducer;
import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.cinchfinancial.routes.routers.CinchRouter;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaConstants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by jbhambhani on 2/23/16.
 */
public class CinchKafkaProducer extends CinchRouter implements CinchMessageProducer {
    private RouteHelper routeHelper;
    private HashMap<String, Object> headerMap;
    private ProducerTemplate kafkaProducer;
    private String uuid;

    public CinchKafkaProducer(RouteHelper routeHelper, HashMap<String, Object> headerMap,
                              ProducerTemplate kafkaProducer) {
        super(routeHelper);
        this.headerMap = headerMap;
        this.kafkaProducer = kafkaProducer;
        this.uuid = UUID.randomUUID().toString();

    }

    @Override
    public void send(Object object) {
        this.kafkaProducer.sendBodyAndHeaders("direct:" + this.uuid, object, this.headerMap);   //, KafkaConstants.PARTITION_KEY, "1");
    }

    @Override
    public void configure() {
        from("direct:" + this.uuid).marshal(getFormat()).convertBodyTo(String.class)
                .to(router())
                .to("log:sent?showAll=true");
    }


}
