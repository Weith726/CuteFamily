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
	private String id;

	
	
	
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
	
	public void setTitle(String docNo,Integer currentCount,Integer maximum,String optSession) {
		String optS =new String();
		if("10:00~12:00".equals(optSession)) {
			optS="上午";
		}
		else if("14:00~17:00".equals(optSession)) {
			optS="下午";
		}
		else {
			optS="晚上";
		}
		
		if(maximum==currentCount) {
			this.title = docNo+" 醫師 "+optS+" (已額滿) ";
		}
		else {
			this.title = docNo+" 醫師 "+optS+" ("+currentCount+"/"+maximum+") ";
		}
			
		
	}
	public String getTitle() {
		return title;
	}
	
	public void setStart(Date start) {
		this.start = start;
	}
	
	public Date getStart() {
		return start;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


}
