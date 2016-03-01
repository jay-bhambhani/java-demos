package com.cinchfinancial;

import com.cinchfinancial.messages.SimpleMessage;
import com.cinchfinancial.routes.producers.CinchProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelHelperApplication { //implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CamelHelperApplication.class, args);
	}

	@Autowired
	@Qualifier("kafkaTopic1Producer")
	CinchProducer messageProducer;

	public void run(String... args) throws InterruptedException, JsonProcessingException {
		SimpleMessage testMessage = new SimpleMessage("routers test message topic1");
		while (true) {
			messageProducer.send(testMessage);
			Thread.sleep(1000);
		}
	}

}