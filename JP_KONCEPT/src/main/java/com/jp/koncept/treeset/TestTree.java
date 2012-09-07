/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.treeset;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

/**
 * @author dimit.chadha
 * 
 */
public class TestTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Employee emp = new Employee(23, "dc", "dc");
		TreeSet<Employee> t1 = new TreeSet<Employee>();

		t1.add(emp);
		Employee emp1 = new Employee(21, "dc1", "dc1");
		t1.add(emp1);
		//
		emp1 = new Employee(19, "dc19", "dc10");
		t1.add(emp1);
		// TreeSet<String> t1 = new TreeSet<String>();
		// t1.add("Dimit");
		// t1.add("Amit");

		Iterator<Employee> iterator;
		iterator = t1.iterator();
		System.out.print("Tree set data: ");
		// Displaying the Tree set data
		while (iterator.hasNext()) {
			System.out.println("");
			Employee e = iterator.next();
			System.out.println(e.getEmpNum() + " ");
			System.out.println(e.getName() + " ");
		}

		List<String> list = new ArrayList<String>();
		list.add("dimit");
		list.add("chadha");

		for (String s : list) {
			System.out.println(s);
		}

		for (Employee s : t1) {
			System.out.println(s.getEmpNum());
		}

		String msg = MessageFormat
				.format("On {1, date,long}, a {0} caused {2,number,currency} of damage.",
						"hurricane",
						new GregorianCalendar(2009, 0, 15).getTime(), 1.0E8);
		System.out.println(msg);
	}

}
