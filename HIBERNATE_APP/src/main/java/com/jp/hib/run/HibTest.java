/*
 * File: HibTest.java
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
package com.jp.hib.run;

import org.hibernate.Session;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.Address;
import com.jp.hib.entities.ResiAddress;
import com.jp.hib.entities.UserDetails;

/**
 * @author dimit.chadha
 */
public class HibTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Dimit2");

		Address homeAddress = new Address();
		homeAddress.setCity("Home City2");
		homeAddress.setState("Home State2");

		userDetails.setHomeAddress(homeAddress);

		Address officeAddress = new Address();
		officeAddress.setCity("Office City");
		officeAddress.setState("Office State");

		userDetails.setOfficeAddress(officeAddress);

		ResiAddress resiAddress = new ResiAddress();
		resiAddress.setCountry("India");
		resiAddress.setHouseNo(185);

		userDetails.getResiAddresses().add(resiAddress);

		resiAddress = new ResiAddress();
		resiAddress.setCountry("UK");
		resiAddress.setHouseNo(2);
		userDetails.getResiAddresses().add(resiAddress);

		Session session = HibHelper.getSession();
		session.beginTransaction();

		session.save(userDetails);

		session.getTransaction().commit();
		HibHelper.closeSession();

		// Retrive
		session = HibHelper.getSession();
		session.beginTransaction();

		userDetails = null;
		userDetails = (UserDetails) session.get(UserDetails.class, 1);
		System.out.println(userDetails);

		session.getTransaction().commit();
		HibHelper.closeSession();
	}

}
