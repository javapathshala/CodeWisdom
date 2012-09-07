package com.jp.koncept.ftp;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpTransfer {

	/**
	 * @param args
	 */
	boolean ftpTransfer(String localfile, String destinationfile) {
		String server = "ftp.india.rsystems.com";
		String username = "Sanare";
		String password = "Sa12Nare1";
		try {
			FTPClient ftp = new FTPClient();
			ftp.connect(server);
			if (!ftp.login(username, password)) {
				ftp.logout();
				return false;
			}
			int reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			InputStream in = new FileInputStream(localfile);
			ftp.setFileType(ftp.BINARY_FILE_TYPE);
			ftp.changeWorkingDirectory("SanareIntegrationBroker//TestCSV");
		
			boolean Store = ftp.storeFile(destinationfile, in);
			in.close();
			ftp.logout();
			ftp.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]) {

		FtpTransfer ftpTransfer=new FtpTransfer();
		ftpTransfer.ftpTransfer("D:\\FCF_ET\\Test.csv", "Test.csv");
	}
}
