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
import com.jp.hib.entities.Person;
import com.jp.hib.entities.Vehicle;

/**
 * @author dimit.chadha
 */
public class OneToOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Person person = new Person();
		person.setPersonName("Person 2");

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Vehicle 2");

		person.setVehicle(vehicle);
		Session session = HibHelper.getSession();
		session.beginTransaction();

		session.save(person);
		session.save(vehicle);

		session.getTransaction().commit();
		HibHelper.closeSession();

		 // Retrive
		 session = HibHelper.getSession();
		 session.beginTransaction();
		
		 person=null;
		 person= (Person) session.get(Person.class, 1);
		 System.out.println(person);
		
		 session.getTransaction().commit();
		 HibHelper.closeSession();
	}

}
