/**
 * Copyright (C) 2011, Dimit Chadha
 * All rights reserved.
 * Visit my blog at http://dimitchadha.blogspot.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.classloaders;

import java.lang.reflect.Method;

/**
 * @author dimit.chadha
 *
 */
public class DynamicLoader {

	public static void main(String[] args) throws Exception {
		ClassLoader defaultLoader = new DynamicLoader().getClass()
				.getClassLoader();
		System.out.println("Default Class Loader is ::: " + defaultLoader);
		if (defaultLoader != null) {
			System.out.println(defaultLoader.getClass());
			defaultLoader = defaultLoader.getParent();
			System.out.println("Parent Loader ::" + defaultLoader);
		}
		Class<?> toRun = Class.forName(args[0]);
		String[] newArgs = scrubArgs(args);
		Method mainMethod = findMain(toRun);
		mainMethod.invoke(null, new Object[] { newArgs });

	}

	private static String[] scrubArgs(String[] args) {
		String[] toReturn = new String[args.length - 1];
		for (int i = 1; i < args.length; i++) {
			toReturn[i - 1] = args[i].toLowerCase();
		}
		return toReturn;
	}

	private static Method findMain(Class<?> clazz) throws Exception {
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals("main"))
				return methods[i];
		}
		return null;
	}
}
