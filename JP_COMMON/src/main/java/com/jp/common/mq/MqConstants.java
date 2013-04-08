package com.jp.common.mq;

public interface MqConstants {

	public final static String INITIAL_CONTEXT_FACTORY = "INITIAL_CONTEXT_FACTORY";

	public static final String HOST_NAME = "DEFAULT.NAMESERVER.HOST_NAME";

	public static final String PORT_NUMBER = "DEFAULT.NAMESERVER.HOST_PORT_NUMBER";

	public final static String QUEUE_FACTORY = "MQ.QUEUE.CONNECTION.FACTORY";

	public final static String JMS_TRANSACTED = "MQ.JMS_TRANSACTED";

	public final static String JMS_ACKNOWLEDGE_MODE = "MQ.JMS_ACKNOWLEDGE_MODE";

	public final static String MQ_IN = "MQ.IN";

	public final static String MQ_OUT = "MQ.OUT";
	
	public final static String MQ_DIRTY = "MQ_DIRTY";

	public static final String BLANK = "";

	public static final String IIOP = "IIOP://";

	public static final String COLON_AFTER_IIOP = ":";

	public static final String SLASH_AFTER_PORT_NUMBER = "/";
	
	public static final String PROPERTIES_FILE_NAME="mq";
}
