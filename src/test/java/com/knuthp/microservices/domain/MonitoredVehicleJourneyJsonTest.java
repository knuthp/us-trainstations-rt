package com.knuthp.microservices.domain;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knuthp.microservices.reisapi.model.MonitoredVehicleJourney;

public class MonitoredVehicleJourneyJsonTest {

	@Test
	public void testSimpleJsonConversion() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		MonitoredVehicleJourney re = mapper
				.readValue("{\"publishedLineName\" : \"L13\" }",
						MonitoredVehicleJourney.class);

		assertThat(re.getPublishedLineName(), is("L13"));
	}

}
