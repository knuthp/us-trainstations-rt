package com.knuthp.microservices.trainstations.rt.domain;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.knuthp.microservices.reisapi.model.Place;

@Component
public class CurrentDepartures {
	private static final Logger logger = LoggerFactory
			.getLogger(CurrentDepartures.class);
	private Map<Place, RtDepartures> cache;

	public CurrentDepartures() {
		cache = new HashMap<Place, RtDepartures>();
	}

	public boolean updateStation(Place place, RtDepartures rtDepartures)
			throws Exception {
		RtDepartures cachedDepartures = cache.get(place);
		if (rtDepartures != null
				&& (cachedDepartures == null || !cachedDepartures
						.equals(rtDepartures))) {
			logger.info("Updated data for " + place);
			cache.put(place, rtDepartures);
			return true;
		} else {
			logger.debug("Equal data for " + place);
			return false;
		}
	}
	
	
	public RtDepartures getDepartures(Place place) {
		return cache.get(place);
	}
	
	public Map<Place, RtDepartures> getDepartures() {
		return new HashMap<Place, RtDepartures>(cache);
	}

}
