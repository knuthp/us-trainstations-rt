package com.knuthp.microservices.trainstations.rt;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpProxy {
	private static Logger LOG = LoggerFactory.getLogger(HttpProxy.class);
	public String getUrlJson(String url) {
		try {
			InputStream in = new URL(url).openStream();
			return IOUtils.toString(in);
		} catch (MalformedURLException e) {
			LOG.error("Trouble with url: " + url, e);
		} catch (IOException e) {
			LOG.error("Trouble with IO: " + url, e);
		}
		return "";
	}

}
