package com.emp.model;

import java.util.*;

public interface EmpDAO_interface {
          public void insert(EmpVO empVO);
          public void update(EmpVO empVO);
          public void delete(String empID);
          public EmpVO findByPrimaryKey(String empID);
          public EmpVO findByAccAndPwd(String empAcc,String empPwd);
          public List<EmpVO> getAll();
          
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}

