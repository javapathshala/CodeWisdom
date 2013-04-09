package com.application.servlet.bib.helpText;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jp.application.logger.TechLogger;


/**
 * Help Text Servlet attached to every presentation page/screen to show the help
 * buttons against every screen
 * 
 * @author dimit
 * 
 */
public class HelpTextServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String pageAlias = "helpPageAlias";

	//private static TechLogger logger = TechLogger.getBaseLogger(HelpTextServlet.class);

	private static long CACHE_INTERVAL = 0;

	private static Hashtable<String, String> timeTable;

	private static Hashtable<String, String> helpTable;

	public final void init(ServletConfig config) throws ServletException {
		super.init(config);		
		CACHE_INTERVAL = Long.parseLong(ResourceBundle.getBundle("admin")
				.getString("HELP_TEXT_CACHED_INTERVAL").trim());
		timeTable = new Hashtable<String, String>();
		helpTable = new Hashtable<String, String>();
		//logger.info("Help Servlet Initialised");
	}

	/*
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	/*
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pageId = request.getParameter(pageAlias);
		long lastCachedLong = 0;
		String javaScript = null;
		String lastCached = null;
		long currentTime = System.currentTimeMillis();
		synchronized (timeTable) {
			lastCached = timeTable.get(pageId);
			if (null != lastCached) {
				lastCachedLong = Long.parseLong(lastCached);
			}
			boolean expire = (currentTime - lastCachedLong) > CACHE_INTERVAL * 1000L;
			if (!expire) {
				javaScript = searchCache(pageId);
			}
			if (null == javaScript || expire) {
				clearCache(pageId);
				// retrive from database & build cache
				javaScript = getDBHelpCache(pageId);
			}
		}
		response.setContentType("text/javascript");
		response.setHeader("Cache-control", "max-age=" + CACHE_INTERVAL);
		response.setDateHeader("Expires", currentTime + CACHE_INTERVAL);
		if (null != javaScript) {
			PrintWriter out = response.getWriter();
			out.println(javaScript);
		}
	}

	/**
	 * Search the cache
	 * 
	 * @param pageId
	 * @return
	 */
	private String searchCache(String pageId) {
		return helpTable.get(pageId);
	}

	/**
	 * Get Help contents from D
	 * 
	 * @param pageId
	 * @return
	 * @throws FrameworkSystemException
	 * @throws FrameworkUserException
	 */
	private String getDBHelpCache(String pageId) {
		ManageHelp help = null;// GetShowHelpCommand.getHelpText(pageId);
		String javaScript = null;
		if (null != help) {
			javaScript = buildCache(help, pageId);
		}
		return javaScript;
	}

	/**
	 * Clear the Cache
	 * 
	 * @param pageId
	 */
	private void clearCache(String pageId) {
		helpTable.remove(pageId);
		timeTable.remove(pageId);
	}

	/**
	 * Build data cache
	 * 
	 * @param help
	 * @param pageId
	 */
	private String buildCache(ManageHelp help, String pageId) {
		if (null != help) {
			timeTable.put(pageId, String.valueOf(help.getLastCached()));
		}
		StringBuffer javaScript = new StringBuffer();
		javaScript.append("if (window.attachEvent)");
		javaScript.append("window.attachEvent(\"onload\", setUpHelp);");
		javaScript.append("else onload = setUpHelp;");
		javaScript.append("var pageId='" + pageId + "';");
		javaScript.append("function setUpHelp(){");
		if (null != help) {
			List<ManageHelpDetails> helpDetails = help.getHelpDetails();
			Iterator<ManageHelpDetails> itr = helpDetails.iterator();
			while (itr.hasNext()) {
				ManageHelpDetails helpContents = itr.next();
				javaScript.append("var fieldId='" + helpContents.getFieldId()
						+ "';");
				String helpText = helpContents.getExistingHelpText();
				helpText = replaceHTML(helpText);
				javaScript.append("var helpText='" + helpText + "';");
				javaScript
						.append("var fieldIdElement=document.getElementById(fieldId);");
				javaScript.append("if(null!=fieldIdElement){");
				javaScript.append("addHelpIcon(fieldIdElement,helpText);}");
			}
		}
		javaScript.append("}");
		String script = javaScript.toString();
		helpTable.put(pageId, script);
		return script;
	}

	/**
	 * Render the html format
	 * 
	 * @param helpText
	 * @return
	 */
	private String replaceHTML(String helpText) {
		helpText = helpText.replaceAll("&lt; ", "<");
		helpText = helpText.replaceAll(" &gt; ", ">");
		helpText = helpText.replaceAll(" &gt;", ">");
		helpText = helpText.replaceAll("&quot;", "\"");
		return helpText;
	}
	
	
}