package com.jp.ejb.listener;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.application.logger.BaseLogger;
import com.ibm.mq.MQException;



/**
 * Bean implementation class for Enterprise Bean: MsgListener
 */
public class MsgListenerBean
	implements
		javax.ejb.MessageDrivenBean,
		javax.jms.MessageListener,ExceptionListener {

	private static final long serialVersionUID = 1L;
	private javax.ejb.MessageDrivenContext fMessageDrivenCtx;
	private final static String PROPERTIES_FILE_NAME = "mq";
	private final static String MQ_OUT = "MQ.OUT";
	private final static String QUEUE_FACTORY = "MQ.QUEUE.CONNECTION.FACTORY";
	private final static String JMS_TRANSACTED = "MQ.JMS_TRANSACTED";
	private final static String JMS_ACKNOWLEDGE_MODE = "MQ.JMS_ACKNOWLEDGE_MODE";
	private QueueConnection qConnection;    
	private QueueSession qSession;
	private QueueSender sender;
	private String queueName;
	private Queue queue;
    private static ResourceBundle bundle;
    private static BaseLogger logger = BaseLogger.getBaseLogger(MsgListenerBean.class);
    private static final String HOST_NAME = "DEFAULT.NAMESERVER.HOST_NAME";
	private static final String PORT_NUMBER = "DEFAULT.NAMESERVER.HOST_PORT_NUMBER";
	protected final static String INITIAL_CONTEXT_FACTORY="INITIAL_CONTEXT_FACTORY";
	private static final String BLANK ="";
	private static final String IIOP = "IIOP://";
	private static final String COLON_AFTER_IIOP = ":";
	private static final String SLASH_AFTER_PORT_NUMBER = "/";

	/**
	 * getMessageDrivenContext
	 */
	public javax.ejb.MessageDrivenContext getMessageDrivenContext() {
		return fMessageDrivenCtx;
	}

	/**
	 * setMessageDrivenContext
	 */
	public void setMessageDrivenContext(javax.ejb.MessageDrivenContext ctx) {
		fMessageDrivenCtx = ctx;
	}

	/**
	 * ejbCreate
	 */
	public void ejbCreate() {
	}

//	/**
//	 * onMessage
//	 */
//	public void onMessage(javax.jms.Message aMessage) {
//		logger.info("Listener Called: Message Picked Up by onMessage()");
//		String msgId = null;
//		try {
//			msgId = aMessage.getJMSMessageID();
//			logger.info("Jms Message ID : "+msgId);
//			logger.info("Jms Correleation ID : "+aMessage.getJMSCorrelationID());
//			if (aMessage instanceof TextMessage) {
//				String sMessage  = ((TextMessage) aMessage).getText();
//				logger.info("Jms Message picked up by Listener is  : "+sMessage);
//				aMessage.acknowledge();
//				String newMessage=sMessage+"-"+"BIB";
//				logger.info("Processing message recieved");
//				logger.info("New Message is :: " +newMessage);
//				sendToOutQueue(msgId,newMessage);
//			}
//		}
//		catch (JMSException e1) {
//			Throwable t=e1;
//			while(t!=null){
//				logger.error("Original Exception is : "+t.getMessage());
//				if(t instanceof JMSException){
//					JMSException je1=(JMSException)t;
//					logger.error("JMS Error Code : "+je1.getLinkedException());		
//					Exception lException = je1.getLinkedException();
//				    if (lException != null){
//				          logger.error("MQ Completion Code : " + lException.getMessage());
//				        }
//				}else if(t instanceof MQException){
//					MQException mqe=(MQException)t;
//					logger.error("Completion Code : "+mqe.completionCode);
//					logger.error("Reason Code : "+mqe.reasonCode);
//					logger.error(mqe.getMessage());						
//				}
//				t=t.getCause();
//			}						
//		}
//		catch (Exception e) {
//			logger.info("Cause is : "+e.getCause());
//			logger.info(e.getMessage());
//			e.printStackTrace();						
//		}
//	}
	
	/**
	 * onMessage
	 */
	public void onMessage(javax.jms.Message aMessage) {
		logger.info("Listener Called: Message Picked Up by onMessage()");
		String msgId = null;
		try {
			msgId = aMessage.getJMSMessageID();
			logger.info("Jms Message ID : "+msgId);
			logger.info("Jms Correleation ID : "+aMessage.getJMSCorrelationID());
			if (aMessage instanceof TextMessage) {
				String sMessage  = ((TextMessage) aMessage).getText();
				logger.info("Jms Message picked up by Listener is  : "+sMessage);
				aMessage.acknowledge();
				//String newMessage=sMessage+"-"+"BIB";
				logger.info("Processing message recieved");
				//logger.info("New Message is :: " +newMessage);
				//sendToOutQueue(msgId,newMessage);
				sendToOutQueue(msgId,sMessage);
			}
		}
		catch (JMSException e1) {
			Throwable t=e1;
			while(t!=null){
				logger.error("Original Exception is : "+t.getMessage());
				if(t instanceof JMSException){
					JMSException je1=(JMSException)t;
					logger.error("JMS Error Code : "+je1.getLinkedException());		
					Exception lException = je1.getLinkedException();
				    if (lException != null){
				          logger.error("MQ Completion Code : " + lException.getMessage());
				        }
				}else if(t instanceof MQException){
					MQException mqe=(MQException)t;
					logger.error("Completion Code : "+mqe.completionCode);
					logger.error("Reason Code : "+mqe.reasonCode);
					logger.error(mqe.getMessage());						
				}
				t=t.getCause();
			}						
		}
		catch (Exception e) {
			logger.info("Cause is : "+e.getCause());
			logger.info(e.getMessage());
			e.printStackTrace();						
		}
	}
	private void sendToOutQueue(String msgId, String newMessage) {
		initialise();
		logger.info("mq.properties initialised");
		logger.info("Calling makeConnection()");
		makeConnection();
		try {
			 sender = qSession.createSender(queue);
			 logger.info("Sending Recieved message to Out Queue (TEST.OUT) after processing. Message Changed to (originalMessage-BIB)");
			 Message message=qSession.createTextMessage(newMessage);
			 logger.info("New Jms Message is :: "+message);
			 logger.info("calling sendToOutQueue()");
			 sender.send(message);
			 logger.info("Messasge Send Successfully");
		} catch (JMSException e) {
			logger.info("Unable to Send Message");
			Throwable t=e;
			while(t!=null){
				logger.error("Original Exception is : "+t.getMessage());
				if(t instanceof JMSException){
					JMSException je1=(JMSException)t;
					logger.error("JMS Error Code : "+je1.getLinkedException());		
					Exception lException = je1.getLinkedException();
				    if (lException != null){
				          logger.error("MQ Completion Code : " + lException.getMessage());
				        }
				}else if(t instanceof MQException){
					MQException mqe=(MQException)t;
					logger.error("Completion Code : "+mqe.completionCode);
					logger.error("Reason Code : "+mqe.reasonCode);
					logger.error(mqe.getMessage());						
				}
				t=t.getCause();
			}						
		}finally{
			//Try to close the JMS resource
			try {
				if (sender != null) {
					sender.close();
                }
				if (qSession != null) {
					qSession.close();
				}
				if (qConnection != null) {
                    qConnection.close();
                }
			}catch(JMSException jmse){
				Throwable t=jmse;
				while(t!=null){
					logger.error("Original Exception is : "+t.getMessage());
					if(t instanceof JMSException){
						JMSException je1=(JMSException)t;
						logger.error("JMS Error Code : "+je1.getLinkedException());		
						Exception lException = je1.getLinkedException();
					    if (lException != null){
					          logger.error("MQ Completion Code : " + lException.getMessage());
					        }
					}else if(t instanceof MQException){
						MQException mqe=(MQException)t;
						logger.error("Completion Code : "+mqe.completionCode);
						logger.error("Reason Code : "+mqe.reasonCode);
						logger.error(mqe.getMessage());						
					}
					t=t.getCause();
				}						
			}
		}
		
	}
	
	private void makeConnection() {
		logger.info("Entered makeConnection()");
		try {
			bundle=ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
			//Contruct the context url			
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
		    String factory = bundle.getString(QUEUE_FACTORY);
		    String initialContextFactory=bundle.getString(INITIAL_CONTEXT_FACTORY);
		    url.append(hostName).append(COLON_AFTER_IIOP).append(portNumber).append(SLASH_AFTER_PORT_NUMBER);
		    Properties urlProperty=new Properties();
		    urlProperty.put(Context.PROVIDER_URL, url.toString());
		    urlProperty.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);		    
		    logger.info("Hostname : "+hostName + "PortNumber : "+portNumber+ "URL :: "+url+"Connection factory : "+factory+"Initial Context Factory : "+initialContextFactory);
		    logger.info("Creating Initial Context with App Server with above parameters : ");
		    //Initialize and create queue connection factory
		    Context ctx = new InitialContext(urlProperty);		    
			QueueConnectionFactory qConnectionFactory=(QueueConnectionFactory)ctx.lookup(factory);
			logger.info("Initial Context Created");
			queueName=bundle.getString(MQ_OUT);
			queue=(Queue)ctx.lookup(queueName);
			logger.info("Queue Name : "+ queueName );
			qConnection=qConnectionFactory.createQueueConnection();
			logger.info("Queue connection : "+ qConnection );
			logger.info("Queue Connection Established");
			int ack=Integer.parseInt(bundle.getString(JMS_ACKNOWLEDGE_MODE));
			boolean transacted=Boolean.valueOf(bundle.getString(JMS_TRANSACTED));
			logger.info("Creating Queue Session");
			qSession = qConnection.createQueueSession(transacted,ack);
			logger.info("Queue Session Established");
		}catch (MissingResourceException ms) {
			logger.error("mq.properties files missing", ms);
		}catch (NamingException naex) {
			logger.error("Promblem while creating initial Context: Naming Exception", naex);
		}catch (JMSException jms) {
			Throwable t=jms;
			while(t!=null){
				logger.error("Original Exception is : "+t.getMessage());
				if(t instanceof JMSException){
					JMSException je1=(JMSException)t;
					logger.error("JMS Error Code : "+je1.getLinkedException());		
					Exception lException = je1.getLinkedException();
				    if (lException != null){
				          logger.error("MQ Completion Code : " + lException.getMessage());
				        }
				}else if(t instanceof MQException){
					MQException mqe=(MQException)t;
					logger.error("Completion Code : "+mqe.completionCode);
					logger.error("Reason Code : "+mqe.reasonCode);
					logger.error(mqe.getMessage());						
				}
				t=t.getCause();
			}						
		}
	}

	private void initialise() {
		logger.info("Initialising mq.properties");
	    if(bundle==null){
	    	try {
	    		bundle=ResourceBundle.getBundle(PROPERTIES_FILE_NAME);    	    		
			} catch (MissingResourceException ioe) {
				logger.error("Could not locate mq.properties", ioe);
			}      
	    }
	}

	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public void onException(JMSException ajmse)
	  {
	    ajmse.printStackTrace();
	    logger.error("Unexpected exception in JMS");
	    Exception lLinkedExcep = ajmse.getLinkedException();
	    if (lLinkedExcep != null)
	    {
	      logger.error("Linked Exception : " + lLinkedExcep);
	    }
	  }
}
