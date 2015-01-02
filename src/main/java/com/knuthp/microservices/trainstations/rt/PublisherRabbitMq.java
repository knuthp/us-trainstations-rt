package com.knuthp.microservices.trainstations.rt;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class PublisherRabbitMq implements Publisher {
	private static final Logger logger = LoggerFactory
			.getLogger(PublisherRabbitMq.class);
	private static final String EXCHANGE_NAME = "logs";

	public PublisherRabbitMq() {
			logger.info("Publisher will publish to RabbitMQ");
	}

	@Override
	public void publishRtDepartures(RtDepartures rtDepartures) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqp://UITrHgLT:yGYKAijJ5OLNrY5ob0gXgFXN5oViPLGz@slow-vervain-44.bigwig.lshift.net:10923/ix2hR1tr2hmP");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
			String message = rtDepartures.toString();

			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
			logger.info("Published " + rtDepartures.getPlaceId());
			channel.close();
			connection.close();
		} catch (IOException | KeyManagementException | NoSuchAlgorithmException | URISyntaxException e) {
			logger.error("Failed to publish", e);
		}
	}
}
