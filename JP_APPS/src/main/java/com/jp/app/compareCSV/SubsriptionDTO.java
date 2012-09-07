/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 * 
 */
public class SubsriptionDTO {

	private String subEffectiveStart;
	private String subRenewalDate;
	private String subInitialTerm;

	private String subStatus;
	private String subAmount;
	private String accountNumber;
	
	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSubEffectiveStart() {
		return subEffectiveStart;
	}

	public void setSubEffectiveStart(String subEffectiveStart) {
		this.subEffectiveStart = subEffectiveStart;
	}

	public String getSubRenewalDate() {
		return subRenewalDate;
	}

	public void setSubRenewalDate(String subRenewalDate) {
		this.subRenewalDate = subRenewalDate;
	}

	public String getSubInitialTerm() {
		return subInitialTerm;
	}

	public void setSubInitialTerm(String subInitialTerm) {
		this.subInitialTerm = subInitialTerm;
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

}
