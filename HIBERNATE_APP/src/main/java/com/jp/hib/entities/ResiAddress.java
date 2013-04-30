/*
 * File: ResiAddress.java
 * Date: 30-Apr-2013
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
package com.jp.hib.entities;

import javax.persistence.Embeddable;

/**
 * @author dimit.chadha
 */
@Embeddable
public class ResiAddress {

	private int houseNo;

	private String country;

	/**
	 * @return the houseNo
	 */
	public int getHouseNo() {
		return houseNo;
	}

	/**
	 * @param houseNo
	 *            the houseNo to set
	 */
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country +"Lucky";
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResiAddress [houseNo=" + houseNo + ", country=" + country + "]";
	}

}
