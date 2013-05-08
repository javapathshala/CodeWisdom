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

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.hql.UserDetails;

/**
 * @author dimit.chadha
 */
public class HQLQueryRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("User Name-1");

		Session session = HibHelper.getSession();
		session.beginTransaction();

		// session.save(userDetails);
		
		 userDetails = new UserDetails();
		 userDetails.setUserName("User Name-2");
		 session.save(userDetails);
		
		 userDetails = new UserDetails();
		 userDetails.setUserName("User Name-3");
		 session.save(userDetails);
		
		 userDetails = new UserDetails();
		 userDetails.setUserName("User Name-4");
		 session.save(userDetails);
		
		 //session.getTransaction().commit();
		// HibHelper.closeSession();

		// Get the list
		// session = HibHelper.getSession();
		// session.beginTransaction();

		// Query query =
		// session.createQuery("from UserDetails  where userId>5");

		// String minUerId = "5";
		// Query query = session.createQuery("from UserDetails  where userId>" +
		// minUerId);

		// String minUerId = " 5 or 1=1"; // --- sql injections
		// Query query = session.createQuery("from UserDetails  where userId>" +
		// minUerId);

		// but above is sql injection. Remedy is below
		String minUserId = "5";
		Query query = session.createQuery("from UserDetails  where userId > ? and userName=?");
		query.setInteger(0, Integer.parseInt(minUserId));
		query.setString(1, "User Name-3");
		// pagination
		// query.setFirstResult(5); // skipping first 5 records - start point
		// query.setMaxResults(5); // max records - end point

		List<UserDetails> users = (List<UserDetails>) query.list();

		// Show all records
		System.out.println(users.size());
		for (UserDetails usersList : users) {
			System.out.println(usersList);
		}

		// Query queryMap =
		// session.createQuery("select new map(userId,userName) from UserDetails");
		// List<UserDetails> usersMap = (List<UserDetails>) query.list();
		// // Show all records
		// System.out.println(usersMap.size());
		// System.out.println(usersMap);

		session.getTransaction().commit();
		HibHelper.closeSession();

	}

}
