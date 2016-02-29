package com.cinchfinancial.routes.routers;

import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.routehelpers.KafkaRouteHelper;
import com.cinchfinancial.routes.routehelpers.RouteHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by jbhambhani on 2/24/16.
 */
public class CinchRouter extends RouteBuilder {

    private RouteHelper routeHelper;
    //private String topic;
    //private String groupId;

    public CinchRouter() {}

    public CinchRouter(RouteHelper routeHelper) {
        this.routeHelper = routeHelper;
        //this.topic = topic;
        //this.groupId = groupId;
    }

    public void configure () {}

    public String router () {     //RouteHelper routeHelper) {
        String routeString = routeHelper.setRouteString();
        return routeString;
    }

    public JacksonDataFormat getFormat() {
        JacksonDataFormat format = new JacksonDataFormat(SimpleMessage.class);
        format.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return format;
    }

    /*
    @Bean
    public HashMap<String, Object> setConstants(String fieldName, Object fieldValue) throws Exception {
        HashMap<String, Object> headerMap = new HashMap<String, Object>();

        //Field fieldKey = KafkaConstants.class.getDeclaredField(fieldName);
        //String fieldString = fieldKey.get();
        headerMap.put(fieldName, fieldValue);
        return headerMap;


    }
    */
}
