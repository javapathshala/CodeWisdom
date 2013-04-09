package com.tech.koncept.listener;

//import com.ibm.mq.MQQueue;
//import com.ibm.mq.jms.MQQueueConnection;
//import com.ibm.mq.jms.MQQueueConnectionFactory;
//import com.ibm.mq.jms.MQQueueReceiver;
//import com.ibm.mq.jms.MQQueueSession;

public class MessageListenerThread {
//implements Runnable, MessageListener {
//	private static MQQueueConnection con;
//
//	private static MQQueueSession session;
//
//	private static MQQueue apiIn;
//
//	private static MQQueueReceiver queueReceiver;
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		MessageListenerThread messageListenerThread = new MessageListenerThread();
//		try {
//			MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
//			factory.setQueueManager("MQSBS5T0");
//			factory.setChannel("MQ.CLT.JP.SSL");
//			factory.setCCSID(819);
//			factory.setMessageRetention(1);
//			factory.setMsgBatchSize(10);
//			factory.setPollingInterval(5000);
//			factory.setSyncpointAllGets(false);
//			factory.setUseConnectionPooling(true);
//			factory.setTransportType(1);
//			factory.setHostName("10.50.1.2");
//			factory.setPort(1414);
//			factory.setTemporaryModel("SYSTEM.DEFAULT.MODEL.QUEUE");
//			con = (MQQueueConnection) factory.createQueueConnection();
//			session = (MQQueueSession) con.createQueueSession(true, 1);
//			apiIn = (MQQueue) session.createQueue("JP.JP.API.REQ");
//			queueReceiver = (MQQueueReceiver) session.createReceiver(apiIn);
//			con.start();
//			System.out.println("waiting for message.....");
//		} catch (JMSException e) {
//			e.printStackTrace();
//		}
//		new Thread(messageListenerThread).start();
//	}
//
//	public synchronized void run() {
//		try {
//			queueReceiver.setMessageListener(this);
//			this.wait();
//		} catch (JMSException e) {
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void onMessage(javax.jms.Message aMessage) {
//		System.out.println("Got Message message.....");
//		try {
//			if (aMessage instanceof TextMessage) {
//				String sMessage = ((TextMessage) aMessage).getText();
//				System.out.println("Message is :: " + sMessage);
//				aMessage.acknowledge();
//			}
//		} catch (JMSException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
