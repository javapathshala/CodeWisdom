/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.tech.rs.ws.crud;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dimit.chadha
 * 
 */
public enum TodoDao {
	instance;

	private Map<String, Todo> contentProvider = new HashMap<String, Todo>();

	private TodoDao() {

		Todo todo = new Todo("1", "Learn REST");
		todo.setDescription("Read http://www.vogella.de/articles/REST/article.html");
		contentProvider.put("1", todo);
		todo = new Todo("2", "Do something");
		todo.setDescription("Read complete http://www.vogella.de");
		contentProvider.put("2", todo);

	}

	public Map<String, Todo> getModel() {
		return contentProvider;
	}
}
