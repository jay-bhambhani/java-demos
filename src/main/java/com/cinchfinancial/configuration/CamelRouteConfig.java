package com.cinchfinancial.configuration;

import com.cinchfinancial.routes.consumers.CinchKafkaConsumer;
import com.cinchfinancial.routes.producers.CinchKafkaProducer;
import com.cinchfinancial.routes.CinchMessageProducer;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jbhambhani on 2/23/16.
 */
@Configuration
public class CamelRouteConfig {

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



}
