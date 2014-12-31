package com.knuthp.microservices.trainstations.rt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;

@Component
public class RuterGateway {
	private static final String REISAPI = "reisapi.ruter.no";
	private static Logger LOG = LoggerFactory.getLogger(RuterGateway.class);
	private HttpProxy httpProxy;
	
	public RuterGateway() {
		setHttpProxy(new HttpProxy());
	}

	public String getDeparturesJson(Place place) {
		String url = "http://" + REISAPI + "/stopvisit/getdepartures/"
				+ place.getId();
		return getHttpProxy().getUrlJson(url);
	}


	@SuppressWarnings("unchecked")
	public List<MonitoredStopVisit> getDepartures(Place place) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
		return (List<MonitoredStopVisit>) mapper.readValue(
				getDeparturesJson(place), new TypeReference<List<MonitoredStopVisit>>(){});
	}

	public HttpProxy getHttpProxy() {
		return httpProxy;
	}

	public void setHttpProxy(HttpProxy httpProxy) {
		this.httpProxy = httpProxy;
	}
}
