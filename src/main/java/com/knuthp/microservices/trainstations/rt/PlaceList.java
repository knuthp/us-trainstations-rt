package com.knuthp.microservices.trainstations.rt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.knuthp.microservices.reisapi.model.Place;

@Component
public class PlaceList {
	private final List<Place> placeList;

	public PlaceList() {
		placeList = new ArrayList<Place>();
		Place kongsbergTrain = new Place("6049104");
		placeList.add(kongsbergTrain);
		
		Place drammenTrain = new Place("6021000");
		placeList.add(drammenTrain);
		
		Place askerTrain = new Place("2200500");
		placeList.add(askerTrain);
	}
	
	
	public List<Place> getPlaceList() {
		return placeList;
	}
}
