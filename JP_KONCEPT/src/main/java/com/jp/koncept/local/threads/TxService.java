/*
 * File: TxService.java
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
public class TxService {

	public void getTxDetails() {
		Transaction tx = TxThreadLocal.get();
		// System.out.println("Transaction ID: " + tx.getTxId());
		// System.out.println("Transaction Name: " + tx.getTxName());
		System.out.println(tx);
	}
}
