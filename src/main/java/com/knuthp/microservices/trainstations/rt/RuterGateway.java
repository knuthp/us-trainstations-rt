package com.knuthp.microservices.trainstations.rt;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;

@Component
public class RuterGateway {
	private static final String REISAPI = "reisapi.ruter.no";
	private static Logger LOG = LoggerFactory.getLogger(RuterGateway.class);
	private HttpProxy httpProxy;
	private ObjectMapper mapper;
	
	public RuterGateway() {
		setHttpProxy(new HttpProxy());
		mapper = new ReisApiObjectMapper();
	}

	public String getDeparturesJson(Place place) {
		String url = "http://" + REISAPI + "/stopvisit/getdepartures/"
				+ place.getId();
		return getHttpProxy().getUrlJson(url);
	}


	@SuppressWarnings("unchecked")
	public List<MonitoredStopVisit> getDepartures(Place place) throws Exception {
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
