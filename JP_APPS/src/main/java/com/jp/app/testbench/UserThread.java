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
public class UserThread extends Thread{

	private long maxActivities = 0;
	private String loadClass = "";
	
	/**
	 * Description : This method automatically invoked after UserThread.start()
	 * call.
	 */
	public void run() {

		try {
			// set maximum Value for No. of ActivityThread
			ActivityThread activityThread = null;
			for (int j = 1; j <= getMaxActivities(); j++) {
				activityThread = new ActivityThread();
				activityThread.setLoadClass(getLoadClass());
				activityThread.start();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * @return the maxActivities
	 */
	public long getMaxActivities() {
		return maxActivities;
	}
	/**
	 * @param maxActivities the maxActivities to set
	 */
	public void setMaxActivities(long maxActivities) {
		this.maxActivities = maxActivities;
	}
	/**
	 * @return the loadClass
	 */
	public String getLoadClass() {
		return loadClass;
	}
	/**
	 * @param loadClass the loadClass to set
	 */
	public void setLoadClass(String loadClass) {
		this.loadClass = loadClass;
	}
	
	
	
}
