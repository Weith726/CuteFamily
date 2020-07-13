package com.appt.model;

import java.util.*;


public interface ApptDAO_interface {
	public void insert(ApptVO apptVO);
    public void update(ApptVO apptVO);
    public void cancel(ApptVO apptVO);
    public void updateState(ApptVO apptVO);
    public void delete(String apptno);
    public ApptVO findByPrimaryKey(String apptno);
    public List<ApptVO> getAll();
    public List<ApptVO> getAll(Map<String, String[]> map); 
  //看診進度//
    public List<ApptVO> getQueue(Map<String, String[]> map);
}
