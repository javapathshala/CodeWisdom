/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.jaxb;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * @author dimit.chadha
 * 
 */
public class JAXBClient {

	private static final String BOOKSTORE_XML = "./bookstore-jaxb.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		JAXBClient obj = new JAXBClient();
		obj.objectToXML();
		obj.XMLToObject();
	}

	private void objectToXML() throws JAXBException, IOException {
		ArrayList<Book> bookList = new ArrayList<Book>();

		// create books
		Book book1 = new Book();
		book1.setIsbn("978-0060554736");
		book1.setName("The Game");
		book1.setAuthor("Neil Strauss");
		book1.setPublisher("Harpercollins");
		bookList.add(book1);

		Book book2 = new Book();
		book2.setIsbn("978-3832180577");
		book2.setName("Feuchtgebiete");
		book2.setAuthor("Charlotte Roche");
		book2.setPublisher("Dumont Buchverlag");
		bookList.add(book2);

		// create bookstore, assigning book
		Bookstore bookstore = new Bookstore();
		bookstore.setName("Fraport Bookstore");
		bookstore.setLocation("Frankfurt Airport");
		bookstore.setBookList(bookList);
		// create JAXB context and instantiate marshaller
		JAXBContext context = JAXBContext.newInstance(Bookstore.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(bookstore, System.out);

		Writer w = null;
		try {
			w = new FileWriter(BOOKSTORE_XML);
			m.marshal(bookstore, w);
		} finally {
			try {
				w.close();
			} catch (Exception e) {
			}
		}

	}

	private void XMLToObject() throws JAXBException, IOException {
		System.out.println("Output from our XML File: ");
		JAXBContext context = JAXBContext.newInstance(Bookstore.class);
		Unmarshaller um = context.createUnmarshaller();
		Bookstore bookstore = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));
		for (int i = 0; i < bookstore.getBooksList().toArray().length; i++) {
			System.out.println("Book " + (i + 1) + ": " + bookstore.getBooksList().get(i).getName() + " from "
					+ bookstore.getBooksList().get(i).getAuthor() + " with publisher " + bookstore.getBooksList().get(i).getPublisher());
		}

	}

}
