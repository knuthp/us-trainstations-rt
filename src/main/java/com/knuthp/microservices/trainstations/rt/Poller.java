package com.knuthp.microservices.trainstations.rt;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;
import com.knuthp.microservices.trainstations.rt.domain.CurrentDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtStop;

@Component
@EnableScheduling
public class Poller {
	private static final Logger logger = LoggerFactory.getLogger(Poller.class);
	@Autowired
	private CurrentDepartures cache;
	@Autowired
	private PlaceList placeList;
	@Autowired
	private RuterGateway ruterGateway;
	@Autowired
	private Publisher publisher;

	public Poller() {
	}

	public Poller(PlaceList placeList, RuterGateway ruterGateway,
			Publisher publisher, CurrentDepartures cache) {
		this.placeList = placeList;
		this.ruterGateway = ruterGateway;
		this.publisher = publisher;
		this.cache = cache;
	}

	@Scheduled(fixedRate = 1000)
	public void pollStations() throws Exception {
		for (Place place : placeList.getPlaceList()) {
			logger.debug("Polling: " + place.getId());
			List<MonitoredStopVisit> departures = ruterGateway
					.getDepartures(place);
			RtDepartures rtDepartures = createDomainObject(place, departures);

			boolean newStatus = cache.updateStation(place, rtDepartures);
			if (newStatus) {
				publisher.publishRtDepartures(rtDepartures);
			}
		}
	}

	private RtDepartures createDomainObject(Place place,
			List<MonitoredStopVisit> departures) {
		RtDepartures rtDepartures = new RtDepartures();
		rtDepartures.setPlaceId(place.getId());
		for (MonitoredStopVisit monitoredStopVisit : departures) {
			RtStop rtStop = new RtStop();
			rtStop.setPublishedLineName(monitoredStopVisit
					.getMonitoredVehicleJourney().getPublishedLineName());
			rtStop.setMonitored(monitoredStopVisit.getMonitoredVehicleJourney()
					.isMonitored());
			rtStop.setVehicleAtStop(monitoredStopVisit
					.getMonitoredVehicleJourney().getMonitoredCall()
					.isVehicleAtStop());
			rtStop.setExpectedArrivalTime(OffsetDateTime
					.parse(monitoredStopVisit.getMonitoredVehicleJourney()
							.getMonitoredCall().getExpectedArrivalTime()));
			rtStop.setExpectedDepartureTime(OffsetDateTime
					.parse(monitoredStopVisit.getMonitoredVehicleJourney()
							.getMonitoredCall().getExpectedDepartureTime()));
			rtStop.setAimedArrivalTime(OffsetDateTime.parse(monitoredStopVisit
					.getMonitoredVehicleJourney().getMonitoredCall()
					.getAimedArrivalTime()));
			rtStop.setAimedDepartureTime(OffsetDateTime
					.parse(monitoredStopVisit.getMonitoredVehicleJourney()
							.getMonitoredCall().getAimedDepartureTime()));
			rtStop.setDelay(monitoredStopVisit.getMonitoredVehicleJourney()
					.getDelay());
			rtStop.setDestinationName(monitoredStopVisit.getMonitoredVehicleJourney().getDestinationName());
			if (monitoredStopVisit.getMonitoredVehicleJourney()
					.getFramedVehicleJourneyRef() != null) {
				rtStop.setJourneyId(monitoredStopVisit
						.getMonitoredVehicleJourney()
						.getFramedVehicleJourneyRef()
						.getDatedVehicleJourneyRef());
			}
			rtDepartures.addStop(rtStop);
		}
		return rtDepartures;
	}

}
