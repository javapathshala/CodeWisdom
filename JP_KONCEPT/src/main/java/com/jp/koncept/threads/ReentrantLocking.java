/*
 * File: ReentrantLocking.java
 * Date: 18-Mar-2013
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
package com.jp.koncept.threads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dimit.chadha
 */
public class ReentrantLocking {

	final Lock lock = new ReentrantLock();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReentrantLocking reentrantLocking = new ReentrantLocking();
		reentrantLocking.goAhead();

	}

	/**
	 * 
	 */
	private void goAhead() {
		new Thread(newRunable(), "Thread1").start();
		new Thread(newRunable(), "Thread1").start();
	}

	/**
	 * @return
	 */
	private Runnable newRunable() {

		return new Runnable() {

			public void run() {
				do {
					try {
						if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
							try {

								System.out.println("locked thread " + Thread.currentThread().getName());

								Thread.sleep(1000);

							} finally {
								lock.unlock();
								System.out.println("unlocked locked thread " + Thread.currentThread().getName());

							}
							break;
						} else {
							System.out.println("unable to lock thread " + Thread.currentThread().getName() + " will re try again");
						}

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while (true);

			}
		};
	}

}
