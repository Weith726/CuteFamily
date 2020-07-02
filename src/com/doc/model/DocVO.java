package com.doc.model;

public class DocVO implements java.io.Serializable{
	private String docno;
	private String divno;
	private String docname;
	private Integer roomno;
	private Integer seniority;
	private String intro;
	private byte[] docpic;
	private Integer docstatus;
	
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public String getDivno() {
		return divno;
	}
	public void setDivno(String divno) {
		this.divno = divno;
	}
	public String getDocname() {
		return docname;
	}
	public void setDocname(String docname) {
		this.docname = docname;
	}
	public Integer getRoomno() {
		return roomno;
	}
	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}
	public Integer getSeniority() {
		return seniority;
	}
	public void setSeniority(Integer seniority) {
		this.seniority = seniority;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public byte[] getDocpic() {
		return docpic;
	}
	public void setDocpic(byte[] docpic) {
		this.docpic = docpic;
	}
	public Integer getDocstatus() {
		return docstatus;
	}
	public void setDocstatus(Integer docstatus) {
		this.docstatus = docstatus;
	}

}
