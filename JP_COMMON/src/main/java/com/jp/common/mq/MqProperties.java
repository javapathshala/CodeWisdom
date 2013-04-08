package com.jp.common.mq;

import java.io.Serializable;
import java.util.Properties;

public class MqProperties implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Properties urlProperty;
	private String sendQueueName;
	private String recieveQueueName;
	private String dirtyQueueName;
	private int ack;
	private boolean transacted;
	
	public MqProperties(Properties urlProperty, String sendQueueName, String recieveQueueName, String dirtyQueueName,int ack, boolean transacted) {
		super();
		this.urlProperty = urlProperty;
		this.sendQueueName = sendQueueName;
		this.recieveQueueName = recieveQueueName;
		this.dirtyQueueName=dirtyQueueName;
		this.ack = ack;
		this.transacted = transacted;
	}

	public int getAck() {
		return ack;
	}

	public String getDirtyQueueName() {
		return dirtyQueueName;
	}

	public String getRecieveQueueName() {
		return recieveQueueName;
	}

	public String getSendQueueName() {
		return sendQueueName;
	}

	public boolean isTransacted() {
		return transacted;
	}

	public Properties getUrlProperty() {
		return urlProperty;
	}
}
