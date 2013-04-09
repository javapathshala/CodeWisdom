package com.application.servlet.help.upload;

import java.io.IOException;

import javax.servlet.ServletInputStream;

//A class to aid in reading multipart/form-data from a ServletInputStream
//It keeps track of how many bytes have been read & detects when the
//content-Length limit has been reached .this is necessary bcos some
//servlet engines are slow to notice the end of stream

class MultipartInputStreamHandler {

	ServletInputStream in;

	String boundary;

	int totalExpected;

	int totalRead = 0;

	byte[] buf = new byte[8 * 1024];

	public MultipartInputStreamHandler(ServletInputStream in, String boundary,
			int totalExpected) {
		this.in = in;
		this.boundary = boundary;
		this.totalExpected = totalExpected;
	}

	public String readLine() throws IOException {
		StringBuffer sbuf = new StringBuffer();
		int result;
		String line;

		do {
			result = this.readLine(buf, 0, buf.length);
			if (result != -1) {
				sbuf.append(new String(buf, 0, result, "ISO-8859-1"));
			}

		} while (result == buf.length);

		if (sbuf.length() == 0) {
			return null;
		}
		sbuf.setLength(sbuf.length() - 2);
		return sbuf.toString();
	}

	public int readLine(byte b[], int off, int len) throws IOException {
		if (totalRead >= totalExpected) {
			return -1;
		} else {
			int result = in.readLine(b, off, len);
			if (result > 0) {
				totalRead += result;
			}
			return result;
		}
	}

	public void close() throws IOException {
		this.in.skip(totalExpected - totalRead);
	}
}
