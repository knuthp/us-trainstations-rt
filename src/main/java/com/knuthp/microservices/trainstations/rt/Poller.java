package com.knuthp.microservices.trainstations.rt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;
import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtStop;

@Component
@EnableScheduling
public class Poller {
	private static final Logger logger = LoggerFactory.getLogger(Poller.class);
	private Map<Place, RtDepartures> cache;
	
	@Autowired
	private PlaceList placeList;
	@Autowired
	private RuterGateway ruterGateway;
	@Autowired
	private Publisher publisher;
	
	public Poller() {
		cache = new HashMap<Place, RtDepartures>();
	}

	public Poller(PlaceList placeList, RuterGateway ruterGateway, Publisher publisher) {
		this.placeList = placeList;
		this.ruterGateway = ruterGateway;
		this.publisher = publisher;
		cache = new HashMap<Place, RtDepartures>();
	}

	
	@Scheduled(fixedRate = 1000)
	public void pollStations() throws Exception {
		for (Place place : placeList.getPlaceList()) {
			logger.debug("Polling: " + place.getId());
			List<MonitoredStopVisit> departures = ruterGateway.getDepartures(place);
			RtDepartures rtDepartures = createDomainObject(place, departures);
			
			RtDepartures cachedDepartures = cache.get(place);
			if (rtDepartures != null && (cachedDepartures == null || !cachedDepartures.equals(rtDepartures))) {			
				logger.info("Updated data for " + place);
				publisher.publishRtDepartures(rtDepartures);
				cache.put(place, rtDepartures);
			} else {
				logger.debug("Equal data for " + place);
			}
		}
	}


	private RtDepartures createDomainObject(Place place,
			List<MonitoredStopVisit> departures) {
		RtDepartures rtDepartures = new RtDepartures();
		rtDepartures.setPlaceId(place.getId());
		for (MonitoredStopVisit monitoredStopVisit : departures) {
			RtStop rtStop = new RtStop();
			rtStop.setPublishedLineName(monitoredStopVisit.getMonitoredVehicleJourney().getPublishedLineName());
			rtStop.setMonitored(monitoredStopVisit.getMonitoredVehicleJourney().isMonitored());
			rtStop.setVehicleAtStop(monitoredStopVisit.getMonitoredVehicleJourney().getMonitoredCall().isVehicleAtStop());
			rtDepartures.addStop(rtStop);
		}
		return rtDepartures;
	}
	
	
}
