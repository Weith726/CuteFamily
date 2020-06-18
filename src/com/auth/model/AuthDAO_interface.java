package com.auth.model;

import java.util.*;


public interface AuthDAO_interface {

	public void insert(AuthVO empVO);

	public void update(AuthVO empVO);

	public void delete(Integer empID);
	
	public void deleteAuth(Integer empID,String bgFuncNo);

	public AuthVO findByPrimaryKey(Integer empID);

	public List<AuthVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	 //查詢某員工的權限(一對多)(回傳 Set)
	public Set<AuthVO> getAuthsByEmp(Integer empID);

}
