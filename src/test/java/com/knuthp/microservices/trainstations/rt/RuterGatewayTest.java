package com.knuthp.microservices.trainstations.rt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.knuthp.microservices.reisapi.model.MonitoredStopVisit;
import com.knuthp.microservices.reisapi.model.MonitoredVehicleJourney;
import com.knuthp.microservices.reisapi.model.Place;

@RunWith(MockitoJUnitRunner.class)
public class RuterGatewayTest {

	@Mock
	private HttpProxy httpProxyMock;
	private RuterGateway ruterGateway;

	@Test
	public void test() {
		String expectedJson = "{\"todo\" : \"some\"}";
		when(httpProxyMock.getUrlJson(endsWith("getdepartures/1"))).thenReturn(
				expectedJson);
		Place place = new Place("1");

		String departuresJson = ruterGateway.getDeparturesJson(place);

		assertEquals(expectedJson, departuresJson);
	}

	@Test
	public void testReadReisApiFromJSON() throws Exception {
		String myResource = IOUtils.toString(this.getClass()
				.getResourceAsStream("departures.json"));
		when(httpProxyMock.getUrlJson(endsWith("getdepartures/1"))).thenReturn(
				myResource);
		Place place = new Place("1");

		List<MonitoredStopVisit> departuresList = ruterGateway
				.getDepartures(place);

		assertEquals(45, departuresList.size());
		MonitoredVehicleJourney journey = departuresList.get(0)
				.getMonitoredVehicleJourney();
		assertEquals("L12", journey.getPublishedLineName());
		assertEquals("PT0S", journey.getDelay());
		assertEquals(false, journey.getMonitoredCall().isVehicleAtStop());
		assertEquals("525_2014-12-31_16:31", journey.getFramedVehicleJourneyRef().getDatedVehicleJourneyRef());
		
	}

	@Before
	public void setUp() {
		ruterGateway = new RuterGateway();
		ruterGateway.setHttpProxy(httpProxyMock);
	}

}
