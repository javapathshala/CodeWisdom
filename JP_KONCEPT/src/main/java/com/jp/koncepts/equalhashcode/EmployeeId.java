/*
 * File: EmployeeId.java
 * Date: 11-Mar-2013
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
package com.jp.koncepts.equalhashcode;

/**
 * @author dimit.chadha
 */
public class EmployeeId {

	private String id;

	public EmployeeId(String id) {
		this.id = id;
	}

	public String toString() {
		return id;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj.getClass() != getClass()) {
			return false;
		}
		EmployeeId empId = (EmployeeId) obj;
		if (this.id == empId.id) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
