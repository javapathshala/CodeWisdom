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


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.firstlevel.UserDetails;

/**
 * @author dimit.chadha
 */
public class QueryCache {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session session = HibHelper.getSession();
		session.beginTransaction();

		//Two Queries -select as query cache is different
		Query query =session.createQuery("from UserDetails user where user.userId=10");
		query.setCacheable(true);
		List<UserDetails> users= (List<UserDetails>)query.list();
		printUsers(users);
		session.getTransaction().commit();
		HibHelper.closeSession();

		Session session2 = HibHelper.getSession();
		session2.beginTransaction();
		Query query2 =session2.createQuery("from UserDetails user where user.userId=10");
		query2.setCacheable(true);
		List<UserDetails> users2= (List<UserDetails>)query2.list();
		printUsers(users2);
		session2.getTransaction().commit();
		HibHelper.closeSession();

		//set query cache to true 
		
	}

	/**
	 * @param users1
	 */
	private static void printUsers(List<UserDetails> users) {
		System.out.println(users);
	}

}
