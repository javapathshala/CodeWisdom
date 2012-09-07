/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.application.common;

/**
 * @author dimit.chadha
 * 
 */
public interface TechConstants {

	char TEXT_DELIMITER = '.';

	String STATUS_ACTIVE = "A";
	String STATUS_LOCK = "L";
	String STATUS_INACTIVE = "I";

	String ERRORS = "errors";
	String MESSAGE = "message";
	String BLANK = "";
	int INT_ZERO = 0;

	String ENTER_METHOD = "Entering Method :: ";
	String EXIT_METHOD = "Exiting Method :: ";

	String LOGGER_FILE = "log4j.properties";
	String LINE_SEPARATOR = System.getProperty("ine.separator");

}
