/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Dimit.Chadha
 * 
 */
public class DateUtil {

	public enum DateEnum {
		YEAR, MONTH, DAY_OF_MONTH, HOUR_OF_DAY, MINUTE, SECOND, MILLISECOND
	};

	public static void main(String[] args) {

	}

	/**
	 * Calculate a date from an original date modified by the specified years,
	 * months & days.
	 * 
	 * @param startDate
	 *            - Start date to calculate new date from.
	 * @param yearsOffset
	 *            - +/- years from start date.
	 * @param monthsOffset
	 *            - +/- months from the start date.
	 * @param daysOffset
	 *            - +/- years from the start date.
	 * @param granuality
	 *            - The granuality of the created date.
	 * @return The created date. NOTE: Is a null parameter the generated date
	 *         WILL include the current time as well!
	 */
	public static Date dateCreate(Date startDate, int yearsOffset, int monthsOffset, int daysOffset, DateEnum granularity) {
		Date retDate = null;
		Date newDate = null;

		// Create a Calendar object for the required date.
		Calendar cal = toCalendar(startDate);

		// Modify by the required YEAR/MONTH/DAY offsets.
		cal.add(Calendar.YEAR, yearsOffset);
		cal.add(Calendar.MONTH, monthsOffset);
		cal.add(Calendar.DATE, daysOffset);

		// Generate and return the required date.
		newDate = cal.getTime();
		retDate = refine(newDate, granularity);
		return retDate;
	}

