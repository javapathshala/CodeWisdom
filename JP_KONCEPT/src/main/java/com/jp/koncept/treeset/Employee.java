/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.treeset;

/**
 * @author dimit.chadha
 * 
 */
public class Employee implements Comparable<Employee> {
	private int empNum;
	private String name;
	private String department;

	public Employee() {

	}

	/**
	 * 
	 * 
	 * @param socSecNum
	 * @param empNum
	 * @param name
	 * @param department
	 */
	public Employee(int empNum, String name, String department) {
		super();
		this.empNum = empNum;
		this.name = name;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public int getEmpNum() {
		return empNum;
	}

	public String getDepartment() {
		return department;
	}


	public int compareTo(Employee o) {
		if (empNum > o.getEmpNum()) {
			return 1;
		} else {
			return -1;
		}

	}

	// public boolean equals(Object obj) {
	// boolean retValue = false;
	// if (obj instanceof Employee) {
	// Employee e = (Employee)obj;
	// retValue = empNum.equals(e.getEmpNum( ));
	// }
	// return retValue;
	// }
	//
	// public int hashCode( ) {
	// return empNum.hashCode( );
	// }
}
