/*
 * File: WorkerThread.java
 * Date: 21-Mar-2013
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
package com.jp.koncept.threads.pool;

/**
 * @author dimit.chadha
 */
public class WorkerThread implements Runnable {

	private String command;

	public WorkerThread(String command) {
		this.command = command;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
		processCommand();
		System.out.println(Thread.currentThread().getName()+" End.");
	}

	/**
	 * 
	 */
	private void processCommand() {
		System.out.println(Thread.currentThread().getName()+" Processing Command.. ");		
	}

}
