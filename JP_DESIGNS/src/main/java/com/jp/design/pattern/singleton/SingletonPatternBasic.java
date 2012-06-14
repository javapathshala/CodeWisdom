/*
 * File: SingletonPatternBasic.java
 * Date: 14-Jun-2012
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
package com.jp.design.pattern.singleton;

/**
 * @author dimit.chadha
 */
public class SingletonPatternBasic {

	// Private Constructor
	private SingletonPatternBasic() {

	}

	private static SingletonPatternBasic instance;

	public static SingletonPatternBasic getInstance() throws SingletonException {
		if (instance == null) {
			instance = new SingletonPatternBasic();
		} else {
			throw new SingletonException("Only One intance of SingleTonPattern Object is allowed! No Two instances can co-exists");
			
		}
		return instance;
	}

}
