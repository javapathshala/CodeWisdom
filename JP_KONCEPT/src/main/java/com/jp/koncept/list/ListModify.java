/*
 * File: ListModify.java
 * Date: 28-Feb-2013
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
package com.jp.koncept.list;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/**
 * @author dimit.chadha
 */
public class ListModify {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			list.add("Dimit" + i);
		}
		System.out.println("Records Inserted!");
		System.out.println("Start Iteration");
		try {
			Iterator<String> itr = list.iterator();
			list.add("test");
			while (itr.hasNext()) {
				System.out.println(itr.next());
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();

		}
	}

}
