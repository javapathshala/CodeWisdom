/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 * 
 */
public class CustomerDTO {

	private String custId;
	private String company;
	private String userName;
	private String password;
	private String custComments;
	private String autoPay;
	private String accountNumber;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustComments() {
		return custComments;
	}

	public void setCustComments(String custComments) {
		this.custComments = custComments;
	}

	public String getAutoPay() {
		return autoPay;
	}

	public void setAutoPay(String autoPay) {
		this.autoPay = autoPay;
	}

}
