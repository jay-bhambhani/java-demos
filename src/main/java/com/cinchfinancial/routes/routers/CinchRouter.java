package com.cinchfinancial.routes.routers;

import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.routehelpers.KafkaRouteHelper;
import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

/**
 * Created by jbhambhani on 2/24/16.
 */
public class CinchRouter extends RouteBuilder {

    private String topic;
    private String groupId;

    public CinchRouter() {}

    public CinchRouter(String topic, String groupId) {
        this.topic = topic;
        this.groupId = groupId;
    }

    public void configure() {}

    public String router(RouteHelper routeHelper) {
        String routeString = routeHelper.setRouteString();
        return routeString;
    }

    public JacksonDataFormat getFormat() {
        JacksonDataFormat format = new JacksonDataFormat(SimpleMessage.class);
        format.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return format;
    }
}
