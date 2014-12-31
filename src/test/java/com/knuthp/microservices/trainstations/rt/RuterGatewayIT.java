package com.knuthp.microservices.trainstations.rt;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.Place;

public class RuterGatewayIT {

	private RuterGateway ruterGateway;
	private Place asker;

	@Test
	public void testGetDeparturesJson() {
		String result = ruterGateway.getDeparturesJson(asker);

		assertThat(result, startsWith("[{\"RecordedAtTime\":\""));
	}

	@Test
	public void testGetDepartures() throws Exception {
		List<MonitoredStopVisit> result = ruterGateway.getDepartures(asker);

		assertEquals(25, result.size());
	}

	@Before
	public void setUp() {
		asker = new Place("2200500");
		ruterGateway = new RuterGateway();
	}

}
