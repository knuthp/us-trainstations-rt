package com.knuthp.microservices.trainstations.rt;

import static org.mockito.Mockito.*;

import org.apache.commons.io.IOUtils;
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

	@Test
	public void test() throws Exception {
		PlaceList placeList = new PlaceList();
		RuterGateway ruterGateway = new RuterGateway();
		ruterGateway.setHttpProxy(httpProxyMock);
		String myResource = IOUtils.toString(this.getClass()
				.getResourceAsStream("departures.json"));
		when(httpProxyMock.getUrlJson(anyString())).thenReturn(
				myResource);
		Poller poller = new Poller(placeList, ruterGateway, publisher);
		
		poller.pollStations();
		
		verify(publisher, times(3)).publishRtDepartures(any(RtDepartures.class));
	}

}
