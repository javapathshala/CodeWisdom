/*
 * File: ThreadLocalUtil.java
 * Date: 20-Mar-2013
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
 * wrapper class around thread local that keeps all the
 *         thread local variables in one single ThreadLocal variable. 
 *         
 *         
 *         The advantage of the utility class is that no developer needs to manage
 *         the thread local variable lifecycle individually. The class puts all
 *         the thread locals in one map of variables. The destroy() method can
 *         be invoked where you can safely remove all thread locals in your web
 *         application. In our case thats a ServletRequestListener ->
 *         requestDestroyed() method. You will also need to place finally blocks
 *         elsewhere. Typical places are near the HttpServlet, in the init(),
 *         doPost(), doGet() methods. This may remove all thread locals in the
 *         pooled worker threads after the request is done or an exception is
 *         thrown unexpectedly. Sometimes it happens that the main thread of the
 *         server leaks thread local variables. If that is the case you need to
 *         find the right places where to call the ThreadLocalUtil -> destroy()
 *         method. To do that figure out where the main thread actually
 *         *creates* the thread variables. You could use your debugger to do
 *         that.
 */
public class ThreadLocalUtil {

	private final static ThreadLocal<ThreadVariables> THREAD_VARIABLES = new ThreadLocal<ThreadVariables>() {

		/**
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected ThreadVariables initialValue() {

			return new ThreadVariables();

		}

	};

	public static Object getThreadVariable(String name) {

		return THREAD_VARIABLES.get().get(name);

	}

	public static Object getThreadVariable(String name, InitialValue initialValue) {

		Object o = THREAD_VARIABLES.get().get(name);

		if (o == null) {

			THREAD_VARIABLES.get().put(name, initialValue.create());

			return getThreadVariable(name);

		} else {

			return o;

		}

	}

	public static void setThreadVariable(String name, Object value) {

		THREAD_VARIABLES.get().put(name, value);

	}

	public static void destroy() {

		THREAD_VARIABLES.remove();

	}

}
