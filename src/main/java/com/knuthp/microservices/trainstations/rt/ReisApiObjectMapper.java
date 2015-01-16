package com.knuthp.microservices.trainstations.rt;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class ReisApiObjectMapper extends ObjectMapper {
	private static final long serialVersionUID = 1L;

	public ReisApiObjectMapper() {
		super();
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);
	}
}
