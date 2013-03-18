/*
 * File: Employee.java
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
public class Employee {

	private String name;

	public Employee(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Employee emp = (Employee) obj;
		if (this.name == emp.name) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
