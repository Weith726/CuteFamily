package com.opt.model;

import java.sql.Date;

public class OptVO implements java.io.Serializable{
	
	private String sessionNo;
	private String docNo;
	private Date optDate;
	private String optSession;
	private Integer currentCount;
	private Integer maximum;
	//JSON專用
	private String title;
	private Date start;
	
	
	
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
	

//    public String toString() {
//        StringBuffer sb = new StringBuffer() ;
//        sb.append("") ;
//        sb.append("title:"+docNo+" ("+currentCount+")") ;
//        sb.append("start:"+optDate) ;
//        sb.append("") ;
//        return sb.toString() ;
//    }
	
	public void setTitle(String docNo,Integer currentCount,Integer maximum) {
		this.title = docNo+" 醫師"+"("+currentCount+"/"+maximum+")";
	}
	
	public void setStart(Date optDate) {
		this.start = optDate;
	}
	
	public String getTitle() {
		return title;
	}
	public Date getStart() {
		return start;
	}

}
