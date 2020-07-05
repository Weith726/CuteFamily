package com.appt.model;

import java.util.*;

public interface ApptDAO_interface {
	public void insert(ApptVO apptVO);
    public void update(ApptVO apptVO);
    public void delete(String apptno);
    public ApptVO findByPrimaryKey(String apptno);
    public List<ApptVO> getAll();
    public ApptVO getApptInfo(String optDate,String optSession);
}
