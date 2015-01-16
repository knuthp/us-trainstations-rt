package com.knuthp.microservices.reisapi.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class MonitoredVehicleJourney {
	private String publishedLineName;
	private boolean monitored;
	private String delay;
	private String destinationName;
	private MonitoredCall monitoredCall;
	private FramedVehicleJourneyRef framedVehicleJourneyRef;
	
	
	public String getPublishedLineName() {
		return publishedLineName;
	}
	public void setPublishedLineName(String publishedLineName) {
		this.publishedLineName = publishedLineName;
	}
	public boolean isMonitored() {
		return monitored;
	}
	public void setMonitored(boolean monitored) {
		this.monitored = monitored;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public MonitoredCall getMonitoredCall() {
		return monitoredCall;
	}
	public void setMonitoredCall(MonitoredCall monitoredCall) {
		this.monitoredCall = monitoredCall;
	}
	
	
	public FramedVehicleJourneyRef getFramedVehicleJourneyRef() {
		return framedVehicleJourneyRef;
	}
	public void setFramedVehicleJourneyRef(FramedVehicleJourneyRef framedVehicleJourneyRef) {
		this.framedVehicleJourneyRef = framedVehicleJourneyRef;
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
