package com.knuthp.microservices.domain;

import static org.junit.Assert.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knuthp.microservices.trainstations.rt.domain.RtDepartures;
import com.knuthp.microservices.trainstations.rt.domain.RtStop;

public class RtDeparturesTest {

	private ToStringStyle oldStyle;

	@Test
	public void testEquals() {
		RtDepartures rtDepartures1 = new RtDepartures();
		rtDepartures1.addStop(new RtStop());
		RtDepartures rtDepartures2 = new RtDepartures();
		rtDepartures2.addStop(new RtStop());
		
		assertEquals(rtDepartures1, rtDepartures2);
		assertEquals(rtDepartures1.hashCode(), rtDepartures2.hashCode());
		assertEquals(rtDepartures1.toString(), rtDepartures2.toString());
	}

	@Test
	public void testNotEquals() {
		RtDepartures rtDepartures1 = new RtDepartures();
		rtDepartures1.addStop(new RtStop());
		RtDepartures rtDepartures2 = new RtDepartures();
		RtStop rtStop2 = new RtStop();
		rtStop2.setPublishedLineName("NotEqual");
		rtDepartures2.addStop(rtStop2);
		
		assertFalse(rtDepartures1 == rtDepartures2);
		assertFalse(rtDepartures1.hashCode() == rtDepartures2.hashCode());
		assertFalse(rtDepartures1.toString() == rtDepartures2.toString());
	}

	@Test
	public void testToJson() throws Exception {
		RtDepartures rtDepartures = new RtDepartures();
		rtDepartures.setPlaceId("myPlaceId");
		RtStop rtStop = new RtStop();
		rtStop.setPublishedLineName("L14");
		rtDepartures.addStop(rtStop);
		ObjectMapper mapper = new ObjectMapper();
		
		String result = mapper.writeValueAsString(rtDepartures);
		
		assertEquals("{\"placeId\":\"myPlaceId\",\"rtStopList\":[{\"monitored\":false,\"publishedLineName\":\"L14\",\"vehicleAtStop\":false}]}", result);
	}
	
	
	@Before
	public void setUpClass() {
		oldStyle = ToStringBuilder.getDefaultStyle();
		ToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
	}

	
	@After
	public void tearDownClass() {
		ToStringBuilder.setDefaultStyle(oldStyle);
	}
}
