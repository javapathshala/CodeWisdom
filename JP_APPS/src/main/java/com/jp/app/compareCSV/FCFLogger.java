package com.jp.app.compareCSV;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;


/**
 * Logger Implementation class
 * 
 * @author dimit.chadha
 * 
 */
public class FCFLogger extends Logger {

	public static Logger logger;

	private static boolean firstRun = true;

	/**
	 * private constructor It is a primary requirement of Singleton
	 */
	protected FCFLogger(String asFQCN) {
		super(asFQCN);
		logger = Logger.getLogger(asFQCN);
	}

	/**
	 * 
	 * @param clz
	 * @return Logger
	 */
	public static FCFLogger getLogger(Class<?> clz) {
		if (firstRun) {
			initialize();
			firstRun = false;
		}
		FCFLogger fcfLogger = new FCFLogger(clz.getName());
		return fcfLogger;
	}

	/**
	 * This method initializes the Logger by reading the log4j.properties file.
	 */
	private static void initialize() {
		final String lsErrMsg = "Failed to load logging property ";
		Properties lLogProperties = new Properties();
		try {
			ClassLoader loader = FCFLogger.class.getClassLoader();
			URL url = loader.getResource("log4j.properties");
			if (null != url) {
				lLogProperties.load(url.openStream());
				PropertyConfigurator.configure(lLogProperties);
			} else {
				throw new IOException(lsErrMsg);
			}
		} catch (IOException e) {
			System.err.println("Failed to load logging property. File " + "log4j.properties"
					+ " not found.");
		}
	}

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public void debug(Object aobMessage) {
		logger.debug(aobMessage);
	}

	public void error(Object aobMessage) {
		logger.error(aobMessage);
	}

	public void error(Object aobMessage, Throwable aThrowable) {
		logger.error(aobMessage, aThrowable);
	}

	public void fatal(Object aobMessage) {
		logger.fatal(aobMessage);
	}

	public void info(Object aobMessage) {
		logger.info(aobMessage);
	}

	public void log(Priority aPriority, Object aobMessage) {
		logger.log(aPriority, aobMessage);
	}

	public void warn(Object aobMessage) {
		logger.warn(aobMessage);
	}

	public void entry(String methodSign) {
		logger.info("Entering Method " + methodSign);
	}

	public void exit(String methodSign) {
		logger.info("Existing Method " + methodSign);
	}

}
