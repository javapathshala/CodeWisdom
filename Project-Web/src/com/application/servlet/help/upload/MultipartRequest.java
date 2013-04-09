package com.application.servlet.help.upload;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * ***** Modification History ***** Release - BBIB porting Modifications: Added
 * unimplemented methods of httpServletRequest Interface
 * 
 */
public class MultipartRequest implements HttpServletRequest {

	private static final int DEFAULT_MAX_POST_SIZE = 1024 * 1024;

	private ServletRequest req;

	private File dir;

	private int maxSize;

	private Hashtable<Object,Object> parameters = new Hashtable<Object,Object>();

	private Hashtable<Object,Object> files = new Hashtable<Object,Object>();

	private Hashtable<Object,Object> fileNames = new Hashtable<Object,Object>();

	private boolean storeInternally = true;

	private boolean isLimitExceed = false;

	public MultipartRequest(ServletRequest request, int maxPostSize)
			throws IOException {
		if (request == null) {
			throw new IllegalArgumentException(" request cant be null");
		}

		if (maxPostSize <= 0) {
			throw new IllegalArgumentException("maxPostsize must be positive");
		}

		// save the request,dir and maxsize
		req = request;
		maxSize = maxPostSize;

		readRequest();
	}

	public MultipartRequest(ServletRequest request, String saveDirectory)
			throws IOException {
		this(request, saveDirectory, DEFAULT_MAX_POST_SIZE);
	}

	public MultipartRequest(ServletRequest request, String saveDirectory,
			int maxPostSize) throws IOException {
		// sanity check values
		if (request == null) {
			throw new IllegalArgumentException(" request cant be null");
		}

		if (saveDirectory == null) {
			throw new IllegalArgumentException("saveDirectory cant be null");
		}

		if (maxPostSize <= 0) {
			throw new IllegalArgumentException("maxPostsize must be positive");
		}

		// save the request,dir and maxsize
		req = request;
		dir = new File(saveDirectory);
		maxSize = maxPostSize;

		// check saveDirectory is truely a directory
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("not a directory: "
					+ saveDirectory);
		}

		// check saveDirectory is writable
		if (!dir.canWrite()) {
			throw new IllegalArgumentException("not writable:" + saveDirectory);
		}

		storeInternally = false;

