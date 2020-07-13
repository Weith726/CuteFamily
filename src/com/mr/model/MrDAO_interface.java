package com.mr.model;

import java.util.*;

import com.appt.model.ApptVO;

public interface MrDAO_interface {
          public void insert(MrVO mrVO);
          public void update(MrVO mrVO);
          public void delete(String mrno);
          public MrVO findByPrimaryKey(String mrno);
          public List<MrVO> getAll();
          //複合查詢//
          public List<MrVO> getAll(Map<String, String[]> map);
          // 
}
