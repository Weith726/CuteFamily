package com.mem.model;

import oracle.sql.BLOB;

public class MemberVO  implements java.io.Serializable{
	private String memNo;
	private String memName;
	private String memAccount;
	private String memPassword;
	private String memCreditCardId;
	private String memPhone;
	private String memEmail;
	private String memAddress;
	private Integer memStatus;
	private byte[] mempic;
	
	
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemCreditCardId() {
		return memCreditCardId;
	}
	public void setMemCreditCardId(String memCreditCardId) {
		this.memCreditCardId = memCreditCardId;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public byte[] getMempic() {
		return mempic;
	}
	public void setMempic(byte[] mempic) {
		this.mempic = mempic;
	}
	
}

