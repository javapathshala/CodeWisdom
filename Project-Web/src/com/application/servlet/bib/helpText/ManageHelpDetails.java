package com.application.servlet.bib.helpText;


import java.sql.Timestamp;

/**
 * Data class containing the helpdetails 
 * @author dimit
 */
public class ManageHelpDetails extends ManageHelp{
	
	private static final long serialVersionUID = -1907130651891714507L;
	private String fieldName;
	private String newHelpText;
	private String existingHelpText;
	private String status;
	private String SubmittedBy;
	private Timestamp lastChanged;
	private long inputterId;
	private String rowID;
	private String fieldId;
	
	/**
	 * @return the existingHelpText
	 */
	public String getExistingHelpText() {
		return existingHelpText;
	}
	/**
	 * @param existingHelpText the existingHelpText to set
	 */
	public void setExistingHelpText(String existingHelpText) {
		this.existingHelpText = existingHelpText;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	/**
	 * @return the newHelpText
	 */
	public String getNewHelpText() {
		return newHelpText;
	}
	/**
	 * @param newHelpText the newHelpText to set
	 */
	public void setNewHelpText(String newHelpText) {
		this.newHelpText = newHelpText;
	}
	/**
	 * @return the lastChanged
	 */
	public Timestamp getLastChanged() {
		return lastChanged;
	}
	/**
	 * @param lastChanged the lastChanged to set
	 */
	public void setLastChanged(Timestamp lastChanged) {
		this.lastChanged = lastChanged;
	}
	/**
	 * @return the submittedBy
	 */
	public String getSubmittedBy() {
		return SubmittedBy;
	}
	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(String submittedBy) {
		SubmittedBy = submittedBy;
	}
	
	/**
	 * @return the inputterId
	 */
	public long getInputterId() {
		return inputterId;
	}
	/**
	 * @param inputterId the inputterId to set
	 */
	public void setInputterId(long inputterId) {
		this.inputterId = inputterId;
	}
	/**
	 * @return the rowID
	 */
	public String getRowID() {
		return rowID;
	}
	/**
	 * @param rowID the rowID to set
	 */
	public void setRowID(String rowID) {
		this.rowID = rowID;
	}
	/**
	 * @return the fieldId
	 */
	public String getFieldId() {
		return fieldId;
	}
	/**
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
}