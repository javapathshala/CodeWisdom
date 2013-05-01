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
import com.jp.hib.entities.onetomany.Cars;
import com.jp.hib.entities.onetomany.User;
import com.jp.hib.entities.onetoone.Person;
import com.jp.hib.entities.onetoone.Vehicle;

/**
 * @author dimit.chadha
 */
public class OneToMany {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		User user = new User();
		user.setUserName("User 1");

		Cars car1 = new Cars();
		car1.setCarName("Fiat");
		user.getCars().add(car1);
		
		Cars car2 = new Cars();
		car2.setCarName("Santro");
		user.getCars().add(car2);
		
		Session session = HibHelper.getSession();
		session.beginTransaction();

		session.save(user);
		session.save(car1);
		session.save(car2);
		session.getTransaction().commit();
		HibHelper.closeSession();

		 // Retrive
		 session = HibHelper.getSession();
		 session.beginTransaction();
		
		 user=null;
		 user= (User) session.get(User.class, 1);
		 System.out.println(user);
		
		 session.getTransaction().commit();
		 HibHelper.closeSession();
	}

}
