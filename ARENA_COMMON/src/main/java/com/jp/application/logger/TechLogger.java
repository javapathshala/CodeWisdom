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
public class TechLogger {// extends Logger {
//
// public static Logger logger;
//
// private static boolean firstRun = true;
//
// /**
// * private constructor It is a primary requirement of Singleton
// */
// protected TechLogger(String asFQCN) {
// super(asFQCN);
// logger = Logger.getLogger(asFQCN);
// }
//
// /**
// *
// * @param clz
// * @return Logger
// */
// public static TechLogger getLogger(Class<?> clz) {
// if (firstRun) {
// initialize();
// firstRun = false;
// }
// TechLogger wsLogger = new TechLogger(clz.getName());
// return wsLogger;
// }
//
// /**
// * This method initializes the Logger by reading the log4j.properties file.
// */
// private static void initialize() {
// final String lsErrMsg = "Failed to load logging property ";
// Properties lLogProperties = new Properties();
// try {
// InputStream lIStream =
// ClassLoader.getSystemResourceAsStream(TechConstants.LOGGER_FILE);
// if (lIStream != null) {
// lLogProperties.load(lIStream);
// PropertyConfigurator.configure(lLogProperties);
// } else {
// throw new IOException(lsErrMsg);
// }
// } catch (IOException e) {
// System.err.println("Failed to load logging property. File " +
// TechConstants.LOGGER_FILE + " not found.");
// }
// }
//
// public boolean isDebugEnabled() {
// return logger.isDebugEnabled();
// }
//
// public boolean isInfoEnabled() {
// return logger.isInfoEnabled();
// }
//
// public void debug(Object aobMessage) {
// logger.debug(aobMessage);
// }
//
// public void error(Object aobMessage) {
// logger.error(aobMessage);
// }
//
// public void error(Object aobMessage, Throwable aThrowable) {
// logger.error(aobMessage, aThrowable);
// }
//
// public void fatal(Object aobMessage) {
// logger.fatal(aobMessage);
// }
//
// public void info(Object aobMessage) {
// logger.info(aobMessage);
// }
//
// public void log(Priority aPriority, Object aobMessage) {
// logger.log(aPriority, aobMessage);
// }
//
// public void warn(Object aobMessage) {
// logger.warn(aobMessage);
// }
//
// public void entry(String methodSign) {
// logger.info(TechConstants.ENTER_METHOD + methodSign);
// }
//
// public void exit(String methodSign) {
// logger.info(TechConstants.EXIT_METHOD + methodSign);
// }

}
