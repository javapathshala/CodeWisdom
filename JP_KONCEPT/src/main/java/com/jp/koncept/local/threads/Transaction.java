/*
 * File: Transaction.java
 * Date: 19-Mar-2013
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.koncept.local.threads;

/**
 * @author dimit.chadha
 */
public class Transaction {

	private int txId;

	private String txName;

	/**
	 * @return the txId
	 */
	public int getTxId() {
		return txId;
	}

	/**
	 * @param txId
	 *            the txId to set
	 */
	public void setTxId(int txId) {
		this.txId = txId;
	}

	/**
	 * @return the txName
	 */
	public String getTxName() {
		return txName;
	}

	/**
	 * @param txName
	 *            the txName to set
	 */
	public void setTxName(String txName) {
		this.txName = txName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [txId=" + txId + ", txName=" + txName + "]";
	}
	
	

}
