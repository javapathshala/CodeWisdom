/*
 * File: RunThreadPool.java
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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dimit.chadha
 */
public class RunThreadPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		{
			// RejectedExecutionHandler implementation
			RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
			// Get the ThreadFactory implementation to use
			ThreadFactory threadFactory = Executors.defaultThreadFactory();
			// creating the ThreadPoolExecutor
			ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
					threadFactory, rejectionHandler);
			// start the monitoring thread
			MonitorThread monitor = new MonitorThread(executorPool, 3);
			Thread monitorThread = new Thread(monitor);
			monitorThread.start();
			// submit work to the thread pool
			for (int i = 0; i < 10; i++) {
				executorPool.execute(new WorkerThread("cmd" + i));
			}
			Thread.sleep(30000);
			// shut down the pool
			executorPool.shutdown();
			// shut down the monitor thread
			Thread.sleep(5000);
			monitor.shutdown();

		}
	}
}

// Notice the change in active, completed and total completed task count of the
// executor. We can invoke shutdown() method to finish execution of all the
// submitted tasks and terminate the thread pool.
