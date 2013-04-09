/*
 * Created on 24-Nov-06
  */
package Mapping;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author dimit
 */
public class Users implements Serializable{

	private int id;
	private String firstname;
	private String lastname;
	private int age;
	private Set favouriteEvents = new HashSet();
	
	/**
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return
	 */
	public Set getFavouriteEvents() {
		return favouriteEvents;
	}

	/**
	 * @return
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param i
	 */
	public void setAge(int i) {
		age = i;
	}

	/**
	 * @param set
	 */
	public void setFavouriteEvents(Set favouriteEvents) {
		this.favouriteEvents = favouriteEvents;
	}

	/**
	 * @param string
	 */
	public void setFirstname(String string) {
		firstname = string;
	}

	/**
	 * @param i
	 */
	public void setId(int i) {
		id = i;
	}

	/**
	 * @param string
	 */
	public void setLastname(String string) {
		lastname = string;
	}

}
