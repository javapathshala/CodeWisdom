/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.application.spring.Interceptor.Security;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserInfo {

	private String userName;
	private String password;

	public UserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;

	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

}
