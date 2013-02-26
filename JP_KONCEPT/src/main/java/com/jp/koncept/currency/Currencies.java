package com.jp.koncept.currency;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Currencies {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StringBuffer buffer = new StringBuffer();
		Locale[] locale = Locale.getAvailableLocales();
		for (int i = 0; i < locale.length; i++) {
			String loc = locale[i].toString();
			if (loc.length() == 5) {
				System.out.print("Locale:" + loc);

				BigDecimal amount = new BigDecimal(4534353453.96);
				NumberFormat format = NumberFormat.getCurrencyInstance(locale[i]);
				NumberFormat number = NumberFormat.getNumberInstance(locale[i]);
				NumberFormat integer = NumberFormat.getIntegerInstance(locale[i]);

				Currency currency = format.getCurrency();
				System.out.print(", Country :" + locale[i].getDisplayCountry());

				// System.out.print(", Currency   Code:" +
				// currency.getCurrencyCode());
				// System.out.print(", Currency Symbol:" +
				// currency.getSymbol());
				int fa = currency.getDefaultFractionDigits();
				System.out.print(", Fraction Digits:" + fa);

				System.out.print(",Amount Cur : " + format.format(amount));
				number.setMaximumFractionDigits(fa);
				String format2 = number.format(amount);
				System.out.print(",Amount Num : " + format2);
				if (fa == 0) {

				}

				System.out.println(",Amount inte: " + integer.format(amount));

			} else {
				buffer.append("\nInvalid Locale:" + loc);
			}
		}
		System.out.print("\nOUTPUT of INVALID LOCALE " + buffer.toString());

	}

}
