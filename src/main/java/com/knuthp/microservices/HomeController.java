package com.knuthp.microservices;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.knuthp.microservices.trainstations.rt.PlaceList;
import com.knuthp.microservices.trainstations.rt.domain.CurrentDepartures;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	private PlaceList placeList;
	private CurrentDepartures currentDepartures;

	@Autowired
	public HomeController(PlaceList placeList,
			CurrentDepartures currentDepartures) {
		this.placeList = placeList;
		this.currentDepartures = currentDepartures;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("numberOfPlaces", placeList.getPlaceList().size());
		model.addAttribute("places", placeList.getPlaceList());
		model.addAttribute("currentDepartures",
				currentDepartures.getDepartures());

		return "home";
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset(String value) {
		logger.info("Reset");
		currentDepartures.reset();
		return "redirect:/";
	}
}
