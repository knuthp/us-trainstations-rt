package com.knuthp.microservices.trainstations.rt;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*; 

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.knuthp.microservices.trainstations.rt.domain.CurrentDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtStop;

@RunWith(MockitoJUnitRunner.class)
public class PollerTest {
	
	@Mock 
	public HttpProxy httpProxyMock;
	@Mock 
	public Publisher publisher;
	@Captor 
	public ArgumentCaptor<RtDepartures> argument;
	private PlaceList placeList;
	private RuterGateway ruterGateway;
	private Poller poller;

	@Test
	public void testPollsAllStations() throws Exception {
		poller.pollStations();
		
		verify(publisher, times(3)).publishRtDepartures(any(RtDepartures.class));
	}


	@Test
	public void testConvertsToRtDepartures() throws Exception {
		poller.pollStations();
		
		verify(publisher, times(3)).publishRtDepartures(argument.capture());
		RtDepartures rtDepartures = argument.getAllValues().get(0);
		assertEquals("6049104", rtDepartures.getPlaceId());
		assertEquals(45, rtDepartures.getRtStopList().size());
		RtStop rtStop = rtDepartures.getRtStopList().get(0);
		assertEquals("L12", rtStop.getPublishedLineName());
		assertEquals(true, rtStop.isMonitored());
		assertEquals(false, rtStop.isVehicleAtStop());
		assertEquals("525_2014-12-31_16:31", rtStop.getJourneyId());
		assertEquals("PT0S", rtStop.getDelay());
		assertEquals("2014-12-31T17:28:47+01:00", rtStop.getExpectedArrivalTime().toString());
		assertEquals("2014-12-31T17:30+01:00", rtStop.getExpectedDepartureTime().toString());
	}

	
	
	@Test
	public void testPollingTwiceWithSameDataOnlyInvokesOnce() throws Exception {
		poller.pollStations();
		poller.pollStations();
		
		verify(publisher, times(3)).publishRtDepartures(any(RtDepartures.class));
	}



	@Before
	public void setUp() throws IOException {
		placeList = new PlaceList();
		ruterGateway = new RuterGateway();
		ruterGateway.setHttpProxy(httpProxyMock);
		String myResource = IOUtils.toString(this.getClass().getClassLoader()
				.getResourceAsStream("departures.json"));
		when(httpProxyMock.getUrlJson(anyString())).thenReturn(
				myResource);
		CurrentDepartures currentDepartures = new CurrentDepartures();
		poller = new Poller(placeList, ruterGateway, publisher, currentDepartures);
	}

}
