/*
 * File: WebCrawler.java
 * Date: 16-Aug-2012
 *
 * This source code is part of Java Pathshala-Wisdom Being Shared.
 * This program is protected by copyright law but you are authorise to learn 
 * & gain ideas from it. Its unauthorised use is explicitly prohibited & any 
 * addition & removal of material. If want to suggest any changes,
 * you are welcome to provide your comments on GitHub Social Code Area.
 * Its unauthorised use gives Java Pathshala the right to obtain retention orders
 * and to prosecute the authors of any infraction.
 * 
 * Visit us at www.javapathshala.com
 */
package com.jp.web.crawler.cr4j;

//A minimal Web Crawler written in Java
//Usage: From command line 
//  java WebCrawler <URL> [N]
//where URL is the url to start the crawl, and N (optional)
//is the maximum number of pages to download.

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class WebCrawler {

	public static final int SEARCH_LIMIT = 20; // Absolute max pages

	public static final boolean DEBUG = false;

	public static final String DISALLOW = "Disallow:";

	public static final int MAXSIZE = 20000; // Max size of file

	// URLs to be searched
	Vector newURLs;

	// Known URLs
	Hashtable knownURLs;

	// max number of pages to download
	int maxPages;

	// initializes data structures. argv is the command line arguments.

	public void initialize(String[] argv) {
		URL url;
		knownURLs = new Hashtable();
		newURLs = new Vector();
		try {
			url = new URL(argv[0]);
		} catch (MalformedURLException e) {
			System.out.println("Invalid starting URL " + argv[0]);
			return;
		}
		knownURLs.put(url, new Integer(1));
		newURLs.addElement(url);
		System.out.println("Starting search: Initial URL " + url.toString());
		maxPages = SEARCH_LIMIT;
		if (argv.length > 1) {
			int iPages = Integer.parseInt(argv[1]);
			if (iPages < maxPages)
				maxPages = iPages;
		}
		System.out.println("Maximum number of pages:" + maxPages);

		/*
		 * Behind a firewall set your proxy and port here!
		 */
		Properties props = new Properties(System.getProperties());
		props.put("http.proxySet", "true");
		props.put("http.proxyHost", "webcache-cup");
		props.put("http.proxyPort", "8080");

		Properties newprops = new Properties(props);
		System.setProperties(newprops);
		/**/
	}

	// Check that the robot exclusion protocol does not disallow
	// downloading url.

	public boolean robotSafe(URL url) {
		String strHost = url.getHost();

		// form URL of the robots.txt file
		String strRobot = "http://" + strHost + "/robots.txt";
		URL urlRobot;
		try {
			urlRobot = new URL(strRobot);
		} catch (MalformedURLException e) {
			// something weird is happening, so don't trust it
			return false;
		}

		if (DEBUG)
			System.out.println("Checking robot protocol " + urlRobot.toString());
		String strCommands;
		try {
			InputStream urlRobotStream = urlRobot.openStream();

			// read in entire file
			byte b[] = new byte[1000];
			int numRead = urlRobotStream.read(b);
			strCommands = new String(b, 0, numRead);
			while (numRead != -1) {
				numRead = urlRobotStream.read(b);
				if (numRead != -1) {
					String newCommands = new String(b, 0, numRead);
					strCommands += newCommands;
				}
			}
			urlRobotStream.close();
		} catch (IOException e) {
			// if there is no robots.txt file, it is OK to search
			return true;
		}
		if (DEBUG)
			System.out.println(strCommands);

		// assume that this robots.txt refers to us and
		// search for "Disallow:" commands.
		String strURL = url.getFile();
		int index = 0;
		while ((index = strCommands.indexOf(DISALLOW, index)) != -1) {
			index += DISALLOW.length();
			String strPath = strCommands.substring(index);
			StringTokenizer st = new StringTokenizer(strPath);

			if (!st.hasMoreTokens())
				break;

			String strBadPath = st.nextToken();

			// if the URL starts with a disallowed path, it is not safe
			if (strURL.indexOf(strBadPath) == 0)
				return false;
		}

		return true;
	}

	// adds new URL to the queue. Accept only new URL's that end in
	// htm or html. oldURL is the context, newURLString is the link
	// (either an absolute or a relative URL).

	public void addnewurl(URL oldURL, String newUrlString)

	{
		URL url;
		if (DEBUG)
			System.out.println("URL String " + newUrlString);
		try {
			url = new URL(oldURL, newUrlString);
			if (!knownURLs.containsKey(url)) {
				String filename = url.getFile();
				int iSuffix = filename.lastIndexOf("htm");
				if ((iSuffix == filename.length() - 3) || (iSuffix == filename.length() - 4)) {
					knownURLs.put(url, new Integer(1));
					newURLs.addElement(url);
					System.out.println("Found new URL " + url.toString());
				}
			}
		} catch (MalformedURLException e) {
			return;
		}
	}

	// Download contents of URL

	public String getpage(URL url)

	{
		try {
			// try opening the URL
			URLConnection urlConnection = url.openConnection();
			System.out.println("Downloading " + url.toString());

			urlConnection.setAllowUserInteraction(false);

			InputStream urlStream = url.openStream();
			// search the input stream for links
			// first, read in the entire URL
			byte b[] = new byte[1000];
			int numRead = urlStream.read(b);
			String content = new String(b, 0, numRead);
			while ((numRead != -1) && (content.length() < MAXSIZE)) {
				numRead = urlStream.read(b);
				if (numRead != -1) {
					String newContent = new String(b, 0, numRead);
					content += newContent;
				}
			}
			return content;

		} catch (IOException e) {
			System.out.println("ERROR: couldn't open URL ");
			return "";
		}
	}

	// Go through page finding links to URLs. A link is signalled
	// by <a href=" ... It ends with a close angle bracket, preceded
	// by a close quote, possibly preceded by a hatch mark (marking a
	// fragment, an internal page marker)

	public void processpage(URL url, String page)

	{
		String lcPage = page.toLowerCase(); // Page in lower case
		int index = 0; // position in page
		int iEndAngle, ihref, iURL, iCloseQuote, iHatchMark, iEnd;
		while ((index = lcPage.indexOf("<a", index)) != -1) {
			iEndAngle = lcPage.indexOf(">", index);
			ihref = lcPage.indexOf("href", index);
			if (ihref != -1) {
				iURL = lcPage.indexOf("\"", ihref) + 1;
				if ((iURL != -1) && (iEndAngle != -1) && (iURL < iEndAngle)) {
					iCloseQuote = lcPage.indexOf("\"", iURL);
					iHatchMark = lcPage.indexOf("#", iURL);
					if ((iCloseQuote != -1) && (iCloseQuote < iEndAngle)) {
						iEnd = iCloseQuote;
						if ((iHatchMark != -1) && (iHatchMark < iCloseQuote))
							iEnd = iHatchMark;
						String newUrlString = page.substring(iURL, iEnd);
						addnewurl(url, newUrlString);
					}
				}
			}
			index = iEndAngle;
		}
	}

	// Top-level procedure. Keep popping a url off newURLs, download
	// it, and accumulate new URLs

	public void run(String[] argv)

	{
		initialize(argv);
		for (int i = 0; i < maxPages; i++) {
			URL url = (URL) newURLs.elementAt(0);
			newURLs.removeElementAt(0);
			if (DEBUG)
				System.out.println("Searching " + url.toString());
			if (robotSafe(url)) {
				String page = getpage(url);
				if (DEBUG)
					System.out.println(page);
				if (page.length() != 0)
					processpage(url, page);
				if (newURLs.isEmpty())
					break;
			}
		}
		System.out.println("Search complete.");
	}

	public static void main(String[] argv) {
		WebCrawler wc = new WebCrawler();
		wc.run(argv);
	}

}
