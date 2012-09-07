/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 * 
 */
public class UpDTO {

	private NameDTO upName;
	private String company;
	private AddressDTO upAddress; // fax-occupation
	private String userName;
	private String password;
	private String upComments;
	private SubDateDTO upSubDateDTO;

	private String subStatus;
	private String subAmount;

	private String AutorenewalDisabled;

	private CreditCardExpiry upCreditCardExpiry;



	public NameDTO getUpName() {
		return upName;
	}

	public void setUpName(NameDTO upName) {
		this.upName = upName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public AddressDTO getUpAddress() {
		return upAddress;
	}

	public void setUpAddress(AddressDTO upAddress) {
		this.upAddress = upAddress;
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

	public String getUpComments() {
		return upComments;
	}

	public void setUpComments(String upComments) {
		this.upComments = upComments;
	}

	public SubDateDTO getUpSubDateDTO() {
		return upSubDateDTO;
	}

	public void setUpSubDateDTO(SubDateDTO upSubDateDTO) {
		this.upSubDateDTO = upSubDateDTO;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSubAmount() {
		return subAmount;
	}

	public void setSubAmount(String subAmount) {
		this.subAmount = subAmount;
	}

	public String getAutorenewalDisabled() {
		return AutorenewalDisabled;
	}

	public void setAutorenewalDisabled(String autorenewalDisabled) {
		AutorenewalDisabled = autorenewalDisabled;
	}

	public CreditCardExpiry getUpCreditCardExpiry() {
		return upCreditCardExpiry;
	}

	public void setUpCreditCardExpiry(CreditCardExpiry upCreditCardExpiry) {
		this.upCreditCardExpiry = upCreditCardExpiry;
	}



}
