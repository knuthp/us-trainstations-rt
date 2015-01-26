package com.knuthp.microservices.trainstations.rt.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FixPolledRtDepartures {
	public static final Logger LOG = LoggerFactory
			.getLogger(FixPolledRtDepartures.class);

	public void fix(RtDepartures rtDepartures) {
		LOG.info("Fixing: {}", rtDepartures.getPlaceId());
		for (RtStop rtStop : rtDepartures.getRtStopList()) {
			if (rtStop.getPublishedLineName() == null
					|| rtStop.getPublishedLineName().isEmpty()) {
				if ("Eidsvoll".equalsIgnoreCase(rtStop.getDestinationName())
						|| "Skien"
								.equalsIgnoreCase(rtStop.getDestinationName())) {
					rtStop.setPublishedLineName("R11");
					LOG.info("Fixing: R11 {}", rtStop.getDestinationName());
				} else if ("Drammen".equalsIgnoreCase(rtStop
						.getDestinationName())
						|| "Lillehammer".equalsIgnoreCase(rtStop
								.getDestinationName())) {
					rtStop.setPublishedLineName("R10");
					LOG.info("Fixing: R10 {}", rtStop.getDestinationName());
				}
			}
		}
	}

}
