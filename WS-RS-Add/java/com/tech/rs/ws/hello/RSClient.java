/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.tech.rs.ws.hello;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * @author dimit.chadha
 *
 */
public class RSClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		// Fluent interfaces
		ClientResponse clientResponse = service.path("rest").path("hello").accept(
				MediaType.TEXT_PLAIN).get(ClientResponse.class);
		System.out.println(clientResponse.getStatus());
		// Get plain text
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_PLAIN).get(String.class));
		// Get XML
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_XML).get(String.class));
		// The HTML
		System.out.println(service.path("rest").path("hello").accept(
				MediaType.TEXT_HTML).get(String.class));

	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/WS-RS-Add").build();
	}

}
