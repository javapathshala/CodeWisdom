/*
 * File: BasicRun.java
 * Date: 16-Apr-2013
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
package com.jp.hib.run;

import org.hibernate.Session;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.firstlevel.UserDetails;

/**
 * @author dimit.chadha
 */
public class FirstLevelCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session session = HibHelper.getSession();
		session.beginTransaction();

		UserDetails userDetails = (UserDetails) session.get(UserDetails.class, 3);
		printUsers(userDetails);

		userDetails.setUserName("Updated User for Id =====3"); // this will take
																// effect
		printUsers(userDetails);

		UserDetails userDetails1 = (UserDetails) session.get(UserDetails.class, 3);
		printUsers(userDetails1);

		// there will be only one select statement . Until session is closed. So
		// by default we have session persist

		// run & see the states.

		session.getTransaction().commit();

		HibHelper.closeSession();
	}

	/**
	 * @param users1
	 */
	private static void printUsers(UserDetails users) {
		System.out.println(users);
	}

}
