package com.cinchfinancial;


import com.cinchfinancial.messages.MxMessage;
import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.CinchKafkaConsumer;
import com.cinchfinancial.routes.CinchKafkaProducer;
import com.cinchfinancial.routes.CinchMessageProducer;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaEndpoint;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CamelHelperApplication.class)
//@WebAppConfiguration
@IntegrationTest
//@ActiveProfiles("test")
public class CamelHelperApplicationTests {

	@Autowired
    CamelContext camelContext;

    @Autowired
    ProducerTemplate producerTemplate;

    CinchMessageProducer kafkaTopic1Producer;
    CinchMessageProducer kafkaTopic2Producer;
    CinchKafkaConsumer kafka1TopicConsumer;

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint endpoint;


	@Before
	public void before() throws Exception {
        kafkaTopic1Producer = new CinchKafkaProducer("test", "camelTest", producerTemplate);
        kafkaTopic2Producer = new CinchKafkaProducer("test2", "camelTest2", producerTemplate);
        kafka1TopicConsumer = new CinchKafkaConsumer("test", "camelTest", "mock:result");
		camelContext.addRoutes((RouteBuilder) kafkaTopic1Producer);
		camelContext.addRoutes((RouteBuilder) kafkaTopic2Producer);
        camelContext.addRoutes((RouteBuilder) kafka1TopicConsumer);
	}

	@Test
	public void testKafkaRoute () throws Exception{
        endpoint.expectedMessageCount(1);
		SimpleMessage simpleMessage = new MxMessage(99999, 1, "new mx message");
        kafkaTopic1Producer.send(simpleMessage);
        endpoint.assertIsSatisfied();
        Exchange exchange = endpoint.getExchanges().get(0);
        Object in = exchange.getIn().getBody();
        Assert.assertEquals(in.getClass(), MxMessage.class);

	}

}
