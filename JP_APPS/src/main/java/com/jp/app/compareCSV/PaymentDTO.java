/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 * 
 */
public class PaymentDTO {

	private String customerAccount;
	private NameDTO payName;
	private AddressDTO payAddress; // fax=null;
	private SubDateDTO paySubDateDTO;

	private CreditCardExpiry payCreditCardExpiry;

	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	public NameDTO getPayName() {
		return payName;
	}

	public void setPayName(NameDTO payName) {
		this.payName = payName;
	}

	public AddressDTO getPayAddress() {
		return payAddress;
	}

	public void setPayAddress(AddressDTO payAddress) {
		this.payAddress = payAddress;
	}

	public SubDateDTO getPaySubDateDTO() {
		return paySubDateDTO;
	}

	public void setPaySubDateDTO(SubDateDTO paySubDateDTO) {
		this.paySubDateDTO = paySubDateDTO;
	}

	public CreditCardExpiry getPayCreditCardExpiry() {
		return payCreditCardExpiry;
	}

	public void setPayCreditCardExpiry(CreditCardExpiry payCreditCardExpiry) {
		this.payCreditCardExpiry = payCreditCardExpiry;
	}

}
