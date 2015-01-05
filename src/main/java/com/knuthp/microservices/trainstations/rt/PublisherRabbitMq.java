package com.knuthp.microservices.trainstations.rt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;

@Component
public class PublisherRabbitMq implements Publisher {
	private static final Logger logger = LoggerFactory
			.getLogger(PublisherRabbitMq.class);
	private AmqpTemplate amqpTemplate;

	@Autowired(required = true)
	public PublisherRabbitMq(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
		logger.info("Publisher will publish to RabbitMQ");
	}

	@Override
	public void publishRtDepartures(RtDepartures rtDepartures) {
			amqpTemplate.convertAndSend(rtDepartures);
	}
}
