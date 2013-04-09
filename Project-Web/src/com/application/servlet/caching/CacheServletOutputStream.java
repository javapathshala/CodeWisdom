package com.application.servlet.caching;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

/**
 * Servlet implementation class for Servlet: CacheServletOutputStream
 * 
 */
public class CacheServletOutputStream extends ServletOutputStream {

	ServletOutputStream delegate;

	ByteArrayOutputStream cache;

	CacheServletOutputStream(ServletOutputStream out) {
		delegate = out;
		cache = new ByteArrayOutputStream(4096);
	}

	public ByteArrayOutputStream getBuffer() {
		return cache;
	}

	public void write(int b) throws IOException {
		delegate.write(b);
		cache.write(b);
	}

	public void write(byte b[]) throws IOException {
		delegate.write(b);
		cache.write(b);
	}

	public void write(byte buf[], int offset, int len) throws IOException {
		delegate.write(buf, offset, len);
		cache.write(buf, offset, len);
	}
}