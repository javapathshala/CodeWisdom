/*
 * Created on 06-Dec-06
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package SetMapping;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dimit
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PersistenceUsers implements DomainObject {

	private int id;
	private String userId;
	private String userName;
	private Set accountsPersist = new HashSet();

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public Set getAccountsPersist() {
		return accountsPersist;
	}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param string
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param set
	 */
	public void setAccountsPersist(Set accountsPersist) {
		this.accountsPersist = accountsPersist;
	}

	/**
	 * @param string
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void add(UserAccountsPersist userAccountsPersist) {
		accountsPersist.add(userAccountsPersist);
	}
	
	public void remove(UserAccountsPersist userAccountsPersist) {
		accountsPersist.remove(userAccountsPersist);
	}
	
	
	
	/**
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param string
	 */
	public void setUserName(String string) {
		userName = string;
	}

}
