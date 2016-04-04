package com.cinchfinancial;


import com.cinchfinancial.messages.MxMessage;
import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.consumers.CinchConsumer;
import com.cinchfinancial.routes.producers.CinchProducer;
import com.cinchfinancial.routes.routehelpers.KafkaRouteHelper;
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
    /*

	@Autowired
    CamelContext camelContext;

    @Autowired
    ProducerTemplate producerTemplate;

    CinchProducer kafkaTopic1Producer;
    //CinchMessageProducer rabbitTopic2Producer;
    CinchConsumer kafka1TopicConsumer;
    //CinchConsumer rabbitTopic2Consumer;

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint endpoint;


	@Before
	public void before() throws Exception {
        kafkaTopic1Producer = new CinchProducer(new KafkaRouteHelper("test", "camelTest"), producerTemplate);
        //rabbitTopic2Producer = new CinchProducer(new RabbitRouteHelper("test2", "direct", "camelTest2"), producerTemplate);
        kafka1TopicConsumer = new CinchConsumer(new KafkaRouteHelper("test", "camelTest"), "mock:result");
		//rabbitTopic2Consumer = new CinchConsumer(new RabbitRouteHelper("test2", "direct", "camelTest2"), "mock:result");
        camelContext.addRoutes((RouteBuilder) kafkaTopic1Producer);
		//camelContext.addRoutes((RouteBuilder) rabbitTopic2Producer);
        camelContext.addRoutes((RouteBuilder) kafka1TopicConsumer);
        //camelContext.addRoutes((RouteBuilder) rabbitTopic2Consumer);
	}

	@Test
	public void testKafkaRoute () throws Exception{
        endpoint.expectedMessageCount(1);
		SimpleMessage simpleMessage = new MxMessage(99999, 1, "new mx message");
        SimpleMessage simpleMessage2 = new MxMessage(99999, 1, "new mx message");
        kafkaTopic1Producer.send(simpleMessage);
        //rabbitTopic2Producer.send(simpleMessage2);
        endpoint.assertIsSatisfied();
        Exchange exchange = endpoint.getExchanges().get(0);
        Object in = exchange.getIn().getBody();
        Assert.assertEquals(in.getClass(), MxMessage.class);

	}
*/
}
