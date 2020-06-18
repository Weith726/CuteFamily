package com.opt.model;

import java.sql.Date;

public class OptVO implements java.io.Serializable{
	
	private String sessionNo;
	private String docNo;
	private Date optDate;
	private String optSession;
	private Integer currentCount;
	private Integer maximum;
	
	public String getSessionNo() {
		return sessionNo;
	}
	public void setSessionNo(String sessionNo) {
		this.sessionNo = sessionNo;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public Date getOptDate() {
		return optDate;
	}
	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}
	public String getOptSession() {
		return optSession;
	}
	public void setOptSession(String optSession) {
		this.optSession = optSession;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	
	

}
