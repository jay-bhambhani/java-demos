package com.cinchfinancial.configuration;

import com.cinchfinancial.routes.CinchKafkaConsumer;
import com.cinchfinancial.routes.CinchKafkaProducer;
import com.cinchfinancial.routes.CinchMessageProducer;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by jbhambhani on 2/23/16.
 */
@Configuration
public class CamelRouteConfig {

    /*

    @Autowired
    private ProducerTemplate producerTemplate;

    @Bean
    public CinchMessageProducer kafkaTopic1Producer() {
        return new CinchKafkaProducer("test", "camelTest", producerTemplate);
    }

    @Bean
    public CinchMessageProducer kafkaTopic2Producer() {
        return new CinchKafkaProducer("test2", "camelTest2", producerTemplate);
    }

    @Bean
    public CinchKafkaConsumer kafka1TopicConsumer() {
        return new CinchKafkaConsumer("test", "camelTest", "log:gotakafka1message");
    }

    */

}
