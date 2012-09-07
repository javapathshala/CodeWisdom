/*
 * File: FtpTransferSun.java
 * Date: 30-Apr-2012
 *
 * Copyright (C) 2012, Java Pathshala
 * All rights reserved.
 * Visit us at blog http://javapathshala.wordpress.com
 * Cloud Applications at http://dimitcloud.cloudfoundry.com
 */
package com.jp.koncept.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import sun.net.TelnetOutputStream;


import sun.net.ftp.FtpClient;

/**
 * Description :
 * @author dimit.chadha
 */
@SuppressWarnings("restriction")
public class FtpTransferSun {

	/**
	 * Description :
	 * @param args
	 */
	public static void main(String[] args) {
		// String host = args[0];
		// String path = args[1];
		// String username = args[2];
		// String password = args[3];

		String server = "ftp.india.rsystems.com";
		String username = "Sanare";
		String password = "Sa12Nare1";

		// int lastSlash = path.lastIndexOf('/');
		// String filename = path.substring(lastSlash+1);
		// String directory = path.substring(0,lastSlash);

		FtpClient ftpClient = new FtpClient();
		try {

			ftpClient.openServer(server);
			ftpClient.login(username, password);
			ftpClient.cd("SanareIntegrationBroker//TestCSV");
			ftpClient.binary();

			TelnetOutputStream netOut = ftpClient.put("D:\\FCF_ET\\Test.csv");
			File file = new File("D:\\FCF_ET\\Test.csv");
			// Now transfer the file contents
			ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(file));
			byte c[] = new byte[1000];
			int read = 0;
			while ((read = fileIn.read(c)) != -1) {
				netOut.write(c, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
