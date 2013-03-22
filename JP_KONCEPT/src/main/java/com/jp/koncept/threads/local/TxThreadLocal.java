/*
 * File: TxThreadLocal.java
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
package com.jp.koncept.threads.local;

/**
 * @author dimit.chadha
 */
public class TxThreadLocal {

	public static final ThreadLocal<Transaction> txThreadLocal = new ThreadLocal<Transaction>();

	public static void set(Transaction tx) {
		txThreadLocal.set(tx);
	}

	public static Transaction get() {
		return txThreadLocal.get();
	}

	public static void unset() {
		txThreadLocal.remove();
	}
}
