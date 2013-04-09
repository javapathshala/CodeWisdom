package com.application.servlet.db.caching;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;

public class Database {
	private static boolean verbose = false;

	final static String driverClass = "com.ibm.db2.jcc.DB2Driver";

	final static String connectionURL = "jdbc:db2://10.50.1.11:50001/BIB640D";

	final static String userID = "root";

	final static String userPassword = "iecp_app";

	public static final Connection getConnection(String baseName) {
		Connection conn = null;
		// String driver = null;
		// String connectionURL = null;
		// String userID = null;
		// String userPassword = null;
		try {
			// ResourceBundle resb = ResourceBundle.getBundle(baseName);
			// driver = resb.getString("database.driver");
			// connectionURL = resb.getString("database.connectionURL");
			// /userID = resb.getString("database.userID");
			// userPassword = resb.getString("database.userPassword");
			// Class.forName(driverClass);
			Class.forName(driverClass).newInstance();
		} catch (MissingResourceException e) {
			System.err.println("Missing Resource: " + e.getMessage());
			return conn;
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: " + e.getMessage());
			return conn;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			if (verbose) {
				// System.out.println("baseName=" + baseName);
				System.out.println("driver=" + driverClass);
				System.out.println("connectionURL=" + connectionURL);
				System.out.println("userID=" + userID);
				System.out.println("userPassword=" + userPassword);
			}

			conn = DriverManager.getConnection(connectionURL, userID,
					userPassword);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("in Database.getConnection");
			System.err.println("on getConnection");
			conn = null;
		} finally {
			return conn;
		}
	}

	public static void setVerbose(boolean v) {
		verbose = v;
	}
}