package com.cinchfinancial.configuration;

import com.cinchfinancial.routes.consumers.CinchConsumer;
import com.cinchfinancial.routes.producers.CinchProducer;
import com.cinchfinancial.routes.CinchMessageProducer;
import com.cinchfinancial.routes.routehelpers.KafkaRouteHelper;
import com.cinchfinancial.routes.routehelpers.RabbitRouteHelper;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * Created by jbhambhani on 2/23/16.
 */
@Configuration
public class CamelRouteConfig {


    @Autowired
    private ProducerTemplate producerTemplate;

    @Bean
    public CinchMessageProducer kafkaTopic1Producer() {
        HashMap<String, Object> headerMap = new HashMap<String, Object>();
        headerMap.put(KafkaConstants.DEFAULT_GROUP, "camelTest");
        headerMap.put(KafkaConstants.TOPIC, "test");
        return new CinchProducer(new KafkaRouteHelper("test", "camelTest"), producerTemplate);
    }

    @Bean
    public CinchMessageProducer rabbitTopic1Producer() {
        return new CinchProducer(new RabbitRouteHelper("test2", "direct", "camelTest2"), producerTemplate);
    }

    @Bean
    public CinchConsumer kafkaTopic1Consumer() {
        return new CinchConsumer(new KafkaRouteHelper("test", "camelTest"), "log:gotakafka1message");
    }

    @Bean
    public CinchConsumer rabbitTopic1Consumer() {
        return new CinchConsumer(new RabbitRouteHelper("test2", "direct", "camelTest2"), "log:gotarabbit1message");
    }



}
