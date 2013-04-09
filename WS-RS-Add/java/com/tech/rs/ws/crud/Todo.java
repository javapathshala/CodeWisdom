/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.tech.rs.ws.crud;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author dimit.chadha
 * 
 *         Create the following data model and a Singleton which serves as the
 *         data provider for the model. We use the implementation based on an
 *         enumeration. Please see the link for details. The Todo class is
 *         annotated with a JAXB annotation
 */
@XmlRootElement
public class Todo {
	private String id;
	private String summary;
	private String description;

	public Todo() {

	}

	public Todo(String id, String summary) {
		this.id = id;
		this.summary = summary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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