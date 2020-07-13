package com.opt.model;

import java.sql.Date;
import java.util.List;



public class OptService {
	
	private OptDAO_interface dao;

	public OptService() {
		dao = new OptDAO();
	}

	public OptVO addOptSession(String docNo,java.sql.Date optDate,String optSession,
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

	public OptVO updateOptSession(String sessionNo,String docNo,java.sql.Date optDate
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
	
	public OptVO updateCurrentCount(Integer currentCount,String sessionNo) {

		OptVO optVO = new OptVO();
		
		optVO.setCurrentCount(currentCount);
		optVO.setSessionNo(sessionNo);

		dao.updateCurrentCount(optVO);

		return optVO;
	}

	public void deleteOptSession(String sessionNo) {
		dao.delete(sessionNo);
	}

	public OptVO getOneOptSession(String sessionNo) {
		return dao.findByPrimaryKey(sessionNo);
	}
	
	public OptVO findSession(String docNo,Date optDate,String optSession) {
		return dao.findRepeat(docNo,optDate,optSession);
	}

	public List<OptVO> getCalInfo() {
		return dao.getCalInfo();
	}
	
	public List<OptVO> getCalInfoByDoc(String docno) {
		return dao.getCalInfoByDoc(docno);
	}
	
	public List<OptVO> getAll() {
		return dao.getAll();
	}

}
