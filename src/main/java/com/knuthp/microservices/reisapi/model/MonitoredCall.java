package com.knuthp.microservices.reisapi.model;

public class MonitoredCall {
	private String expectedArrivalTime;
	private String expectedDepartureTime;
	private String aimedArrivalTime;
	private String aimedDepartureTime;
	private boolean vehicleAtStop;
	
	public String getExpectedArrivalTime() {
		return expectedArrivalTime;
	}

	public void setExpectedArrivalTime(String expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
	}

	public String getExpectedDepartureTime() {
		return expectedDepartureTime;
	}

	public void setExpectedDepartureTime(String expectedDepartureTime) {
		this.expectedDepartureTime = expectedDepartureTime;
	}


	public String getAimedArrivalTime() {
		return aimedArrivalTime;
	}

	public void setAimedArrivalTime(String aimedArrivalTime) {
		this.aimedArrivalTime = aimedArrivalTime;
	}

	public String getAimedDepartureTime() {
		return aimedDepartureTime;
	}

	public void setAimedDepartureTime(String aimedDepartureTime) {
		this.aimedDepartureTime = aimedDepartureTime;
	}

	public boolean isVehicleAtStop() {
		return vehicleAtStop;
	}

	public void setVehicleAtStop(boolean vehicleAtStop) {
		this.vehicleAtStop = vehicleAtStop;
	}
}
