package com.func.model;

import java.util.*;

public interface FuncDAO_interface {
	
	public void insert(FuncVO funcVO);
    public void update(FuncVO funcVO);
    public void delete(String bgFuncNo);
    public FuncVO findByPrimaryKey(String bgFuncNo);
    public List<FuncVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
	

}
