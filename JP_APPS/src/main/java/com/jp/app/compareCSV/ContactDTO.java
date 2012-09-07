/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 * 
 */
public class ContactDTO {

	private NameDTO conName;
	private AddressDTO conAddress; // fax-occupation
	private String customerAccount;

	
	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public NameDTO getConName() {
		return conName;
	}

	public void setConName(NameDTO conName) {
		this.conName = conName;
	}

	public AddressDTO getConAddress() {
		return conAddress;
	}

	public void setConAddress(AddressDTO conAddress) {
		this.conAddress = conAddress;
	}

}
