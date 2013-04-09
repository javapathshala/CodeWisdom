/*
 * Created on Aug 23, 2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.jp.spring.basic;

import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author dimit welcomeAdvice To change the template for this generated type
 *         comment go to Window&gt;Preferences&gt;Java&gt;Code
 *         Generation&gt;Code and Comments
 */
public class HelloApp {

	public static void main(String[] args) throws Exception {
//		BeanFactory factory = new XmlBeanFactory(new FileInputStream(
//				"../Spring FrameWork/properties/Hello.xml"));
		FileSystemXmlApplicationContext factory = new FileSystemXmlApplicationContext("../Spring FrameWork/properties/Hello.xml");

		GreetingService greetingService = (GreetingService) factory
				.getBean("greetingTarget");
		greetingService.sayGreeting();
	}
}
