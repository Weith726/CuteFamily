package com.appt.model;

import java.sql.Date;

public class ApptVO implements java.io.Serializable{
	
	private String apptno;
	private String memno;
	private String sessionno;
	private String petNo;
	private Integer seqno;
	private String symdesc;
	private byte[] symphoto;
	private Integer optstate;
	private String docname;
	private String memName;
	private String optSession;
	private Date optDate;
	
	
	public String getApptno() {
		return apptno;
	}
	public void setApptno(String apptno) {
		this.apptno = apptno;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public String getPetNo() {
		return petNo;
	}
	public void setPetNo(String petNo) {
		this.petNo = petNo;
	}
	public String getSessionno() {
		return sessionno;
	}
	public void setSessionno(String sessionno) {
		this.sessionno = sessionno;
	}
	public Integer getSeqno() {
		return seqno;
	}
	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}
	public String getSymdesc() {
		return symdesc;
	}
	public void setSymdesc(String symdesc) {
		this.symdesc = symdesc;
	}
	public byte[] getSymphoto() {
		return symphoto;
	}
	public void setSymphoto(byte[] symphoto) {
		this.symphoto = symphoto;
	}
	public Integer getOptstate() {
		return optstate;
	}
	public void setOptstate(Integer optstate) {
		this.optstate = optstate;
	}
	//JOIN用
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getOptSession() {
		return optSession;
	}
	public void setOptSession(String optSession) {
		this.optSession = optSession;
	}
	public Date getOptDate() {
		return optDate;
	}
	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	
	
	
}
