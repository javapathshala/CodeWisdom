package com.application.servlet.bib.helpText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data class contains the Screen Name & helpdetails 
 * @author dimit
 */
public class ManageHelp implements Serializable{
	
	private static final long serialVersionUID = -1381711411476940175L;
	private String screenName;
	private String pageId;
	private long lastCached;
	private List<ManageHelpDetails> helpDetails;	
	/**
	 * Default Constructor
	 */
	public ManageHelp(){
		
	}
	
	/**
	 * @param screenName
	 */
	public ManageHelp(String screenName,String pageId) {
		super();
		this.screenName = screenName;
		this.pageId=pageId;
		helpDetails=new ArrayList<ManageHelpDetails>();
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	
	/**
	 * screenName to set
	 * @param screenName 
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	
	public void add(ManageHelpDetails help){
		helpDetails.add(help);
	}

	/**
	 * @return the helpDetails
	 */
	public List<ManageHelpDetails> getHelpDetails() {
		return helpDetails;
	}

	/**
	 * @param helpDetails the helpDetails to set
	 */
	public void setHelpDetails(List<ManageHelpDetails> helpDetails) {
		this.helpDetails = helpDetails;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the lastCached
	 */
	public long getLastCached() {
		return lastCached;
	}

	/**
	 * @param lastCached the lastCached to set
	 */
	public void setLastCached(long lastCached) {
		this.lastCached = lastCached;
	}
}