		// now parse the request saving data to "parameters" and "files";
		// write the file contents to saveDirectory
		readRequest();
	}

	private String extractBoundary(String line) {
		int index = line.indexOf("boundary=");
		if (index == -1) {
			return null;
		}

		String boundary = line.substring(index + 9);
		boundary = "--" + boundary;
		return boundary;
	}

	private String extractContentType(String line) throws IOException {
		String contentType = null;

		// convert the line to a lowercase string
		String origline = line;
		line = origline.toLowerCase();

		if (line.startsWith("content-type")) {
			int start = line.indexOf(" ");

			if (start == -1) {
				throw new IOException("content type corrupt: " + origline);
			}

			contentType = line.substring(start + 1);
		} else if (line.length() != 0) {
			// no content typt,so should be empty
			throw new IOException("malformed line after disposition:"
					+ origline);
		}

		return contentType;
	}

	private String[] extractDispositionInfo(String line) throws IOException {
		String[] retval = new String[3];
		String origline = line;
		line = origline.toLowerCase();

		int start = line.indexOf("content-disposition");
		int end = line.indexOf(";");
		if (start == -1 || end == -1) {
			throw new IOException("cont disposition corrupt" + origline);
		}

		String disposition = line.substring(start + 21, end);
		if (!disposition.equals("form-data")) {
			throw new IOException("invalid content disposition" + disposition);
		}

		start = line.indexOf("name=\"", end);
		end = line.indexOf("\"", start + 7);
		if (start == -1 || end == -1) {
			throw new IOException("content dispostion corrupt" + origline);
		}

		String name = origline.substring(start + 6, end);
		String filename = null;
		start = line.indexOf("filename=\"", end + 2);
		end = line.indexOf("\"", start + 10);
		if (start != -1 && end != -1) {
			filename = origline.substring(start + 10, end);
			int s = filename.lastIndexOf("\\");
			filename = filename.substring(s + 1);

			if (filename.equals("")) {
				filename = "unknown";
			}
		}
		retval[0] = disposition;
		retval[1] = name;
		retval[2] = filename;
		return retval;
	}

	/**
	 * getAttribute method comment.
	 */
	public java.lang.Object getAttribute(java.lang.String arg1) {
		return null;
	}

	/**
	 * getAttributeNames method comment.
	 */
	public java.util.Enumeration getAttributeNames() {
		return null;
	}

	/**
	 * getAuthType method comment.
	 */
	public java.lang.String getAuthType() {
		return null;
	}

	/**
	 * getCharacterEncoding method comment.
	 */
	public java.lang.String getCharacterEncoding() {
		return null;
	}

	/**
	 * getContentLength method comment.
	 */
	public int getContentLength() {
		return 0;
	}

	/**
	 * getContentType method comment.
	 */
	public java.lang.String getContentType() {
		return null;
	}

	public String getContentType(String name) {
		try {
			UploadedFile file = (UploadedFile) files.get(name);
			return file.getContentType();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * getContextPath method comment.
	 */
	public java.lang.String getContextPath() {
		return null;
	}

	/**
	 * getCookies method comment.
	 */
	public javax.servlet.http.Cookie[] getCookies() {
		return null;
	}

	/**
	 * getDateHeader method comment.
	 */
	public long getDateHeader(java.lang.String arg1) {
		return 0;
	}

	public File getFile(String name) {
		try {
			UploadedFile file = (UploadedFile) files.get(name);
			return file.getFile();
		} catch (Exception e) {
			return null;
		}
	}

	public Enumeration getFileNames() {
		return fileNames.keys();
	}

	public String getElementValue(String _name) {
		return (String) fileNames.get(_name);
	}

	public byte[] getFileByteArray(String _file) {
		return (byte[]) files.get(_file);
	}

	public String getFilesystemName(String name) {

		try {
			UploadedFile file = (UploadedFile) files.get(name);
			return file.getFilesystemName();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * getHeader method comment.
	 */
	public java.lang.String getHeader(java.lang.String arg1) {
		return null;
	}

	/**
	 * getHeaderNames method comment.
	 */
	public java.util.Enumeration getHeaderNames() {
		return null;
	}

	/**
	 * getHeaders method comment.
	 */
	public java.util.Enumeration getHeaders(java.lang.String arg1) {
		return null;
	}

	/**
	 * getInputStream method comment.
	 */
	public javax.servlet.ServletInputStream getInputStream()
			throws java.io.IOException {
		return null;
	}

	/**
	 * getIntHeader method comment.
	 */
	public int getIntHeader(java.lang.String arg1) {
		return 0;
	}

	/**
	 * getLocale method comment.
	 */
	public java.util.Locale getLocale() {
		return null;
	}

	/**
	 * getLocales method comment.
	 */
	public java.util.Enumeration getLocales() {
		return null;
	}

	/**
	 * getMethod method comment.
	 */
	public java.lang.String getMethod() {
		return null;
	}

	public String getParameter(String name) {

		try {
			String param = (String) parameters.get(name);
			if (param.equals(""))
				return null;
			return param;
		} catch (Exception e) {
			return null;
		}
	}

	public Enumeration getParameterNames() {
		return parameters.keys();
	}

	/**
	 * getParameterValues method comment.
	 */
	public java.lang.String[] getParameterValues(java.lang.String arg1) {
		return null;
	}

	/**
	 * getPathInfo method comment.
	 */
	public java.lang.String getPathInfo() {
		return null;
	}

	/**
	 * getPathTranslated method comment.
	 */
	public java.lang.String getPathTranslated() {
		return null;
	}

	/**
	 * getProtocol method comment.
	 */
	public java.lang.String getProtocol() {
		return null;
	}

	/**
	 * getQueryString method comment.
	 */
	public java.lang.String getQueryString() {
		return null;
	}

	/**
	 * getReader method comment.
	 */
	public java.io.BufferedReader getReader() throws java.io.IOException {
		return null;
	}

	/**
	 * getRealPath method comment.
	 */
	public java.lang.String getRealPath(java.lang.String arg1) {
		return null;
	}

	/**
	 * getRemoteAddr method comment.
	 */
	public java.lang.String getRemoteAddr() {
		return null;
	}

	/**
	 * getRemoteHost method comment.
	 */
	public java.lang.String getRemoteHost() {
		return null;
	}

	/**
	 * getRemoteUser method comment.
	 */
	public java.lang.String getRemoteUser() {
		return null;
	}

	/**
	 * getRequestDispatcher method comment.
	 */
	public javax.servlet.RequestDispatcher getRequestDispatcher(
			java.lang.String arg1) {
		return null;
	}

	/**
	 * getRequestedSessionId method comment.
	 */
	public java.lang.String getRequestedSessionId() {
		return null;
	}

	/**
	 * getRequestURI method comment.
	 */
	public java.lang.String getRequestURI() {
		return null;
	}

	/**
	 * getScheme method comment.
	 */
	public java.lang.String getScheme() {
		return null;
	}

	/**
	 * getServerName method comment.
	 */
	public java.lang.String getServerName() {
		return null;
	}

	/**
	 * getServerPort method comment.
	 */
	public int getServerPort() {
		return 0;
	}

	/**
	 * getServletPath method comment.
	 */
	public java.lang.String getServletPath() {
		return null;
	}

	/**
	 * getSession method comment.
	 */
	public javax.servlet.http.HttpSession getSession() {
		return null;
	}

	/**
	 * getSession method comment.
	 */
	public javax.servlet.http.HttpSession getSession(boolean arg1) {
		return null;
	}

	/**
	 * getUserPrincipal method comment.
	 */
	public java.security.Principal getUserPrincipal() {
		return null;
	}

	/**
	 * isRequestedSessionIdFromCookie method comment.
	 */
	public boolean isRequestedSessionIdFromCookie() {
		return false;
	}

	/**
	 * isRequestedSessionIdFromUrl method comment.
	 */
	public boolean isRequestedSessionIdFromUrl() {
		return false;
	}

	/**
	 * isRequestedSessionIdFromURL method comment.
	 */
	public boolean isRequestedSessionIdFromURL() {
		return false;
	}

	/**
	 * isRequestedSessionIdValid method comment.
	 */
	public boolean isRequestedSessionIdValid() {
		return false;
	}

	/**
	 * isSecure method comment.
	 */
	public boolean isSecure() {
		return false;
	}

	/**
	 * isUserInRole method comment.
	 */
	public boolean isUserInRole(java.lang.String arg1) {
		return false;
	}

	protected void readAndSaveFile(MultipartInputStreamHandler in,
			String boundary, String filename) throws IOException {

		OutputStream os = null;

		// work out where to put the file
		if (storeInternally) {
			os = new ByteArrayOutputStream();
		} else {
			File f = new File(dir + File.separator + filename);
			os = new FileOutputStream(f);
		}

		BufferedOutputStream out = new BufferedOutputStream(os, 8 * 1024);

		byte[] bbuf = new byte[8 * 1024];
		int result;
		String line;

		// ServletInputSteam.readLine() has the annoying habit of
		// adding a \r\n to the end of the last line
		// since we want a byte-for-byte transfer,we have to cut those chars

		boolean rnflag = false;

		while ((result = in.readLine(bbuf, 0, bbuf.length)) != -1) {
			// check for the boundary

			if (result > 2 && bbuf[0] == '-' && bbuf[1] == '-') {
				line = new String(bbuf, 0, result, "ISO-8859-1");

				if (line.startsWith(boundary)) {
					break;
				}
			}

			// are we suppose to write \r\n for the last iteration ?
			if (rnflag) {
				out.write('\r');
				out.write('\n');
				rnflag = false;
			}

			// write the buffer ,postpone any editing \r\n
			if (result >= 2 && bbuf[result - 2] == '\r'
					&& bbuf[result - 1] == '\n') {
				out.write(bbuf, 0, result - 2);
				rnflag = true;
			} else {
				out.write(bbuf, 0, result);
			}
		}

		out.flush();
		out.close();
		os.close();
	}

	protected byte[] readFile(MultipartInputStreamHandler in, String boundary,
			String filename) throws IOException {

		OutputStream os = null;

		os = new ByteArrayOutputStream();

		BufferedOutputStream out = new BufferedOutputStream(os, 8 * 1024);

		byte[] bbuf = new byte[8 * 1024];
		int result;
		String line;

		// ServletInputSteam.readLine() has the annoying habit of
		// adding a \r\n to the end of the last line
		// since we want a byte-for-byte transfer,we have to cut those chars

		boolean rnflag = false;

		while ((result = in.readLine(bbuf, 0, bbuf.length)) != -1) {
			// check for the boundary

			if (result > 2 && bbuf[0] == '-' && bbuf[1] == '-') {
				line = new String(bbuf, 0, result, "ISO-8859-1");

				if (line.startsWith(boundary)) {
					break;
				}
			}

			// are we suppose to write \r\n for the last iteration ?
			if (rnflag) {
				out.write('\r');
				out.write('\n');
				rnflag = false;
			}

			// write the buffer ,postpone any editing \r\n
			if (result >= 2 && bbuf[result - 2] == '\r'
					&& bbuf[result - 1] == '\n') {
				out.write(bbuf, 0, result - 2);
				rnflag = true;
			} else {
				out.write(bbuf, 0, result);
			}
		}

		out.flush();
		out.close();
		os.close();

		return ((ByteArrayOutputStream) os).toByteArray();
	}

	protected boolean readNextPart(MultipartInputStreamHandler in,
			String boundary) throws IOException {
		// read the first line ,should look like this
		// content-disposition:form-data;name="field1";filename="file1.txt"

		String line = in.readLine();

		if (line == null) {
			// no parts left,we'r done
			return true;
		}

		// parse the content-disposition line

		String[] dispInfo = extractDispositionInfo(line);
		//String disposition = dispInfo[0];
		String name = dispInfo[1];
		String filename = dispInfo[2];

		// now onto the next line.this will either be empty
		// or contain a content-type and an empty line.

		line = in.readLine();

		if (line == null) {
			// no parts left,we/r done
			return true;
		}

		// get the content type.or null if none is specified
		String contentType = extractContentType(line);

		if (contentType != null) {
			// eaat the empty line
			line = in.readLine();

			if (line == null || line.length() > 0) {
				// line should be empty
				throw new IOException("malformed line after content type: "
						+ line);
			}
		} else {
			// assume a default content type
			contentType = "application/octet-stream";
		}

		// now,finally we read the content (end after reading the boundary)

		if (filename == null) {
			// this is a parameter
			String value = readParameter(in, boundary);
			parameters.put(name, value);
		} else {
			// this is a file
			if (storeInternally) {
				if (isLimitExceed) {
					parameters.put("simple_LimitExceed_text", byteConversion());
					// here close means skip all other bytes.
					in.close();
					return true;
				} else {
					byte[] file = readFile(in, boundary, filename);
					fileNames.put(name, filename);
					files.put(filename, file);
				}
			} else {
				readAndSaveFile(in, boundary, filename);
				files.put(name, new UploadedFile(dir.toString(), filename,
						contentType));
			}
		}

		return false;
	}

	public String getElementName(String fileName) {
		return (String) fileNames.get(fileName);

	}

	private String byteConversion() {
		String value;
		int remainderForKB = maxSize / 1024;
		if (remainderForKB > 1024) {
			int remainderForMB = remainderForKB / 1024;
			if (remainderForMB > 1024) {
				int remainderForGB = remainderForMB / 1024;
				value = remainderForGB + "GB";
			} else {
				value = remainderForMB + "MB";
			}
		} else {
			if (remainderForKB == 0)
				value = maxSize + "Bytes";
			else
				value = remainderForKB + "KB";
		}
		return value;
	}

	protected String readParameter(MultipartInputStreamHandler in,
			String boundary) throws IOException {
		StringBuffer sbuf = new StringBuffer();
		String line;

		while ((line = in.readLine()) != null) {
			if (line.startsWith(boundary)) {
				break;
			}

			// add the \r\n in case there are many lines
			sbuf.append(line + "\r\n");
		}

		if (sbuf.length() == 0) {
			return null;
		}

		sbuf.setLength(sbuf.length() - 2); // cut off the last line's \r\n

		return sbuf.toString(); // no URL decoding is needed
	}

	protected void readRequest() throws IOException {
		// check the content type to make sure it's "multipart/form-data"
		String type = req.getContentType();
		if (type == null
				|| !type.toLowerCase().startsWith("multipart/form-data")) {
			throw new IOException(
					"posted content type isn't multipart/form-data");
		}

		// check the content length to prevent denial of service attacks
		int length = req.getContentLength();

		if (length > maxSize) {
			// throw new IOException( "posted content length of " + length +
			// "exceeds limit of " + maxSize );
			isLimitExceed = true;
		}

		// get the boundary string ;it's included in the content type.
		// should look something like "------------------------12012133613061"

		String boundary = extractBoundary(type);

		if (boundary == null) {
			throw new IOException("seperation boundary was not specified");
		}

		// construct the special input stream we'll read from
		MultipartInputStreamHandler in = new MultipartInputStreamHandler(req
				.getInputStream(), boundary, length);

		// read the first line ,should be the first boundary
		String line = in.readLine();

		if (line == null) {
			throw new IOException("corrupt form data: premature ending");
		}

		// verify that the line is the boundary
		if (!line.startsWith(boundary)) {
			throw new IOException("corrupt form data: no leading boundary");
		}

		// now that we are just beyond the first boundary, loop over each part
		boolean done = false;
		while (!done) {
			done = readNextPart(in, boundary);
		}
	}

	/**
	 * removeAttribute method comment.
	 */
	public void removeAttribute(java.lang.String arg1) {
	}

	/**
	 * setAttribute method comment.
	 */
	public void setAttribute(java.lang.String arg1, java.lang.Object arg2) {
	}

	// start code change for imports op code server name , ip address
	public ServletRequest getRequest() {
		return req;
	}// end code change for imports op code server name , ip address

	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map getParameterMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setCharacterEncoding(String arg0)
			throws UnsupportedEncodingException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getAsyncContext()
	 */
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getDispatcherType()
	 */
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#getServletContext()
	 */
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isAsyncStarted()
	 */
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#isAsyncSupported()
	 */
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#startAsync()
	 */
	public AsyncContext startAsync() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletRequest#startAsync(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	public AsyncContext startAsync(ServletRequest arg0, ServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#authenticate(javax.servlet.http.HttpServletResponse)
	 */
	public boolean authenticate(HttpServletResponse arg0) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getPart(java.lang.String)
	 */
	public Part getPart(String arg0) throws IOException, IllegalStateException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#getParts()
	 */
	public Collection<Part> getParts() throws IOException, IllegalStateException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#login(java.lang.String, java.lang.String)
	 */
	public void login(String arg0, String arg1) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServletRequest#logout()
	 */
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}

}