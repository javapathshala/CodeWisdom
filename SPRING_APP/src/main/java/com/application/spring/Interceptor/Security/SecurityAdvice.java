/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.application.spring.Interceptor.Security;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SecurityAdvice implements MethodBeforeAdvice {

	private SecurityManager securityManager;

	public SecurityAdvice() {
		this.securityManager = new SecurityManager();
	}

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		UserInfo user = securityManager.getLoggedOnUser();
		if (user == null) {
			System.out.println("No user authenticated");
			throw new SecurityException(
					"You must login before attempting to invoke the method: "
							+ method.getName());
		} else if ("dimit".equals(user.getUserName())) {
			System.out.println("Logged in user is Dimit - OKAY!");
		} else {
			System.out.println("Logged in user is " + user.getUserName()
					+ " NOT GOOD :(");
			throw new SecurityException("User " + user.getUserName()
					+ " is not allowed access to method " + method.getName());
		}

	}

}
