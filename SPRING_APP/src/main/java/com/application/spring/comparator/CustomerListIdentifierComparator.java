package com.application.spring.comparator;

import java.util.Comparator;

import com.application.data.load.impl.CustomerDAO;

public class CustomerListIdentifierComparator implements Comparator {

	private String sortOrder;

	public CustomerListIdentifierComparator(String sortOrder) {
		super();
		this.sortOrder = sortOrder;
	}

	public int compare(Object objectOne, Object objectTwo) {
		CustomerDAO custOne = (CustomerDAO) objectOne;
		CustomerDAO custTwo = (CustomerDAO) objectTwo;
		int result = 0;
		if ("0".equals(sortOrder)) {
			result = compareIdentifierAsc(custOne, custTwo);
		} else if ("1".equals(sortOrder)) {
			result = compareIdentifierDsc(custOne, custTwo);
		}
		return result;
	}

	private int compareIdentifierAsc(CustomerDAO custOne, CustomerDAO custTwo) {
		int i = 0;
		long idOne = custOne.getCustId();
		long idTwo = custTwo.getCustId();
		if (idOne > idTwo) {
			i = 1;
		} else {
			i = 0;
		}
		return i;
	}

	private int compareIdentifierDsc(CustomerDAO custOne, CustomerDAO custTwo) {
		int i = 0;
		long idOne = custOne.getCustId();
		long idTwo = custTwo.getCustId();
		if (idOne > idTwo) {
			i = 0;
		} else {
			i = 1;
		}
		return i;
	}
}
