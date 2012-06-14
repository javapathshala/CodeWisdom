/*
 * File: RoundPeg.java
 * Date: 12-Jun-2012
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
package com.jp.design.pattern.adapter;

/**
 * @author dimit.chadha
 */
public class RoundPeg implements IRoundPeg {

	/*
	 * (non-Javadoc)
	 * @see
	 * com.jp.design.pattern.adapter.IRoundPeg#insertIntoHole(java.lang.String)
	 */
	public void insertIntoHole(String msg) {
		System.out.println("RoundPeg insertIntoHole(): " + msg);

	}

}
