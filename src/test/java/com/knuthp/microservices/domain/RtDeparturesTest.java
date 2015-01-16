package com.knuthp.microservices.domain;

import static org.junit.Assert.*;

import java.time.OffsetDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.knuthp.microservices.trainstations.rt.NsObjectMapper;
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
		rtStop.setDelay("PT60S");
		rtStop.setExpectedArrivalTime(OffsetDateTime
				.parse("2014-12-31T17:29:00+01:00"));
		rtDepartures.addStop(rtStop);
		ObjectMapper mapper = new NsObjectMapper();

		String result = mapper.writeValueAsString(rtDepartures);

		assertEquals("{\"placeId\":\"myPlaceId\",\"rtStopList\":["
				+ "{\"monitored\":false," + "\"publishedLineName\":\"L14\","
				+ "\"vehicleAtStop\":false," + "\"journeyId\":null,"
				+ "\"delay\":\"PT60S\","
				+ "\"expectedArrivalTime\":\"2014-12-31T17:29+01:00\","
				+ "\"expectedDepartureTime\":null,"
				+ "\"aimedArrivalTime\":null,"
				+ "\"aimedDepartureTime\":null}]}", result);
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
