package com.knuthp.microservices.trainstations.rt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;

public class PublisherLog implements Publisher {
	private static final Logger logger = LoggerFactory.getLogger(PublisherLog.class);
	@Override
	public void publishRtDepartures(RtDepartures rtDepartures) {
		logger.info("Publish to log: " + rtDepartures);
	}

}
