package com.knuthp.microservices.trainstations.rt;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;

@RunWith(MockitoJUnitRunner.class)
public class PollerTest {
	
	@Mock 
	public HttpProxy httpProxyMock;
	@Mock 
	public Publisher publisher;
	private PlaceList placeList;
	private RuterGateway ruterGateway;
	private Poller poller;

	@Test
	public void test() throws Exception {
		poller.pollStations();
		
		verify(publisher, times(3)).publishRtDepartures(any(RtDepartures.class));
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
		String myResource = IOUtils.toString(this.getClass()
				.getResourceAsStream("departures.json"));
		when(httpProxyMock.getUrlJson(anyString())).thenReturn(
				myResource);
		poller = new Poller(placeList, ruterGateway, publisher);
	}

}
