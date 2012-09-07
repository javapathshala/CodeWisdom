/**
 * 
 */
package com.jp.application.common;

/**
 * @author dimit.chadha
 * 
 */
public interface RegExpConstants {
	String MEMORABLE_WORD_REGEX = "^[a-zA-Z]{6,8}$";
	String MEMORABLE_WORD_PROMPT = "^[^a-zA-Z0-9]*[a-zA-Z0-9].*$";
	String MOBILE_NO = "^[0-9-]{0,14}$";
	String NETWORK_PROVIDER = "^([ ]*[A-Za-z0-9]){1,40}[ ]*$";

	String ADDRESS_LINE = "^[0-9a-zA-Z]*[0-9a-zA-Z\\.\\/\\-\\', ]*[0-9a-zA-Z]+$"; // Taglib
																					// only
	String ADDRESS_START = "^[0-9a-zA-Z]*[0-9a-zA-Z\\.\\/\\-\\', ]*[0-9a-zA-Z]+$"; // Taglib
																					// only

	String AUTH_USERS = "^[ ]*([0]?[1-9]|10)[ ]*$"; // only for taglib

	String BALANCE = "^[ ]*(£[ ]*([-+][ ]*)?|[-+][ ]*(£[ ]*)?)?([1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))|[0-9])(\\.[0-9]{0,2})?[ ]*$";

	String CARD_NUMBER = "^[ ]*(([0-9][ ]*){13}|([0-9][ ]*){16})$";
	String CARD_SECURITY_NUMBER = "^[0-9][0-9][0-9]$";
	String CREDIT_LIMIT = "^[ ]*(£[ ]*(\\+[ ]*)?|\\+[ ]*(£[ ]*)?)?([1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))|[0-9])(\\.[0-9]{0,2})?[ ]*$";
	String ANNUAL_INCOME = "^[ ]*(£[ ]*(\\+[ ]*)?|\\+[ ]*(£[ ]*)?)?([1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))|[0-9])(\\.[0-9]{0,2})?[ ]*$";
	String CURRENCY_CODE = "^[ ]*[0-9]{3}[ ]*$"; // only in converter
	// String CUSTOMER_ID = "^[ ]*"+ CustomerID.getREKernel() + "[ ]*$"; // only
	// in convertor

	String DAY_OF_MONTH = "^[ ]*[0]*([1-9]|[12][0-9]|3[01])[ ]*$"; // currently
																	// only used
																	// by TagLib
	String DEBIT_CARD = "^[ ]*(([0-9][ ]*){13}|([0-9][ ]*){16}|([0-9][ ]*){18}|([0-9][ ]*){19})$"; // taglib
																									// version.
																									// Changed
																									// Neil
																									// McGrane
																									// 27/6/2001
	// String DEBIT_CARD_CNV = "^" + DebitCardNumber.getRegexString() + "$"; //
	// convertor -- why are these two so different

	// String EMAIL_ADDRESS = "^[ ]*" + EmailAddress.getREKernel() + "[ ]*$";

	String FORENAME = "^[ ]*([A-Za-z]+[ ]?)+[ ]*$";
	String FULLNAME = "^([A-Za-z-' ]*)$";
	String RELATIONSHIP = "^((0){1}[1|2]{1}){1}$";

	String INCOME = "^[ ]*[+]?[1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))(\\.[0-9]{0,2})?[ ]*$";
	String INITIALS = "^([ ]*[A-Za-z-',;_]( *[.] *)?)+[ ]*$";
	// String ISSUE = "^[ ]*[0-9]{2}[ ]*$"; removed, as this is duplicated with
	// SWITCH_ISSUE_NUMBER below

	String LOGONID = "^[ ]*([0-9][ ]*){9}$";

	String MEMORABLE_WORD_LETTER = "^[A-Z]$";

	// SC707 - Message Rule isn't a regular expression.
	// It is designed to work with TextArea validators Message and Message750
	// which do not use regular expressions.
	String MESSAGE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ 0123456789\\n\\r\\t./,'£$!%_:;<>@#{}[]()\\\"&?=+-*";
	// SC707 - End

	String MONEY = "^[ ]*(£[ ]*([-+][ ]*)?|[-+][ ]*(£[ ]*)?)?([1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))|[0-9])(\\.[0-9]{0,2})?[ ]*$";
	String MONTH_NUMBER = "^[ ]*[0]*([1-9]|1[0-2])[ ]*$"; // ditto

	// String NEW_PASSCODE = "^[ ]*" + Passcode.getREKernel() + "[ ]*$";
	String NEW_PASSCODE_CHECK = "^[0-9]{6}$"; // Only use by Taglib but is this
												// correct??? should it be the
												// same as one above

	// String PASSCODE = "^[ ]*" + Passcode.getREKernel() + "[ ]*$";
	String PAYMENT = "^[ ]*[+]?[1-9](([0-9]{0,2}(,[0-9]{3})*)|([0-9]*))(\\.[0-9]{0,2})?[ ]*$";
	String PHONENO = "^[ ]*([+]|\\([ ]*([0-9][ ]*)+\\)|)[ ]*([0-9][ ]*)+$";

	// This is more complex than it should be because Netscape fails on the
	// natural regex
	String POSTCODE_FULL = "^([A-Za-z] *){1,2}([0-9A-Za-z] *|([0-9A-Za-z] *){2})([0-9] *)([A-Za-z] *){2}$";
	String POSTCODEA = "^([A-Za-z][ ]*){1,2}([0-9A-Za-z][ ]*){1,2}$";
	String POSTCODEB = "^([0-9][A-Za-z][ ]*){2}$";

	String SEX = "^[mf]$";
	String SUBJECT = "^[ ]*([A-Za-z]+[ ]?)+[ ]*$";

	String SURNAME = "^[ ]*([A-Za-z]+([ ]?[-' ][ ]?[A-Za-z]+)*)+[ ]*$";

	String SWITCH_ISSUE_NUMBER = "^[ ]*[0-9]{1,2}[ ]*$";

	String TITLE = "^([ ]*[A-Za-z]+[.]?)+[ ]*$";
	String TRANSACTION_TYPE = "^[ ]*[0-9]{2}[ ]*$";
}
