package com.application.servlet.mq.queue;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

//import javax.jms.ExceptionListener;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.application.logger.BaseLogger;
//import com.ibm.mq.MQException;



/**
 * Servlet implementation class for Servlet: SendMessage
 *
 */
 public class SendMessage {
 
// extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet,ExceptionListener {
//
//	private static final long serialVersionUID = 1L;
//	private static ResourceBundle bundle;
//	private String PROPERTIES_FILE_NAME="mq";
//	private static BaseLogger logger = BaseLogger.getBaseLogger(SendMessage.class);
//	private final static String MQ_IN = "MQ.IN";
//	private final static String QUEUE_FACTORY = "MQ.QUEUE.CONNECTION.FACTORY";
//	private final static String JMS_TRANSACTED = "MQ.JMS_TRANSACTED";
//	private final static String JMS_ACKNOWLEDGE_MODE = "MQ.JMS_ACKNOWLEDGE_MODE";
//	protected final static String FAILURE_REASON = "FailureReason";
//	private QueueConnection qConnection;
//	private QueueSession qSession;
//	private QueueSender sender;
//	private String queueName;
//	private Queue queue;
//	private static final String HOST_NAME = "DEFAULT.NAMESERVER.HOST_NAME";
//	private static final String PORT_NUMBER = "DEFAULT.NAMESERVER.HOST_PORT_NUMBER";
//	protected final static String INITIAL_CONTEXT_FACTORY="INITIAL_CONTEXT_FACTORY";
//	private static final String BLANK ="";
//	private static final String IIOP = "IIOP://";
//	private static final String COLON_AFTER_IIOP = ":";
//	private static final String SLASH_AFTER_PORT_NUMBER = "/";
//	public SendMessage() {
//		super();
//	}
//
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		logger.info("doGet() Method of Servlet Called");
//		logger.info("endMessage(HttpServletRequest request)");
//		sendMessage(request);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		logger.info("doPost() Method of Servlet Called");
//		logger.info("endMessage(HttpServletRequest request)");
//		sendMessage(request);
//	}
//
//	private void sendMessage(HttpServletRequest request) {
//		logger.info("SendMessage Method Called");
//		String textMessage=(String)request.getParameter("txtMsg");
//		logger.info("You selected to send message : "+ textMessage);
//		logger.info("Calling makeConnection()");
//		makeConnection();
//		logger.info("Preparing for Send MQ Message");
//		logger.info("Calling createAndSendMessage()");
//		createAndSendMessage(textMessage);
//		logger.info("Exit from SendMessage()");
//	}
//
//	private void makeConnection() {
//		logger.info("Entered makeConnection()");
//		try {
//			bundle=ResourceBundle.getBundle(PROPERTIES_FILE_NAME);
//			//Contruct the context url
//		    StringBuffer url = new StringBuffer(IIOP);
//		    String hostName = bundle.getString(HOST_NAME);
//		    String portNumber = bundle.getString(PORT_NUMBER);
//		    if(null==hostName || BLANK.equals(hostName)){
//		    	hostName=System.getProperty(HOST_NAME);
//		    	logger.info("Host Name read from -D option : "+hostName);
//		    }
//		    if(null==portNumber || BLANK.equals(portNumber)){
//		    	portNumber=System.getProperty(PORT_NUMBER);
//		    	logger.info("Port Number read from -D option : "+portNumber);
//		    }
//		    String factory = bundle.getString(QUEUE_FACTORY);
//		    String initialContextFactory=bundle.getString(INITIAL_CONTEXT_FACTORY);
//		    url.append(hostName).append(COLON_AFTER_IIOP).append(portNumber).append(SLASH_AFTER_PORT_NUMBER);
//		    Properties urlProperty=new Properties();
//		    urlProperty.put(Context.PROVIDER_URL, url.toString());
//		    urlProperty.put(Context.INITIAL_CONTEXT_FACTORY, initialContextFactory);
//		    logger.info("Hostname : "+hostName + "PortNumber : "+portNumber+ "URL :: "+url+"Connection factory : "+factory+"Initial Context Factory : "+initialContextFactory);
//		    logger.info("Creating Initial Context with App Server with above parameters : ");
//		    //Initialize and create queue connection factory
//		    Context ctx = new InitialContext(urlProperty);
//			QueueConnectionFactory qConnectionFactory=(QueueConnectionFactory)ctx.lookup(factory);
//			logger.info("Initial Context Created");
//			queueName=bundle.getString(MQ_IN);
//			queue=(Queue)ctx.lookup(queueName);
//			logger.info("Queue Name : "+ queueName );
//			qConnection=qConnectionFactory.createQueueConnection();
//			logger.info("Queue connection : "+ qConnection );
//			logger.info("Queue Connection Established");
//			int ack=Integer.parseInt(bundle.getString(JMS_ACKNOWLEDGE_MODE));
//			boolean transacted=Boolean.valueOf(bundle.getString(JMS_TRANSACTED));
//			logger.info("Creating Queue Session");
//			qSession = qConnection.createQueueSession(transacted,ack);
//			logger.info("Queue Session Established");
//		}catch (MissingResourceException ms) {
//			logger.error("mq.properties files missing", ms);
//		}catch (NamingException naex) {
//			logger.error("Promblem while creating initial Context: Naming Exception", naex);
//		}catch (JMSException jms) {
//			Throwable t=jms;
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
//	}
//
//	private void createAndSendMessage(String textMessage) {
//		try {
//			logger.info("Entered createAndSendMessage(String textMessage)");
//			Message message=qSession.createTextMessage(textMessage);
//			logger.info("Jms Message Created : "+message);
//			String msgId=message.getJMSMessageID();
//			message.setJMSCorrelationID("DimitMessage");
//			logger.info("Jms Message ID : "+msgId);
//			logger.info("Jms Correleation ID while sending the message : "+message.getJMSCorrelationID());
//			sender = qSession.createSender(queue);
//			logger.info("Sender created");
//			sender.send(message);
//			logger.info("Message with message ID : "+msgId+" send to MQ Queue TEST.IN");
//			msgId=null;
//		} catch (JMSException e) {
//			Throwable t=e;
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
//		}finally{
//			//Try to close the JMS resource
//			try {
//				if (sender != null) {
//					sender.close();
//               }
//				if (qSession != null) {
//					qSession.close();
//				}
//				if (qConnection != null) {
//                   qConnection.close();
//               }
//			}catch(JMSException jmse){
//				Throwable t=jmse;
//				while(t!=null){
//					logger.error("Original Exception is : "+t.getMessage());
//					if(t instanceof JMSException){
//						JMSException je1=(JMSException)t;
//						logger.error("JMS Error Code : "+je1.getLinkedException());
//						Exception lException = je1.getLinkedException();
//					    if (lException != null){
//					          logger.error("MQ Completion Code : " + lException.getMessage());
//					        }
//					}else if(t instanceof MQException){
//						MQException mqe=(MQException)t;
//						logger.error("Completion Code : "+mqe.completionCode);
//						logger.error("Reason Code : "+mqe.reasonCode);
//						logger.error(mqe.getMessage());
//					}
//					t=t.getCause();
//				}
//			}
//		}
//	}
//
//	public void onException(JMSException ajmse)
//	  {
//	    ajmse.printStackTrace();
//	    logger.error("Unexpected exception in JMS");
//	    Exception lLinkedExcep = ajmse.getLinkedException();
//	    if (lLinkedExcep != null)
//	    {
//	     logger.error("Linked Exception : " + lLinkedExcep);
//	    }
//	  }
}