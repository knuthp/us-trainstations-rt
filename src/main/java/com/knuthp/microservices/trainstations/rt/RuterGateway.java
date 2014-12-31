package com.knuthp.microservices.trainstations.rt;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;

@Component
public class RuterGateway {
	private static final String REISAPI = "reisapi.ruter.no";
	private static Logger LOG = LoggerFactory.getLogger(RuterGateway.class);

	public String getDeparturesJson(Place place) {
		String url = "http://" + REISAPI + "/stopvisit/getdepartures/"
				+ place.getId();
		return getUrlJson(url);
	}

	private String getUrlJson(String url) {
		try {
			InputStream in = new URL(url).openStream();
			return IOUtils.toString(in);
		} catch (MalformedURLException e) {
			LOG.error("Trouble with url: " + url, e);
		} catch (IOException e) {
			LOG.error("Trouble with IO: " + url, e);
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<MonitoredStopVisit> getDepartures(Place place) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String url = "http://" + REISAPI + "/stopvisit/getdepartures/"
				+ place.getId();
		return (List<MonitoredStopVisit>) mapper.readValue(getUrlJson(url),
				List.class);
	}
}
