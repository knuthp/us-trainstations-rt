package com.knuthp.microservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knuthp.microservices.trainstations.rt.PlaceList;

@Controller
@RequestMapping(value="/place")
public class PlaceController {
	
	private PlaceList placeList;

	@Autowired
	public PlaceController(PlaceList placeList) {
		this.placeList = placeList;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET) 
	public String home(Locale locale, Model model) {
		model.addAttribute("placeList", placeList.getPlaceList());
		
		return "place";
	}

}
