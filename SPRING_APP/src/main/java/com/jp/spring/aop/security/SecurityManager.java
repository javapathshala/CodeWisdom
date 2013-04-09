/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.aop.security;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SecurityManager {

	private static ThreadLocal threadLocal = new ThreadLocal();

	public void login(String userName, String password) {
		// assumes that all credential are valid for a login
		threadLocal.set(new UserInfo(userName, password));
	}

	public void logout() {
		threadLocal.set(null);
		int x = 0;
	}

	public UserInfo getLoggedOnUser() {
		return (UserInfo) threadLocal.get();
	}

}
