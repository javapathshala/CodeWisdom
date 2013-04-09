package com.application.servlet.authorization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PasswordBuilder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties passwords = new Properties();
		passwords.put("dimit", "dimit");
		passwords.put("chadha", "chadha");
		try {
			FileOutputStream out = new FileOutputStream("./password.properties");
			passwords.store(out, "Passwords");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
