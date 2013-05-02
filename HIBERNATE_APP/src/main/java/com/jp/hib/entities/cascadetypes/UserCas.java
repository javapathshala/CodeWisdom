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
package com.jp.hib.entities.cascadetypes;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is for ONE-ONE Mapping
 * 
 * @author dimit.chadha
 */
@Entity
@Table(name = "USER")
public class UserCas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "USER_NAME")
	private String userName;

	//Below one creates another table
	//Using cascade - saved unsaved entities in case of releation. no need to implicity session.save 
	@OneToMany (cascade=CascadeType.PERSIST)
	@JoinTable(name="USER_CAR", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "CAR_ID"))
	private Collection<CarsCas> cars = new ArrayList<CarsCas>();

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the cars
	 */
	public Collection<CarsCas> getCars() {
		return cars;
	}

	/**
	 * @param cars
	 *            the cars to set
	 */
	public void setCars(Collection<CarsCas> cars) {
		this.cars = cars;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", cars=" + cars + "]";
	}

}