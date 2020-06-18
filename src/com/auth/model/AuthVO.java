package com.auth.model;

public class AuthVO implements java.io.Serializable{
	
	private Integer empID;
	private String bgFuncNo;
	
	public Integer getEmpID() {
		return empID;
	}
	public void setEmpID(Integer empID) {
		this.empID = empID;
	}
	public String getBgFuncNo() {
		return bgFuncNo;
	}
	public void setBgFuncNo(String bgFuncNo) {
		this.bgFuncNo = bgFuncNo;
	}

}
