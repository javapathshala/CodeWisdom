/*
 * Created on Aug 23, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.basic;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author dimit
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GreetingServiceImpl implements GreetingService, BeanNameAware {

	private String greeting;

	/**
	 * 
	 */
	// public GreetingServiceImpl(String greeting) {
	// this.greeting = greeting;
	// }
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SimpleHelloWorld.GreetingService#sayGreeting()
	 */
	public void sayGreeting() {
		System.out.println(greeting);
	}

	public void setBeanName(String arg0) {
		// TODO Auto-generated method stub

	}

}
