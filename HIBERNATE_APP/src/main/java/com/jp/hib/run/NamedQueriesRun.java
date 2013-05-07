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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.named.queries.UserDetails;

/**
 * @author dimit.chadha
 */
public class NamedQueriesRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session session = HibHelper.getSession();
		session.beginTransaction();

		Query queryById = session.getNamedQuery("UserDetails.byId");
		queryById.setInteger(0, 4);

		List<UserDetails> users = (List<UserDetails>) queryById.list();

		printUsers(users);
		// named native query
		Query queryByName = session.getNamedQuery("UserDetails.byName");
		queryByName.setString(0, "User Name-3");

		List<UserDetails> users1 = (List<UserDetails>) queryByName.list();

		printUsers(users1);

		// Criteria API
		System.out.println("#### Criteria Example ####");
		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq("userName", "User Name-3")).add(Restrictions.between("userId", 11, 23));
		criteria.addOrder(Order.desc("userId"));

		List<UserDetails> users2 = (List<UserDetails>) criteria.list();
		printUsers(users2);

		// Projections - used for aggreation functions or group
		System.out.println("#### Projection Example ####");
		Criteria criteria1 = session.createCriteria(UserDetails.class).setProjection(Projections.max("userId"));

		List<Integer> users3 = (List<Integer>) criteria1.list();
		System.out.println(users3.get(0));

		session.getTransaction().commit();
		HibHelper.closeSession();

	}

	/**
	 * @param users1
	 */
	private static void printUsers(List<UserDetails> users1) {
		System.out.println(users1.size());
		for (UserDetails usersList1 : users1) {
			System.out.println(usersList1);
		}
	}

}
