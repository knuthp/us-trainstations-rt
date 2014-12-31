package com.knuthp.microservices.reisapi.model;

public class MonitoredVehicleJourney {
	private String publishedLineName;
	private boolean monitored;
	private String delay;
	private MonitoredCall monitoredCall;
	
	
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
	public MonitoredCall getMonitoredCall() {
		return monitoredCall;
	}
	public void setMonitoredCall(MonitoredCall monitoredCall) {
		this.monitoredCall = monitoredCall;
	}
}
