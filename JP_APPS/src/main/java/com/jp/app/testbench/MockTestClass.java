/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.app.testbench;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Dimit.Chadha
 * 
 */
public class MockTestClass implements LoadTester {

	Calendar cal = Calendar.getInstance();


	public void doLoadTest(Object obj) {
		System.out.println("Starting Load Testing for class : " + getClass().getName());
		MockTestClass mockTest = (MockTestClass) obj;
		// PaymentTable paymentTable = new PaymentTable();
		Date[] dates = mockTest.getDates(2009, 6, 30, 30, 24);
		System.out.println(dates);
	}

	public Date[] getDates(int year, int month, int day, int paymentDay, int numOfInstallments) {
		int i;
		Date data[] = new Date[numOfInstallments + 1];

		data[0] = setInitialDate(year, month, day, paymentDay);

		for (i = 1; i <= numOfInstallments; i++) {
			data[i] = getNextDate(paymentDay);
		}
		return data;
	}

	public Date setInitialDate(int year, int month, int day, int paymentDay) {
		Date date;
		month--;
		cal.set(year, month, day, 0, 0, 0);
		date = cal.getTime();
		if (paymentDay == 0)
			paymentDay = day;
		cal.set(Calendar.DATE, paymentDay);
		return date;
	}

	public Date getNextDate(int paymentDay) {
		int saveYear, saveMonth, saveDay;
		Date date;
		cal.add(Calendar.MONTH, 1);
		if (paymentDay != 0 && cal.get(Calendar.MONTH) == 2 && paymentDay > 28)
			cal.add(Calendar.DATE, paymentDay - 28);
		saveYear = cal.get(Calendar.YEAR);
		saveMonth = cal.get(Calendar.MONTH);
		saveDay = cal.get(Calendar.DATE);
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
			cal.add(Calendar.DATE, 1);

		date = cal.getTime();

		if (paymentDay != 0)
			cal.set(saveYear, saveMonth, saveDay, 0, 0, 0);
		return date;
	}

}
