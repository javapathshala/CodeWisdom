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
import com.jp.hib.entities.cascadetypes.CarsCas;
import com.jp.hib.entities.cascadetypes.UserCas;


/**
 * @author dimit.chadha
 */
public class CasadeTypeRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserCas user = new UserCas();
		user.setUserName("User3");

		CarsCas car1 = new CarsCas();
		car1.setCarName("Fiat");
		

		CarsCas car2 = new CarsCas();
		car2.setCarName("Jeep");
		
		user.getCars().add(car1);
		user.getCars().add(car2);

		Session session = HibHelper.getSession();
		session.beginTransaction();

		//session.save(user);
//		session.save(car1);
//		session.save(car2);
		
		session.persist(user);
		session.getTransaction().commit();
		HibHelper.closeSession();

		// Get Cars based on User ID
		session = HibHelper.getSession();
		session.beginTransaction();

		user = null;
		user = (UserCas) session.get(UserCas.class, 2);
		System.out.println(user);

		session.getTransaction().commit();
		HibHelper.closeSession();
	}

}
