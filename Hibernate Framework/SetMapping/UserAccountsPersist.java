/*
 * Created on 06-Dec-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package SetMapping;

/**
 * @author dimit
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UserAccountsPersist implements DomainObject{
	private int id;
	private String accountNumber;
	private String sortCode;
	

	/**
	 * @return
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @return
	 */
	public String getSortCode() {
		return sortCode;
	}

	/**
	 * @param string
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @param string
	 */
	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

}
