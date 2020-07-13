package com.mr.model;

public class MrVO implements java.io.Serializable{
	private String mrno;
	private String apptno;
	private String docno;
	private String petno;
	private String symptom;
	private String prescription;
	private Integer apptfee;
	private Integer medfee;
	private Integer operfee;
	
	public String getMrno() {
		return mrno;
	}
	public void setMrno(String mrno) {
		this.mrno = mrno;
	}
	public String getApptno() {
		return apptno;
	}
	public void setApptno(String apptno) {
		this.apptno = apptno;
	}
	public String getDocno() {
		return docno;
	}
	public void setDocno(String docno) {
		this.docno = docno;
	}
	public String getPetno() {
		return petno;
	}
	public void setPetno(String petno) {
		this.petno = petno;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public Integer getApptfee() {
		return apptfee;
	}
	public void setApptfee(Integer apptfee) {
		this.apptfee = apptfee;
	}
	public Integer getMedfee() {
		return medfee;
	}
	public void setMedfee(Integer medfee) {
		this.medfee = medfee;
	}
	public Integer getOperfee() {
		return operfee;
	}
	public void setOperfee(Integer operfee) {
		this.operfee = operfee;
	}
}
	