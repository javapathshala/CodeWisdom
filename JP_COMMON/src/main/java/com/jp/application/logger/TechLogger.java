/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.application.logger;

/**
 * @author dimit.chadha
 * 
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;

public class TechLogger extends Logger {

	private static String csFQCN;

	private static Class ccClazz;

	private static boolean cbFirstRun = true;

	private static final String LOG_PROPERTIES_FILE = "base_logging.properties";

	private Logger cLogger = null;

	protected TechLogger(String asFQCN) {
		super(asFQCN);
		cLogger = Logger.getLogger(ccClazz);
	}

	private static void initialize() {
		final String lsErrMsg = "Failed to load logging property ";
		Properties lLogProperties = new Properties();
		try {
			InputStream lIStream = ClassLoader.getSystemResourceAsStream(LOG_PROPERTIES_FILE);
			if (lIStream != null) {
				lLogProperties.load(lIStream);
				PropertyConfigurator.configure(lLogProperties);
			} else {
				throw new IOException(lsErrMsg);
			}
		} catch (IOException e) {
			System.out.println("Failed to load logging property. File " + LOG_PROPERTIES_FILE + " not found.");
		}
	}

	public static synchronized TechLogger getBaseLogger(Class acClassName) {
		if (cbFirstRun) {
			initialize();
			cbFirstRun = false;
		}
		ccClazz = acClassName;
		csFQCN = ccClazz.getName();
		TechLogger lBaseLogger = new TechLogger(csFQCN);
		return lBaseLogger;
	}

	public boolean isDebugEnabled() {
		return cLogger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return cLogger.isInfoEnabled();
	}

	public void debug(Object aobMessage) {
		cLogger.debug(aobMessage);
	}

	public void error(Object aobMessage) {
		cLogger.error(aobMessage);
	}

	public void error(Object aobMessage, Throwable aThrowable) {
		cLogger.error(aobMessage, aThrowable);
	}

	public void fatal(Object aobMessage) {
		cLogger.fatal(aobMessage);
	}

	public void info(Object aobMessage) {
		cLogger.info(aobMessage);
	}

	public void log(Priority aPriority, Object aobMessage) {
		cLogger.log(aPriority, aobMessage);
	}

	public void warn(Object aobMessage) {
		cLogger.warn(aobMessage);
	}

}
