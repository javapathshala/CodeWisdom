/*
 * File: Stock.java
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
package com.jp.hib.entities.inher.tableclass;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This is for ONE-ONE Mapping
 * 
 * @author dimit.chadha
 */
@Entity
@Table(name = "TWO_WHEELER")
public class TwoWheeler extends Vehicle {

	private String steeringHandle;

	/**
	 * @return the steeringHandle
	 */
	public String getSteeringHandle() {
		return steeringHandle;
	}

	/**
	 * @param steeringHandle
	 *            the steeringHandle to set
	 */
	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}

}
