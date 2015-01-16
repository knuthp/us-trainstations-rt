package com.knuthp.microservices.reisapi.model;

public class MonitoredCall {
	private String expectedArrivalTime;
	private String expectedDepartureTime;
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


	public boolean isVehicleAtStop() {
		return vehicleAtStop;
	}

	public void setVehicleAtStop(boolean vehicleAtStop) {
		this.vehicleAtStop = vehicleAtStop;
	}
}
