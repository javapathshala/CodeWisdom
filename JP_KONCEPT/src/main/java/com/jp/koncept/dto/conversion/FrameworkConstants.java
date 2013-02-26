/*
 * File: FrameworkConstants.java
 * Date: Dec 22, 2010
 *
 * This application is part of COBISCORP�S banking solutions.
 * Its unauthorized use is explicitly prohibited as is any
 * alteration or addition made by any of its users without due written
 * consent from COBISCORP.
 * This program is public by copyright law and by
 * international conventions of intellectual property.  Its unauthorized
 * use gives COBISCORP the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 */
package com.jp.koncept.dto.conversion;

/**
 * Description : Interface containing all the framework constants
 * @author dimit.chadha
 */
public interface FrameworkConstants {

	/* Special Characters as String */
	String PREFIX = "get";
	String GETCLASS = "getClass";
	String IS = "is";
	int ZERO = 0;

	String BLANK = "";

	String COLON = ":";

	String DASH = "-";

	String COMMA = ",";

	String SPACE = " ";

	String SLASH = "/";

	String NULL = "Null";

	String ZERO_STR = "0";

	String MINUS_ONE = "-1";

	String UNDERSCORE = "_";

	/**************** Logging Constants *********************/

	// Debug Log file key
	String LOG_DEBUG = "Debug";
	// Error Log file key
	String LOG_ERROR = "Error";
	// Trace Log file key
	String LOG_TRACE = "Trace";

	String LOG_INFO = "Info";
	String LOG_EXIT = "Exit";
	String LOG_ENTRY = "Entry";
	String LOG_WARNING = "Warning";
	String LOG_EXCEPTION = "Exception";

	// The Log4J property prefix
	String LOG4J_PREFIX = "log4j";

	// Module Specific Logging
	String MODULE_PREFIX = "module.";

	// Module level logging enabled or disabled
	String MODULE_ENABLED = "componentLoggingEnabled";

	String LOG_LEVEL = "com.ecobis.framework.logging.log4j.FrameworkLevel";

	String LOG4J_ROOT = LOG4J_PREFIX + ".rootLogger";

	String TRACE_FILE = "log4j.appender.Trace.File";

	String ERROR_FILE = "log4j.appender.Error.File";

	String SERVER_LOG_ROOT = "SERVER_LOG_ROOT";

	// Set the characters to print the log messages
	char USER_ID = 'a';
	char DOT_CHAR = '.';
	char SESSION_ID = 's';
	char METHOD_NAME = 'b';
	char COMPONENT_ID = 'r';
	char EXCEPTION_TYPE = 'e';

	String CULTURE_FILE = "properties/culture";

	String HOLIDAY_FILE = "properties/holidays";

	String MESSAGES_FILE = "properties/messages";

	String CATALOGS_FILE = "properties/catalogs";

	String EXCEPTION_FILE = "properties/exceptions";

	// Name of the properties file for logging
	String LOGGER_FILE = "properties/logger.properties";

	String NEW_LINE = System.getProperty("line.separator");

	String DEFAULT_LOCALE = "en_US";

	String DEFAULT_CODE = "US";

	String DEFAULT_LANG = "en";

	// Width of star is 60 column
	String STAR_LINE = "***********************************************************";

	String DOT_PATTERN = "[.]";

	String NO_EXCEPTION = "No Exception";

	String GETCLASSNAME = "getClassName";

	String THROWABLE_STR = "Throwable : ";

	String GETSTACKTRACE = "getStackTrace";

	String GETMETHODNAME = "getMethodName";

	String GETLINENUMBER = "getLineNumber";

	String FIN_AMOUNT = "Financial Amount";

	String FIN_CURRENCY = "Financial Currency";

	String TRACE_EXIT = "Exiting from method";

	String TRACE_ENTRY = "Entering into method";

	String LOCALE_CREATED = "Locale created for ";

	String DEF_PROG_COMMENTS = "DEF_PROG_COMMENTS";

	String ERROR_START = "****FORMATTER ERROR****";

	String STACKTRACE = "java.lang.StackTraceElement";

	String FIN_AMOUNT_ARRAY = "FinancialAmount array";

	String LOCINFO_FAILED = "LocationInfo failed using JDK 1.6 methods";

	String PRE_JDK_MSG = "LocationInfo will use pre-JDK 1.4 methods to determine location.";

	/********************* DATE and TIME Constants **************************/
	String DATE = "Date";
	String TIME = "Time";
	String ZONEID = "Zone Id";
	String PATTERN = "Pattern";
	String DATE_PATTERN = "Date/Pattern";
	String APPLICATION = "ApplicationException";
	String RESOURCE = "FrameworkMissingResourceException";
	String PROPERTY = "FrameworkMissingPropertyException";

	String DAYS = "DAYS";
	String SUNDAY = "SUNDAY";
	String MONDAY = "MONDAY";
	String TUESDAY = "TUESDAY";
	String WEDNESDAY = "WEDNESDAY";
	String THURSDAY = "THURSDAY";
	String FRIDAY = "FRIDAY";
	String SATURDAY = "SATURDAY";

	String ERROR = "ERROR";

}
