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

import org.hibernate.Session;

import com.jp.hib.connection.HibHelper;
import com.jp.hib.entities.Employee;

/**
 * @author dimit.chadha
 */
public class BasicRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BasicRun basicRun = new BasicRun();

		// Configuration configuration = new Configuration();
		// configuration.configure();
		// ServiceRegistry serviceRegistry = new
		// ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		// SessionFactory sessionFactory =
		// configuration.buildSessionFactory(serviceRegistry);

		switch (Integer.parseInt(args[0])) {
			case 1: // This is for select all records from table Employee
				basicRun.AllRecords(HibHelper.getSession());
				break;
			case 2: // This is for inserting a new Data in the Table Employee
				basicRun.insertRecord(HibHelper.getSession());
				HibHelper.closeSession();
				break;
			case 3: // This is for deleting Data from the Table Employee
				basicRun.deleteRecord(HibHelper.getSession());
				break;
			case 4: // This is for deleting Data from the Table Employee
				basicRun.UpdateRecord(HibHelper.getSession());
				HibHelper.closeSession();
				break;
			default:
				System.out.println("Enter Something");
				break;
		}
	}

	private void AllRecords(Session session) {
		session.beginTransaction();
		List<Employee> employees = (List<Employee>) session.createQuery("FROM Employee").list();
		session.getTransaction().commit();
		HibHelper.closeSession();
		for (int i = 0; i < employees.size(); i++) {
			Employee emp = (Employee) employees.get(i);
			System.out.println(emp);
		}
		HibHelper.closeSession();
	}

	/**
	 * @param session
	 */
	private void UpdateRecord(Session session) {
		session.beginTransaction();
		Employee emp = (Employee) session.load(Employee.class, new Integer(7));
		emp.setName("Pragati2");
		session.getTransaction().commit();
		HibHelper.closeSession();
		AllRecords(HibHelper.getSession());
	}

	/**
	 * @param session
	 */
	private void deleteRecord(Session session) {
		session.beginTransaction();
		Employee emp = (Employee) session.load(Employee.class, new Integer(6));
		session.getTransaction().commit();
		HibHelper.closeSession();
		Session sessionDel = HibHelper.getSession();
		sessionDel.beginTransaction();
		sessionDel.delete(emp);
		sessionDel.getTransaction().commit();
		HibHelper.closeSession();
		AllRecords(HibHelper.getSession());
	}

	/**
	 * 
	 */
	private void insertRecord(Session session) {
		Employee insertEmp = new Employee();
		insertEmp.setName("Dimit");
		insertEmp.setAddress("Pragati");
		insertEmp.setSalary(20014);
		session.beginTransaction();
		session.save(insertEmp);
		session.getTransaction().commit();
		AllRecords(session);
	}
}
