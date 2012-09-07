/**
 * 
 */
package com.jp.koncept.serialization;

import java.io.Serializable;

/**
 * @author dimit.chadha
 * 
 */
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6560746657685778117L;
	public String name;
	public String address;
//	public transient int SSN;
	public int SSN;
	public int number;

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}
}
