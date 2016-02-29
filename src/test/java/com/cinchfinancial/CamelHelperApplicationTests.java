package com.cinchfinancial;


import com.cinchfinancial.messages.MxMessage;
import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.consumers.CinchConsumer;
import com.cinchfinancial.routes.producers.CinchProducer;
import com.cinchfinancial.routes.CinchMessageProducer;
import com.cinchfinancial.routes.routehelpers.KafkaRouteHelper;
import com.cinchfinancial.routes.routehelpers.RabbitRouteHelper;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    CinchConsumer kafka1TopicConsumer;

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint endpoint;


	@Before
	public void before() throws Exception {
        kafkaTopic1Producer = new CinchProducer(new KafkaRouteHelper("test", "camelTest"), producerTemplate);
        kafkaTopic2Producer = new CinchProducer(new RabbitRouteHelper("test2", "direct", "camelTest2"), producerTemplate);
        kafka1TopicConsumer = new CinchConsumer(new KafkaRouteHelper("test", "camelTest"), "mock:result");
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
