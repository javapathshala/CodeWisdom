package com.application.servlet.sessionFixation;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: SessionFixation
 * 
 */
public class SessionFixation extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7030094281576316357L;

	public SessionFixation() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		resetSession(request, response);

	}

	// WASA02 Session Fixation Attack Start
	private void resetSession(HttpServletRequest req, HttpServletResponse res) {

		HttpSession httpSession = req.getSession(false);
		System.out.println("Old session is : " + httpSession.getId());
		Map<String, Object> tempStore = new HashMap<String, Object>();
		String tempAttributeName;
		Enumeration sessionAttributes = httpSession.getAttributeNames();
		while (sessionAttributes.hasMoreElements()) {
			tempAttributeName = sessionAttributes.nextElement().toString();
			tempStore.put(tempAttributeName, httpSession
					.getAttribute(tempAttributeName));
		}
		if (httpSession != null) {
			httpSession.invalidate();
			httpSession = null;
		}
		httpSession = req.getSession(true);
		Iterator keySet = tempStore.keySet().iterator();
		String tempSessionKey;
		while (keySet.hasNext()) {
			tempSessionKey = keySet.next().toString();
			httpSession.setAttribute(tempSessionKey, tempStore
					.get(tempSessionKey));
		}
		System.out.println(httpSession.getId());
	}
	// WASA02 Session Fixation Attack End
}