/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.aop.security;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SecurityApp {

	public static void main(String[] args) {
		// get the security manager
		SecurityManager mgr = new SecurityManager();

		// get the bean
		SecureBean bean = getSecureBean();

		// try as robh
		mgr.login("dimit", "dimit");
		bean.writeSecureMessage();
		mgr.logout();

		// try as janm
		try {
			mgr.login("janm", "pwd");
			bean.writeSecureMessage();
		} catch (SecurityException ex) {
			System.out.println("Exception Caught: " + ex.getMessage());
		} finally {
			mgr.logout();
		}

		// try with no credentials
		try {
			bean.writeSecureMessage();
		} catch (SecurityException ex) {
			System.out.println("Exception Caught: " + ex.getMessage());
		}

	}

	private static SecureBean getSecureBean() {
		// create the target
		SecureBean target = new SecureBean();

		// create the advice
		SecurityAdvice advice = new SecurityAdvice();

		// get the proxy
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);

		//factory.addBeforeAdvice(advice);
		//factory.addAdvice(advice);
		SecureBean proxy = (SecureBean) factory.getProxy();

		return proxy;

	}
}
