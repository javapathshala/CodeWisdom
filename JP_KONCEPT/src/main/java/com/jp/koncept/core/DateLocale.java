package com.jp.koncept.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateLocale {
	/**
z	 * @param args
	 */
	public static void main(String[] args) {
		Date d = new Date();
		Locale loc = Locale.getDefault();
		DateFormat df = new SimpleDateFormat();
		System.out.println(loc.getDisplayCountry() + "  " + df.format(d) + " ");
	}
}