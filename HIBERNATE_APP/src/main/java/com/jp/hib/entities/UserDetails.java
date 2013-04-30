/*
 * File: UserDetails.java
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
package com.jp.hib.entities;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

/**
 * @author dimit.chadha
 */

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Embedded
	private Address homeAddress;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "city", column = @Column(name = "OFFICE_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "OFFICE_STATE")), })
	private Address officeAddress;

	@ElementCollection
     //@ElementCollection(fetch=FetchType.EAGER)
	// @GenericGenerator(name = "hilo-gen", strategy = "hilo")
	// @CollectionId(columns = { @Column(name = "RESI_ID") }, generator =
	// "hilo-gen", type = @Type(type = "long"))
	@AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "RESI_COUNTRY")),
			@AttributeOverride(name = "houseNo", column = @Column(name = "RESI_HNO")), })
	@JoinTable(name = "RESI_ADDRESS", joinColumns = @JoinColumn(name = "USER_ID"))
	private Collection<ResiAddress> resiAddresses = new HashSet<ResiAddress>();

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
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
	 * @return the homeAddress
	 */
	public Address getHomeAddress() {
		return homeAddress;
	}

	/**
	 * @param homeAddress
	 *            the homeAddress to set
	 */
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * @return the officeAddress
	 */
	public Address getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * @param officeAddress
	 *            the officeAddress to set
	 */
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	// @Override
	// public String toString() {
	// return "UserDetails [\nuserId=" + userId + ",\n userName=" + userName +
	// ",\n homeAddress=" + homeAddress + ",\n officeAddress="
	// + officeAddress + ",\n resiAddresses=" + resiAddresses + "]";
	// }

	/**
	 * @return the resiAddresses
	 */
	public Collection<ResiAddress> getResiAddresses() {
		return resiAddresses;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", homeAddress=" + homeAddress + ", officeAddress="
				+ officeAddress + ", resiAddresses=" + resiAddresses + "]";
	}

	/**
	 * @param resiAddresses
	 *            the resiAddresses to set
	 */
	public void setResiAddresses(Collection<ResiAddress> resiAddresses) {
		this.resiAddresses = resiAddresses;
	}

}
