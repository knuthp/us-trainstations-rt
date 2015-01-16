package com.knuthp.microservices.trainstations.rt.domain;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RtStop {

	private boolean monitored;
	private String publishedLineName;
	private boolean vehicleAtStop;
	private String journeyId;
	private String delay;
	private OffsetDateTime expectedArrivalTime;
	private OffsetDateTime expectedDepartureTime;

	public boolean isMonitored() {
		return monitored;
	}

	public void setMonitored(boolean monitored) {
		this.monitored = monitored;
	}

	public String getPublishedLineName() {
		return publishedLineName;
	}

	public void setPublishedLineName(String publishedLineName) {
		this.publishedLineName = publishedLineName;
	}

	public boolean isVehicleAtStop() {
		return vehicleAtStop;
	}

	public void setVehicleAtStop(boolean vehicleAtStop) {
		this.vehicleAtStop = vehicleAtStop;
	}

	public String getJourneyId() {
		return journeyId;
	}

	public void setJourneyId(String journeyId) {
		this.journeyId = journeyId;
	}

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	
	public OffsetDateTime getExpectedArrivalTime() {
		return expectedArrivalTime;
	}

	public void setExpectedArrivalTime(OffsetDateTime expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
	}

	public OffsetDateTime getExpectedDepartureTime() {
		return expectedDepartureTime;
	}

	public void setExpectedDepartureTime(OffsetDateTime expectedDepartureTime) {
		this.expectedDepartureTime = expectedDepartureTime;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
