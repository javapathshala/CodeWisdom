/*
 * File: BabyCrawler.java
 * Date: 14-Aug-2012
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
package com.jp.web.crawler.baby;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author dimit.chadha
 */
public class BabyCrawler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL my_url = new URL("http://www.javapathshala.com/");
			BufferedReader br = new BufferedReader(new InputStreamReader(my_url.openStream()));
			String strTemp = "";
			while (null != (strTemp = br.readLine())) {
				System.out.println(strTemp);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
