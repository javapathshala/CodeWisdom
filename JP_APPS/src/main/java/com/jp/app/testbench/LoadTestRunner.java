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
public final class LoadTestRunner {

	private LoadTestRunner() {

	}

	public static void main(String[] args) {
		try {
			final String loadClass = args[0]; // class to be tested
			final long maxUsers = Long.parseLong(args[1]);
			final long maxActivities = Long.parseLong(args[2]);
			UserThread userThread = null;
			for (int i = 1; i <= maxUsers; i++) {
				userThread = new UserThread();
				userThread.setMaxActivities(maxActivities);
				userThread.setLoadClass(loadClass);
				userThread.start();
			}
		} catch (Throwable e) { // to catch outofmemory error
			e.printStackTrace();
		}

	}
}
