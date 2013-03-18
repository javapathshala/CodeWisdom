/*
 * File: DefaultHashCode.java
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author dimit.chadha
 */
public class DefaultHashCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<EmployeeId, Employee> employees = new HashMap<EmployeeId, Employee>();
		employees.put(new EmployeeId("111"), new Employee("Johny"));
		employees.put(new EmployeeId("222"), new Employee("Jeny")); // Line A
		employees.put(new EmployeeId("333"), new Employee("Jessie"));
		Employee emp = employees.get(new EmployeeId("222"));// Line B
		System.out.println(emp); // Line C

	}

}
