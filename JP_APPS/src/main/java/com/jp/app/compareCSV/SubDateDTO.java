/**
 * 
 */
package com.jp.app.compareCSV;

/**
 * @author dimit.chadha
 *
 */
public class SubDateDTO {
	
	private String upPubCode;
	private String SubCreationDate;
	private String SubFirstIssueDate;
	private String SubExpireDate;
	private String SubCancelDate;
	private String subTerm;
	private String subComment;
	
	
	public String getSubComment() {
		return subComment;
	}

	public void setSubComment(String subComment) {
		this.subComment = subComment;
	}

	public String getSubTerm() {
		return subTerm;
	}

	public void setSubTerm(String subTerm) {
		this.subTerm = subTerm;
	}
	
	public String getUpPubCode() {
		return upPubCode;
	}
	public void setUpPubCode(String upPubCode) {
		this.upPubCode = upPubCode;
	}
	public String getSubCreationDate() {
		return SubCreationDate;
	}
	public void setSubCreationDate(String subCreationDate) {
		SubCreationDate = subCreationDate;
	}
	public String getSubFirstIssueDate() {
		return SubFirstIssueDate;
	}
	public void setSubFirstIssueDate(String subFirstIssueDate) {
		SubFirstIssueDate = subFirstIssueDate;
	}
	public String getSubExpireDate() {
		return SubExpireDate;
	}
	public void setSubExpireDate(String subExpireDate) {
		SubExpireDate = subExpireDate;
	}
	public String getSubCancelDate() {
		return SubCancelDate;
	}
	public void setSubCancelDate(String subCancelDate) {
		SubCancelDate = subCancelDate;
	}
	
	
}
