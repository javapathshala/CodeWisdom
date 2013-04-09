/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.aop.types;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BussinessLogicImpl implements BussinessInterface {

	/*
	 * @see BeforeAOP.bussinessInterface#bussinessMethod()
	 */
	public void bussinessMethod() throws Exception {
		System.out.println("Business Method Callled :::  ");
		System.out.println("Inside Business Method :::  ");
//		if(true){
//			throw new Exception();
//		}
	}
}
