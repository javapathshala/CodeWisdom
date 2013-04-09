package com.application.spring.forms;

import java.util.ArrayList;
import java.util.List;

public class ViewCustomerForm {
	
	private String customerNameFilter;
	private List<CustomerListForm> customerList;
	private String errorField;
	
	
	public ViewCustomerForm() {
		super();
		customerList=new ArrayList<CustomerListForm>();
	}

	public void addCustomer(CustomerListForm customer){
		customerList.add(customer);
	}

	/**
	 * @return the customerList
	 */
	public List<CustomerListForm> getCustomerList() {
		return customerList;
	}

	/**
	 * @param customerList the customerList to set
	 */
	public void setCustomerList(List<CustomerListForm> customerList) {
		this.customerList = customerList;
	}

	/**
	 * @return the customerNameFilter
	 */
	public String getCustomerNameFilter() {
		return customerNameFilter;
	}

	/**
	 * @param customerNameFilter the customerNameFilter to set
	 */
	public void setCustomerNameFilter(String customerNameFilter) {
		this.customerNameFilter = customerNameFilter;
	}

	/**
	 * @return the errorField
	 */
	public String getErrorField() {
		return errorField;
	}

	/**
	 * @param errorField the errorField to set
	 */
	public void setErrorField(String errorField) {
		this.errorField = errorField;
	}
	
	
}
