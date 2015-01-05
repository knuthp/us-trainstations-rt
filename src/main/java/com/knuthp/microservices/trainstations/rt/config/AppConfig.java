package com.knuthp.microservices.trainstations.rt.config;

import java.net.URI; 
import java.net.URISyntaxException;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
	private static final String DEFAULT_AMQP_TX = "amqp://UITrHgLT:yGYKAijJ5OLNrY5ob0gXgFXN5oViPLGz@slow-vervain-44.bigwig.lshift.net:10922/ix2hR1tr2hmP";
	private static final String EXCHANGE_NAME = "train.stations.rt";

	@Bean
	public ConnectionFactory connectionFactory() {
		final URI rabbitMqUrl;
		try {
			rabbitMqUrl = new URI(DEFAULT_AMQP_TX);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		CachingConnectionFactory cf = new CachingConnectionFactory();
		cf.setUsername(rabbitMqUrl.getUserInfo().split(":")[0]);
		cf.setPassword(rabbitMqUrl.getUserInfo().split(":")[1]);
		cf.setHost(rabbitMqUrl.getHost());
		cf.setPort(rabbitMqUrl.getPort());
		cf.setVirtualHost(rabbitMqUrl.getPath().substring(1));
		return cf;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
		rabbitAdmin.declareExchange(fanoutExchange());
		return rabbitAdmin;
	}

	@Bean
	public AmqpTemplate amqpTemplate() {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
		rabbitTemplate.setExchange(fanoutExchange().getName());
		return rabbitTemplate;
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(EXCHANGE_NAME);
	}
}
