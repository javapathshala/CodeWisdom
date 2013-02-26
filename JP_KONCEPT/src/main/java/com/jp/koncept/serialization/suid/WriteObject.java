/*
 * File: WriteObject.java
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

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


/**
 * @author dimit.chadha
 *
 */
public class WriteObject {
	
	public static void main (String args[]) {
		 
		   Address address = new Address();
		   address.setStreet("wall street");
		   address.setCountry("united states");
		   //address.setPostcode("WA14 2WF");
		   try{
	 
			FileOutputStream fout = new FileOutputStream("c:\\address.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fout);   
			oos.writeObject(address);
			oos.close();
			System.out.println("Serialization Done");
	 
		   }catch(Exception ex){
			   ex.printStackTrace();
		   } 
		}

}
