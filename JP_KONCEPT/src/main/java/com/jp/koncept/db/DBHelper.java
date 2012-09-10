package com.jp.koncept.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.jp.common.exception.DBException;
import com.jp.common.message.VisibleMessages;

public class DBHelper {

	final static String driverClass = "com.ibm.db2.jcc.DB2Driver";

	final static String connectionURL = "jdbc:db2://10.50.1.11:50001/BIB640D";

	final static String userID = "root";

	final static String userPassword = "iecp_app";

	Connection con = null;

	VisibleMessages visibleMessages = new VisibleMessages();

	public static void main(String[] args) {
		DBHelper dbHelper = new DBHelper();
		try {
			dbHelper.openConnection();
			// dbHelper.executeQuery();
			dbHelper.closeConnection();
		} catch (DBException e) {
			dbHelper.displayException(e);
		}
	}

	/*
	 * public void executeQuery() { try { PreparedStatement
	 * p=con.prepareStatement("select * from ICEB.Entry"); ResultSet rs=
	 * p.executeQuery(); while (rs.next()){ String str=rs.getString(2);
	 * System.out.println(str); } } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 */

	public void closeConnection() {
		try {
			System.out.print("  Closing Connection...\n");
			con.close();
			System.out.print("Connection With DB2 Server Closed !!! ");
		} catch (SQLException e) {
			displayStack(e);
		}
	}

	public void openConnection() throws DBException {
		try {
			System.out.print("Loading JDBC Driver  -> " + driverClass + "\n");
			Class.forName(driverClass).newInstance();
			System.out.print("Connecting to -> " + connectionURL + "\n");
			this.con = DriverManager.getConnection(connectionURL, userID, userPassword);
			System.out.print("Connected as         -> " + userID + "\n");
			System.out.print("Connection With DB2 Server Established !!! ");
		} catch (ClassNotFoundException e) {
			displayStack(e);
		} catch (InstantiationException e) {
			displayStack(e);
		} catch (IllegalAccessException e) {
			displayStack(e);
		} catch (SQLException e) {
			throw new DBException(visibleMessages.retrieveFor("DB_CLOSED"));
		}
	}

	public void displayStack(Exception e) {
		e.printStackTrace();
	}

	public void displayException(Exception e) {
		displayStack(e);
	}

}
