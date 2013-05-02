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
import com.jp.hib.entities.inher.singletable.FourWheeler;
import com.jp.hib.entities.inher.singletable.TwoWheeler;
import com.jp.hib.entities.inher.singletable.Vehicle;

/**
 * @author dimit.chadha
 */
public class InheritanceSingleTableRun {

	/**
	 * When no inheritance is specified, all columns go to one table only- default Stragery 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setVehicleName("Passion Plus");
		twoWheeler.setSteeringHandle("Bike Steering Handle");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setVehicleName("Audi");
		fourWheeler.setSteeringWheel("Audi Steering Handle");

		Session session = HibHelper.getSession();
		session.beginTransaction();

		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);

		session.getTransaction().commit();
		HibHelper.closeSession();

	}

}
