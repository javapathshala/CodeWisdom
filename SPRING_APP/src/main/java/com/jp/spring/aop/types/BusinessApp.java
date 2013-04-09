/*
 * Created on Oct 26, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.aop.types;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author shubhams
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BusinessApp {

	public static void main(String[] args) {

		ApplicationContext apctx = new FileSystemXmlApplicationContext(
				"../Spring FrameWork/properties/BeforeAOPConfig.xml");
		BussinessInterface bInterface = (BussinessInterface) apctx
				.getBean("BusinessLogicBean");
		try {
			bInterface.bussinessMethod();
		} catch (Exception e) {
		}
	}
}
