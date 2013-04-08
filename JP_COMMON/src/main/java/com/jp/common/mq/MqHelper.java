package com.jp.common.mq;


import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.Context;

import com.jp.application.logger.TechLogger;


public class MqHelper implements MqConstants{
	
	private static TechLogger logger = TechLogger.getBaseLogger(MqHelper.class);
	private static MqHelper instance;
	private static ResourceBundle bundle;
	private String sendQueueName;
	private String recieveQueueName;
	private String dirtyQueueName;
	private String factory;
	private int ack;
	private boolean transacted;
	private Properties urlProperty;
	private HashMap<String,MqProperties> conProp;
	
	
	private MqHelper()  {
		super();
		initialise();
	}
	
	public final static synchronized MqHelper getInstance() {
		if (instance == null) {
			instance = new MqHelper();
		}
		return instance;
	}
	
	private void initialise() {
		logger.info("Initialising MQ");
		try {
			bundle=ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
			logger.info("Reading mq.properties");
			buildProperties(bundle);			
		}catch (MissingResourceException ms) {
			logger.error("mq.properties files missing", ms);
		}
	}
	
	private void buildProperties(ResourceBundle bundle) {
		contextUrl(bundle);
		sendQueueName=bundle.getString(MQ_IN);
		recieveQueueName=bundle.getString(MQ_OUT);
		dirtyQueueName=bundle.getString(MQ_DIRTY);
		ack=Integer.parseInt(bundle.getString(JMS_ACKNOWLEDGE_MODE));
		transacted=Boolean.valueOf(bundle.getString(JMS_TRANSACTED));
		populateProperties();
	}

	private void contextUrl(ResourceBundle bundle2) {
		StringBuffer url = new StringBuffer(IIOP);		    
	    String hostName = bundle.getString(HOST_NAME);
	    String portNumber = bundle.getString(PORT_NUMBER);
	    if(null==hostName || BLANK.equals(hostName)){
	    	hostName=System.getProperty(HOST_NAME);
	    	logger.info("Host Name read from -D option : "+hostName);
	    }
	    if(null==portNumber || BLANK.equals(portNumber)){
	    	portNumber=System.getProperty(PORT_NUMBER);
	    	logger.info("Port Number read from -D option : "+portNumber);
	    }		    
	    String initialContextFactory=bundle.getString(INITIAL_CONTEXT_FACTORY);
	    url.append(hostName).append(COLON_AFTER_IIOP).append(portNumber).append(SLASH_AFTER_PORT_NUMBER);
	    urlProperty.put(Context.PROVIDER_URL, url.toString());
	    urlProperty.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);		    
	    factory = bundle.getString(QUEUE_FACTORY);
	    logger.info("Hostname : "+hostName + "PortNumber : "+portNumber+ "URL :: "+url+"Connection factory : "+factory+"Initial Context Factory : "+initialContextFactory);	    		
	}
	
	private void populateProperties() {
		MqProperties mqProp =new MqProperties(urlProperty,sendQueueName,recieveQueueName,dirtyQueueName,ack,transacted);
		conProp = new HashMap<String,MqProperties>();
		conProp.put("properties", mqProp);
		
	}
}