	/**
	 * Create a Date object from numeric values.
	 * 
	 * @param year
	 *            - The year
	 * @param month
	 *            - The month [1:12]
	 * @param day
	 *            - The day [1:31]
	 * @return The created Date object
	 */
	public static Date dateFromNumbers(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day); // Month - 1 is used to get the
											// correct month.
		return calendar.getTime();
	}

	/**
	 * Create a Date object from a String object with the specified date/time
	 * format.
	 * 
	 * @param dateFormat
	 *            - The date format to use for the conversion
	 * @param dateToFormat
	 *            - The string to convert to a date object
	 * @return dateToFormat as a Date object
	 * @throws ParseException
	 */
	public static Date dateFromString(String dateFormat, String dateToFormat) throws ParseException {
		Date date = null;
		if (dateToFormat != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			date = sdf.parse(dateToFormat);
		}
		return date;
	}

	/**
	 * Create a Date object from numeric values.
	 * 
	 * @param year
	 *            - The year
	 * @param month
	 *            - The month [1:12]
	 * @param day
	 *            - The day [1:31]
	 * @param hours
	 *            - The hour [0:23]
	 * @param mins
	 *            - The minute [0:59]
	 * @param secs
	 *            - The second [0:59]
	 * @param msecs
	 *            - The millisecond [0:999]
	 * @return The created Date object
	 * @throws ParseException
	 */
	public static Date dateTimeFromNumbers(int year, int month, int day, int hours, int mins, int secs, int msec) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day, hours, mins, secs); // Month - 1 is
																// used to get
																// the correct
																// month.
		calendar.add(Calendar.MILLISECOND, msec);
		return calendar.getTime();
	}

	/**
	 * Create a String object from a Date object with the specified date/time
	 * format.
	 * 
	 * @param format
	 *            - the format for the date string.
	 * @param date
	 *            - the date to format.
	 * @return 'date' formated to a string of the specified format.
	 * @throws ParseException
	 */
	public static String dateToString(String dateFormat, Date dateToFormat) {
		String date = null;
		if (dateToFormat != null) {
			// FastDateFormat fdf = FastDateFormat.getInstance(dateFormat);
			// date = fdf.format(dateToFormat);
		}
		return date;
	}

	/**
	 * Converts a date object to a calandar object.
	 * 
	 * @param date
	 *            - The date to convert (null == now!)
	 * @return The date object converted to a calandar object.
	 */
	public static Calendar toCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		if (date == null) { // Create from the current date/time.
			cal.setTime(cal.getTime());
		} else { // Create form the specified Date object.
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * Converts a date object to a calandar object.
	 * 
	 * @param date
	 *            - The date to convert (null == now!)
	 * @param granuality
	 *            - The specified level of granuality.
	 * @return The date object converted to a calandar object.
	 */
	public static Calendar toCalendar(Date date, DateEnum granularity) {
		// Get the Date as a Calander object.
		Calendar cal1 = Calendar.getInstance();
		if (date == null) { // Create from the current date/time.
			cal1.setTime(cal1.getTime());
		} else { // Create form the specified Date object.
			cal1.setTime(date);
		}

		// Create an empty calendar.
		Calendar cal2 = Calendar.getInstance();
		cal2.clear(); // Set all fields undefined.

		// Set the required granuality of the Calandar value and return it;
		while (true) {
			cal2.set(Calendar.YEAR, cal1.get(Calendar.YEAR));
			if (granularity == DateEnum.YEAR)
				break;

			cal2.set(Calendar.MONTH, cal1.get(Calendar.MONTH));
			if (granularity == DateEnum.MONTH)
				break;

			cal2.set(Calendar.DAY_OF_MONTH, cal1.get(Calendar.DAY_OF_MONTH));
			if (granularity == DateEnum.DAY_OF_MONTH)
				break;

			cal2.set(Calendar.HOUR_OF_DAY, cal1.get(Calendar.HOUR_OF_DAY));
			if (granularity == DateEnum.HOUR_OF_DAY)
				break;

			cal2.set(Calendar.MINUTE, cal1.get(Calendar.MINUTE));
			if (granularity == DateEnum.MINUTE)
				break;

			cal2.set(Calendar.SECOND, cal1.get(Calendar.SECOND));
			if (granularity == DateEnum.SECOND)
				break;

			cal2.set(Calendar.MILLISECOND, cal1.get(Calendar.MILLISECOND));
			break;
		}
		return cal2;
	}

	/**
	 * Compares two dates to the specified level of granuality.
	 * 
	 * @param date1
	 *            - The date to be compared.
	 * @param date2
	 *            - The date to be compared with.
	 * @param granuality
	 *            - The level of granuality to compare the dates.
	 * @returns true if date 1 is AFTER date 2 for the specified level of
	 *          granularity
	 */
	public static boolean after(Date date1, Date date2, DateEnum granularity) {
		int comparison = compare(date1, date2, granularity);
		if (comparison > 0)
			return true;
		else
			return false;
	}

	/**
	 * Compares two dates to the specified level of granuality.
	 * 
	 * @param date1
	 *            - The date to be compared.
	 * @param date2
	 *            - The date to be compared with.
	 * @param granuality
	 *            - The level of granuality to compare the dates.
	 * @returns true if date 1 is BEFORE date 2 for the specified level of
	 *          granularity
	 */
	public static boolean before(Date date1, Date date2, DateEnum granularity) {
		int comparison = compare(date1, date2, granularity);
		if (comparison < 0)
			return true;
		else
			return false;
	}

	/**
	 * Compares two dates to the specified level of granuality.
	 * 
	 * @param date1
	 *            - The date to be compared.
	 * @param date2
	 *            - The date to be compared with.
	 * @param granuality
	 *            - The level of granuality to compare the dates.
	 * @returns a calandar comparision to the specified level of granularity:
	 *          -ve = date1 is BEFORE date 2 0 = date1 is EQUIVALENT to date 2.
	 *          +ve = date1 is AFTER date 2.
	 */
	public static int compare(Date date1, Date date2, DateEnum granularity) {
		Calendar cal1 = toCalendar(date1, granularity);
		Calendar cal2 = toCalendar(date2, granularity);
		int comparison = cal1.compareTo(cal2);
		return comparison;
	}

	/**
	 * Compares two dates to the specified level of granuality.
	 * 
	 * @param date1
	 *            - The date to be compared.
	 * @param date2
	 *            - The date to be compared with.
	 * @param granuality
	 *            - The level of granuality to compare the dates.
	 * @returns true if date 1 is EQUIVALENT to date 2 for the specified level
	 *          of granularity
	 */
	public static boolean equals(Date date1, Date date2, DateEnum granularity) {
		int comparison = compare(date1, date2, granularity);
		if (comparison == 0)
			return true;
		else
			return false;
	}

	/**
	 * Refine a date to the specified level of accuracy.
	 * 
	 * @param date
	 *            - The date to be refined.
	 * @param granuality
	 *            - The level of granuality to set the date to.
	 * @returns A refined date to the specified level of granularity: Notes: [1]
	 *          The date must have the granularity component to remove it! [2]
	 *          IF date is null THEN null is returned!
	 */
	public static Date refine(Date date, DateEnum granularity) {
		Date newDate = null;
		if (date != null) {
			Calendar cal = toCalendar(date, granularity);
			newDate = cal.getTime();
		}
		return newDate;
	}
}
