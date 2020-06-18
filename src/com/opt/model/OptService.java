package com.opt.model;

import java.util.List;



public class OptService {
	
	private OptDAO_interface dao;

	public OptService() {
		dao = new OptJDBCDAO();
	}

	public OptVO addEmp(String docNo,java.sql.Date optDate,String optSession,
			Integer currentCount,Integer maximum) {

		OptVO optVO = new OptVO();

		optVO.setDocNo(docNo);
		optVO.setOptDate(optDate);
		optVO.setOptSession(optSession);
		optVO.setCurrentCount(currentCount);
		optVO.setMaximum(maximum);
		dao.insert(optVO);

		return optVO;
	}

	public OptVO updateEmp(String sessionNo,String docNo,java.sql.Date optDate
			,String optSession,Integer currentCount,Integer maximum) {

		OptVO optVO = new OptVO();
		
		
		optVO.setSessionNo(sessionNo);
		optVO.setDocNo(docNo);
		optVO.setOptDate(optDate);
		optVO.setOptSession(optSession);
		optVO.setCurrentCount(currentCount);
		optVO.setMaximum(maximum);
		dao.update(optVO);

		return optVO;
	}

	public void deleteEmp(String sessionNo) {
		dao.delete(sessionNo);
	}

	public OptVO getOneEmp(String sessionNo) {
		return dao.findByPrimaryKey(sessionNo);
	}

	public List<OptVO> getAll() {
		return dao.getAll();
	}

}
