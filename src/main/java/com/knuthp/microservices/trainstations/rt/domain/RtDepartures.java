package com.knuthp.microservices.trainstations.rt.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RtDepartures {

	private String placeId;
	private List<RtStop> rtStopList;
	
	public RtDepartures() {
		rtStopList = new ArrayList<RtStop>();
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public void addStop(RtStop rtStop) {
		rtStopList.add(rtStop);
	}

	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
