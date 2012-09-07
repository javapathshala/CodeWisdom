/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.app.testbench;
/**
 * @author Dimit.Chadha
 * 
 */
public class ActivityThread extends Thread {

	private static long threadCounter = 0;
	private String loadClass = "";

	/**
	 * Description : This method automatically invoked after
	 * ActivityThread.start() call.
	 */
	public void run() {
		try {
			String loadClass = getLoadClass();
			final LoadTester loadTestObj = (LoadTester) Class.forName(loadClass).newInstance();
			loadTestObj.doLoadTest(loadTestObj);
			System.out.println("Activity Thread Counter : " + threadCounter++);
			System.out.println("Activity Thread Counter : " + loadClass+" : " + threadCounter);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the threadCounter
	 */
	public static long getThreadCounter() {
		return threadCounter;
	}

	/**
	 * @param threadCounter
	 *            the threadCounter to set
	 */
	public static void setThreadCounter(long threadCounter) {
		ActivityThread.threadCounter = threadCounter;
	}

	/**
	 * @return the loadClass
	 */
	public String getLoadClass() {
		return loadClass;
	}

	/**
	 * @param loadClass
	 *            the loadClass to set
	 */
	public void setLoadClass(String loadClass) {
		this.loadClass = loadClass;
	}

}
