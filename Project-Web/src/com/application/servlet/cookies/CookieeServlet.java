package com.application.servlet.cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: CookieeServlet
 *
 */
 public class CookieeServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -6157093533662350504L;


	public CookieeServlet() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		for(int i=0; i<3; i++) {
			//Default maxAge is -1, indicating cookie applies only to current browsing session.
			Cookie cookie = new Cookie("Dimit"+i,"Cookie-Value-S"+i);
			cookie.setPath("/");
			response.addCookie(cookie);
			//cookie = new Cookie("aaaaaa " + i,"Cookie-Value-P" + i);
			//Cookie is valid for an hour, regardless of whether user quits browser, reboots computer, or whatever.
			//cookie.setMaxAge(3600);
		//	response.addCookie(cookie);
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(
		"<BODY BGCOLOR=\"#FDF5E6\">\n" +
		"<H1 ALIGN=\"CENTER\">" + "title" + "</H1>\n" +
		"There are six cookies associated with this page.\n" +
		"To see them, visit the\n" +
		"<A HREF=\"/MeWeb/ShowCookies\">\n" +
		"<CODE>ShowCookies</CODE> servlet</A>.\n" +
		"<P>\n" +
		"Three of the cookies are associated only with the\n" +
		"current session, while three are persistent.\n" +
		"Quit the browser, restart, and return to the\n" +
		"<CODE>ShowCookies</CODE> servlet to verify that\n" +
		"the three long-lived ones persist across sessions.\n" +
		"</BODY></HTML>");		
	}  	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}   	  	    
}