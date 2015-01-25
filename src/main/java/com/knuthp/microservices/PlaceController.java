package com.knuthp.microservices;

import java.util.Locale;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knuthp.microservices.reisapi.model.Place;
import com.knuthp.microservices.trainstations.rt.PlaceList;

@Controller
@RequestMapping(value="/place")
public class PlaceController {
	private static final Logger LOG = LoggerFactory.getLogger(PlaceController.class);
	
	private PlaceList placeList;

	@Autowired
	public PlaceController(PlaceList placeList) {
		this.placeList = placeList;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String home(Locale locale, Model model) {
		model.addAttribute("placeList", placeList.getPlaceList());
		LOG.info("Getting place page");
		return "place";
	}

	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String addPlace(Place place) {
		LOG.info("Adding place" + place);
		placeList.getPlaceList().add(place);
		return "redirect:/place/";

	}
}
