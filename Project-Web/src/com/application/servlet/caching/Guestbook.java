package com.application.servlet.caching;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class for Servlet: Guestbook
 * 
 */
public class Guestbook extends CacheHttpServlet {

	private static final long serialVersionUID = 1L;

	private Vector<GuestbookEntry> entries = new Vector<GuestbookEntry>(); // User
																			// entry
																			// list

	private long lastModified = 0; // Time last entry was added

	// Display the current entries, then ask for a new entry
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		printHeader(out);
		printForm(out);
		printMessages(out);
		printFooter(out);
	}

	// Add a new entry, then dispatch back to doGet()
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		handleForm(req, res);
		doGet(req, res);
	}

	private void printHeader(PrintWriter out) throws ServletException {
		out.println("<HTML><HEAD><TITLE>Guestbook</TITLE></HEAD>");
		out.println("<BODY>");
	}

	private void printForm(PrintWriter out) throws ServletException {
		out.println("<FORM METHOD=POST>"); // posts to itself
		out.println("<B>Please submit your feedback:</B><BR>");
		out.println("Your name: <INPUT TYPE=TEXT NAME=name><BR>");
		out.println("Your email: <INPUT TYPE=TEXT NAME=email><BR>");
		out.println("Comment: <INPUT TYPE=TEXT SIZE=50 NAME=comment><BR>");
		out.println("<INPUT TYPE=SUBMIT VALUE=\"Send Feedback\"><BR>");
		out.println("</FORM>");
		out
				.println("<A HREF=\"http://www.servlets.com/soapbox/freecache.html\">");
		out.println("Back to the article</A>");

		out.println("<HR>");
	}

	private void printMessages(PrintWriter out) throws ServletException {
		String name, email, comment;
		int numEntries = 0;

		Enumeration e = entries.elements();
		while (e.hasMoreElements()) {
			numEntries++;
			GuestbookEntry entry = (GuestbookEntry) e.nextElement();
			name = entry.name;
			if (name == null)
				name = "Unknown user";
			email = entry.email;
			if (name == null)
				email = "Unknown email";
			comment = entry.comment;
			if (comment == null)
				comment = "No comment";
			out.println("<DL>");
			out.println("<DT><B>" + name + "</B> (" + email + ") says");
			out.println("<DD><PRE>" + comment + "</PRE>");
			out.println("</DL>");

			// Sleep for 0.2 seconds to simulate a slow data source, max at 5.0
			// sec
			if (numEntries * 0.2 <= 5.0) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException ignored) {
				}
			}
		}
	}

	private void printFooter(PrintWriter out) throws ServletException {
		out.println("</BODY>");
	}

	private void handleForm(HttpServletRequest req, HttpServletResponse res) {
		GuestbookEntry entry = new GuestbookEntry();

		entry.name = req.getParameter("name");
		entry.email = req.getParameter("email");
		entry.comment = req.getParameter("comment");

		entries.addElement(entry);

		// Make note we have a new last modified time
		lastModified = System.currentTimeMillis();
	}

	public long getLastModified(HttpServletRequest req) {
		return lastModified;
	}
}

class GuestbookEntry {
	public String name;

	public String email;

	public String comment;
}