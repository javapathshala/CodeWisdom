/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.xss;

/**
 * @author Dimit.Chadha
 * 
 */
public class CrossSiteScripting {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String invalid = "<script>alert();</script>";
		String data = "ddddd";
		if (invalid.indexOf('<') != -1) {
			invalid = invalid.replaceAll("<", "&lt;");
		}
		if (invalid.indexOf('>') != -1) {
			invalid = invalid.replaceAll(">", "&gt;");
		}
		if (invalid.indexOf('&') != -1) {
			invalid = invalid.replaceAll("&", "&amp;");
		}
		if (invalid.indexOf('\"') != -1) {
			invalid = invalid.replaceAll("\"", "&quot;");
		}

		if (!invalid.equalsIgnoreCase(data)) {
			System.out.println("maclious code attacked");
		}
	}
}
