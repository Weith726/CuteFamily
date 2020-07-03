package com.appt.model;

import java.sql.Blob;

public class ApptVO implements java.io.Serializable{
	private String apptno;
	private String memno;
	private String sessionno;
	private Integer seqno;
	private String symdesc;
	private byte[] symphoto;
	private Integer optstate;
	
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
	
	
}
