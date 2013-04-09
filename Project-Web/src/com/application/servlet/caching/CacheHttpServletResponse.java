package com.application.servlet.caching;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: CacheHttpServletResponse
 * 
 */
public class CacheHttpServletResponse implements HttpServletResponse {
	// Store key response variables so they can be set later
	private int status;

	private Hashtable<String,Object> headers;

	private int contentLength;

	private String contentType;

	private Locale locale;

	private Vector<Cookie> cookies;

	private boolean didError;

	private boolean didRedirect;

	private boolean gotStream;

	private boolean gotWriter;

	private HttpServletResponse delegate;

	private CacheServletOutputStream out;

	private PrintWriter writer;

	CacheHttpServletResponse(HttpServletResponse res) {
		delegate = res;
		try {
			out = new CacheServletOutputStream(res.getOutputStream());
		} catch (IOException e) {
			System.out.println("Got IOException constructing cached response: "
					+ e.getMessage());
		}
		internalReset();
	}

	private void internalReset() {
		status = 200;
		headers = new Hashtable<String,Object>();
		contentLength = -1;
		contentType = null;
		locale = null;
		cookies = new Vector<Cookie>();
		didError = false;
		didRedirect = false;
		gotStream = false;
		gotWriter = false;
		out.getBuffer().reset();
	}

	public boolean isValid() {
		// We don't cache error pages or redirects
		return didError != true && didRedirect != true;
	}

	private void internalSetHeader(String name, Object value) {
		Vector<Object> v = new Vector<Object>();
		v.addElement(value);
		headers.put(name, v);
	}

	private void internalAddHeader(String name, Object value) {
		Vector<Object> v = (Vector) headers.get(name);
		if (v == null) {
			v = new Vector<Object>();
		}
		v.addElement(value);
		headers.put(name, v);
	}

	public void writeTo(HttpServletResponse res) {
		// Write status code
		res.setStatus(status);
		// Write convenience headers
		if (contentType != null)
			res.setContentType(contentType);
		if (locale != null)
			res.setLocale(locale);
		// Write cookies
		Enumeration enum1 = cookies.elements();
		while (enum1.hasMoreElements()) {
			Cookie c = (Cookie) enum1.nextElement();
			res.addCookie(c);
		}
		// Write standard headers
		enum1 = headers.keys();
		while (enum1.hasMoreElements()) {
			String name = (String) enum1.nextElement();
			Vector values = (Vector) headers.get(name); // may have multiple
														// values
			Enumeration enum2 = values.elements();
			while (enum2.hasMoreElements()) {
				Object value = enum2.nextElement();
				if (value instanceof String) {
					res.setHeader(name, (String) value);
				}
				if (value instanceof Integer) {
					res.setIntHeader(name, ((Integer) value).intValue());
				}
				if (value instanceof Long) {
					res.setDateHeader(name, ((Long) value).longValue());
				}
			}
		}
		// Write content length
		res.setContentLength(out.getBuffer().size());
		// Write body
		try {
			out.getBuffer().writeTo(res.getOutputStream());
		} catch (IOException e) {
			System.out.println("Got IOException writing cached response: "
					+ e.getMessage());
		}
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (gotWriter) {
			throw new IllegalStateException(
					"Cannot get output stream after getting writer");
		}
		gotStream = true;
		return out;
	}

	public PrintWriter getWriter() throws UnsupportedEncodingException {
		if (gotStream) {
			throw new IllegalStateException(
					"Cannot get writer after getting output stream");
		}
		gotWriter = true;
		if (writer == null) {
			OutputStreamWriter w = new OutputStreamWriter(out,
					getCharacterEncoding());
			writer = new PrintWriter(w, true); // autoflush is necessary
		}
		return writer;
	}

	public void setContentLength(int len) {
		delegate.setContentLength(len);
		// No need to save the length; we can calculate it later
	}

	public void setContentType(String type) {
		delegate.setContentType(type);
		contentType = type;
	}

	public String getCharacterEncoding() {
		return delegate.getCharacterEncoding();
	}

	public void setBufferSize(int size) throws IllegalStateException {
		delegate.setBufferSize(size);
	}

	public int getBufferSize() {
		return delegate.getBufferSize();
	}

	public void reset() throws IllegalStateException {
		delegate.reset();
		internalReset();
	}

	public boolean isCommitted() {
		return delegate.isCommitted();
	}

	public void flushBuffer() throws IOException {
		delegate.flushBuffer();
	}

	public void setLocale(Locale loc) {
		delegate.setLocale(loc);
		locale = loc;
	}

	public Locale getLocale() {
		return delegate.getLocale();
	}

	public void addCookie(Cookie cookie) {
		delegate.addCookie(cookie);
		cookies.addElement(cookie);
	}

	public boolean containsHeader(String name) {
		return delegate.containsHeader(name);
	}

	/** @deprecated */
	public void setStatus(int sc, String sm) {
		delegate.setStatus(sc, sm);
		status = sc;
	}

	public void setStatus(int sc) {
		delegate.setStatus(sc);
		status = sc;
	}

	public void setHeader(String name, String value) {
		delegate.setHeader(name, value);
		internalSetHeader(name, value);
	}

	public void setIntHeader(String name, int value) {
		delegate.setIntHeader(name, value);
		internalSetHeader(name, new Integer(value));
	}

	public void setDateHeader(String name, long date) {
		delegate.setDateHeader(name, date);
		internalSetHeader(name, new Long(date));
	}

	public void sendError(int sc, String msg) throws IOException {
		delegate.sendError(sc, msg);
		didError = true;
	}

	public void sendError(int sc) throws IOException {
		delegate.sendError(sc);
		didError = true;
	}

	public void sendRedirect(String location) throws IOException {
		delegate.sendRedirect(location);
		didRedirect = true;
	}

	public String encodeURL(String url) {
		return delegate.encodeURL(url);
	}

	public String encodeRedirectURL(String url) {
		return delegate.encodeRedirectURL(url);
	}

	public void addHeader(String name, String value) {
		internalAddHeader(name, value);
	}

	public void addIntHeader(String name, int value) {
		internalAddHeader(name, new Integer(value));
	}

	public void addDateHeader(String name, long value) {
		internalAddHeader(name, new Long(value));
	}

	/** @deprecated */
	public String encodeUrl(String url) {
		return this.encodeURL(url);
	}

	/** @deprecated */
	public String encodeRedirectUrl(String url) {
		return this.encodeRedirectURL(url);
	}

	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#getHeader(java.lang.String)
	 */
	public String getHeader(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#getHeaderNames()
	 */
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#getHeaders(java.lang.String)
	 */
	public Collection<String> getHeaders(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletResponse#getStatus()
	 */
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
}