/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.tech.rs.ws.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dimit.chadha
 * 
 */
@XmlRootElement
public class Todo {

	// JAX-RS supports an automatic mapping from JAXB annotated class to XML and
	// JSON
	// Isn't that cool?
	private String summary;
	private String description;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
