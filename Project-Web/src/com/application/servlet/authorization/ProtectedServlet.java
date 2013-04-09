package com.application.servlet.authorization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;



/**
 * Example of password-protected pages handled directly by servlets.
 * 
 */
public class ProtectedServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	private static final long serialVersionUID = 3341604613075261675L;

	private Properties passwords;

	private String passwordFile;

	public ProtectedServlet() {
		super();
	}

	/**
	 * Read the password file from the location specified by the passwordFile
	 * initialization parameter.
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			passwordFile = config.getInitParameter("passwordFile");
			passwords = new Properties();
			passwords.load(new FileInputStream(passwordFile));
		} catch (IOException ioe) {
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		passwords = new Properties();
		passwords.load(new FileInputStream("C:"+"/"+"password.properties"));// use init config instead of this.
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String authorization = request.getHeader("Authorization");		
		if (authorization == null) {
			askForPassword(response);
		}else{
			String userInfo = authorization.substring(6).trim();
			BASE64Decoder decoder = new BASE64Decoder();
			String nameAndPassword =new String(decoder.decodeBuffer(userInfo));
			int index = nameAndPassword.indexOf(":");
			String user = nameAndPassword.substring(0, index);
			String password = nameAndPassword.substring(index+1);
			String realPassword = passwords.getProperty(user);
			//response.setIntHeader("Refresh", 10);
			if ((realPassword != null) &&(realPassword.equals(password))){
				String title = "Welcome to the Protected Page";
				out.println("<BODY BGCOLOR=\"#FDF5E6\">\n" +
				"<H1 ALIGN=CENTER>" + title + "</H1>\n" +
				"Congratulations. You have accessed a\n" +
				"highly proprietary company document.\n" +
				"Shred or eat all hardcopies before\n" +
				"going to bed tonight.\n" +
				"</BODY></HTML>");
			}else{
				askForPassword(response);
			}
		}
		

	}

	/**
	 * If no Authorization header was supplied in the request.
	 * @param response
	 */
	private void askForPassword(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Ie 401
		response.setHeader("WWW-Authenticate","BASIC realm=\"privileged-few\"");
		
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}