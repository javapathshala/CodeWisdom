/*
 * File: FetchFlights.java
 * Date: 05-Jul-2012
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.cleartrip.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;

import com.jp.cleartrip.dto.AirSearchResult;
import com.jp.cleartrip.dto.Flight;
import com.jp.cleartrip.dto.Flights;
import com.jp.cleartrip.dto.Segment;
import com.jp.cleartrip.dto.Segments;
import com.jp.cleartrip.dto.Solution;
import com.jp.cleartrip.dto.Solutions;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author dimit.chadha
 */
public class FetchFlights {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		// Client client = Client.create(config);

		ClientConfig clientConfig = new DefaultClientConfig();
		// clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
		// Boolean.TRUE);
		Client client = Client.create(clientConfig);
		WebResource service = client.resource(getBaseURI());

		MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
		queryParams.add("from", "LHR");
		queryParams.add("to", "SFO");
		queryParams.add("depart-date", "2012-10-18");
		queryParams.add("return-date", "2012-10-20");
		queryParams.add("adults", "1");
		queryParams.add("children", "0");
		queryParams.add("infants", "0");

		// from=BOM
		// to=DEL
		// depart-date=2012-07-07
		// return-date=2012-07-10
		// adults=2
		// children=1
		// infants=0

		System.out.println("#####################");

//		AirSearchResult airSearchResult = service.queryParams(queryParams).header("X-CT-API-KEY", "4d256d6a924266e0efc53008327e9038")
//				.accept(MediaType.APPLICATION_XML).get(AirSearchResult.class);

		ClientResponse clientResponse=service.queryParams(queryParams).header("X-CT-API-KEY", "4d256d6a924266e0efc53008327e9038").accept(MediaType.TEXT_XML).get(ClientResponse.class);
		
		
		String response = clientResponse.getEntity(String.class);
		System.out.println(response);
		
		
	
//		System.out.println(airSearchResult);
//
//		List<Object> solutions = airSearchResult.getSolutionsOrOnwardSolutionsOrReturnSolutions();
//
//		Solutions solutions1 = (Solutions) solutions.get(0);
//
//		List<Solution> solutionList = solutions1.getSolution();
//
//		for (Solution solution : solutionList) {
//			Flights flights = solution.getFlights();
//			List<Flight> flight2 = flights.getFlight();
//			for (Flight flight : flight2) {
//				Segments segments = flight.getSegments();
//				List<Segment> segment = segments.getSegment();
//				
//				for (Segment segment2 : segment) {
//					System.out.println("Departure Airport : "+segment2.getDepartureAirport());
//					System.out.println("Arrival Airport : "+segment2.getArrivalAirport());
//					System.out.println("Airline : "+segment2.getAirline());
//					System.out.println("Flight Number : "+segment2.getFlightNumber());
//					System.out.println("######################################");
//				}
//
//			}
//
//		}

		System.out.println("dsdas");

	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://api.staging.cleartrip.com/air/1.0/search").build();
	}

	// private static void XMLToObject(String xmlString) throws JAXBException,
	// IOException {
	// System.out.println("Output from our XML File: ");
	// JAXBContext context = JAXBContext.newInstance(Bookstore.class);
	// Unmarshaller um = context.createUnmarshaller();
	// Bookstore bookstore = (Bookstore) um.unmarshal(new InputSource(new
	// StringReader(xmlString)));
	// for (int i = 0; i < bookstore.getBooksList().toArray().length; i++) {
	// System.out.println("Book " + (i + 1) + ": " +
	// bookstore.getBooksList().get(i).getName() + " from "
	// + bookstore.getBooksList().get(i).getAuthor() + " with publisher " +
	// bookstore.getBooksList().get(i).getPublisher());
	// }
	//
	// }
}
