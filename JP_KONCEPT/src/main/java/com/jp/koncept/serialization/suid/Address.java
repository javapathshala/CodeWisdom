/*
 * File: Address.java
 * Date: 06-Dec-2012
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
package com.jp.koncept.serialization.suid;

import java.io.Serializable;

/**
 * @author dimit.chadha
 */
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6432634463643260329L;

	String street;

	String country;
	
	//String postcode;

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return this.street;
	}

	public String getCountry() {
		return this.country;
	}

	
	/**
	 * @return the postcode
	 */
//	public String getPostcode() {
//		return postcode;
//	}
//
//	
//	/**
//	 * @param postcode the postcode to set
//	 */
//	public void setPostcode(String postcode) {
//		this.postcode = postcode;
//	}

	@Override
	public String toString() {
		//return new StringBuffer(" Street : ").append(this.street).append(" Country : ").append(this.country).append("  Postcode :  ").append(this.postcode).toString();
		return new StringBuffer(" Street : ").append(this.street).append(" Country : ").append(this.country).toString();
	}

}
