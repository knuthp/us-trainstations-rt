package com.knuthp.microservices.trainstations.rt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Poller {
	private static final Logger logger = LoggerFactory.getLogger(Poller.class);

	@Scheduled(fixedRate = 5000)
	public void doLog() {
		logger.info("Scheduled logging");
	}
	
	
	
}
