package com.jp.koncept.regexp;

import static java.lang.System.out;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindPattern {

	static int godSmack[];
	static int danzig[] = new int[] { 1, 2 };

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("[\\d][\\s]*[\\w]");
		Matcher match = pattern.matcher("bc7dab234xyab3");
		out.println("Pattern Required is :: " + match.pattern());
		while (match.find()) {
			out.println(match.start() + "   " + match.group());
		}
		FindPattern fp = new FindPattern();
		Object obj = fp;
		obj.toString();
		((FindPattern) obj).spin();
		// System.out.println(godSmack[0]);
		// danzig[0] = 7;
		System.out.println(new FindPattern().danzig.length);
	}

	void spin() {
		out.println("OK");
	}
}