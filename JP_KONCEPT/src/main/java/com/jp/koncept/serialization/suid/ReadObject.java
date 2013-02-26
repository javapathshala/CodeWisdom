/*
 * File: ReadObject.java
 * Date: 06-Dec-2012
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.koncept.serialization.suid;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * @author dimit.chadha
 */
public class ReadObject {

	// The “InvalidClassException” will raise, because you write a serialization
	// class with serialVersionUID “1L” but try to retrieve it back with updated
	// serialization class, serialVersionUID “2L”.
	public static void main(String args[]) {

		Address address;

		try {

			FileInputStream fin = new FileInputStream("c:\\address.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			address = (Address) ois.readObject();
			ois.close();

			System.out.println(address);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
