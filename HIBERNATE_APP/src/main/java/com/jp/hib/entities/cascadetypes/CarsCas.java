/*
 * File: StockDetail.java
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
package com.jp.hib.entities.cascadetypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author dimit.chadha
 */
@Entity
@Table(name = "CARS")
public class CarsCas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CAR_ID")
	private int carId;

	@Column(name = "CAR_NAME")
	private String carName;

	// @ManyToOne
	// @NotFound(action=NotFoundAction.IGNORE)
	// private User user;

	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * @param carId
	 *            the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}

	/**
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}

	/**
	 * @param carName
	 *            the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}

	// /**
	// * @return the user
	// */
	// public User getUser() {
	// return user;
	// }
	//
	// /**
	// * @param user
	// * the user to set
	// */
	// public void setUser(User user) {
	// this.user = user;
	// }

}
