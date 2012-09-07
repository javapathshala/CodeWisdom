/**
 * 
 */
package com.application.data.load.impl;

import com.jp.application.common.AbstractDTO;

/**
 * @author dimit Data class for lucene
 * 
 */
public class CustomerDAO extends AbstractDTO {

	private String customerName;
	private long custId;
	private LocationDAO locationDAO;

	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId
	 *            the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 *            the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the locationDAO
	 */
	public LocationDAO getLocationDAO() {
		return locationDAO;
	}

	/**
	 * @param locationDAO
	 *            the locationDAO to set
	 */
	public void setLocationDAO(LocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}
}
