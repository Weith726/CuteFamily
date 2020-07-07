package com.appt.model;

import java.util.List;
import java.util.Map;

import com.appt.model.ApptDAO_interface;
import com.emp.model.EmpVO;

public class ApptService {

	private ApptDAO_interface dao;

	public ApptService() {
		dao = new ApptJDBCDAO();
	}

	public ApptVO addAppt(String memno, String sessionno, Integer seqno,
			 String symdesc, byte[] symphoto, Integer optstate) {

		ApptVO apptVO = new ApptVO();

		apptVO.setMemno(memno);
		apptVO.setSessionno(sessionno);
		apptVO.setSeqno(seqno);
		apptVO.setSymdesc(symdesc);
		apptVO.setSymphoto(symphoto);
		apptVO.setOptstate(optstate);
		dao.insert(apptVO);

		return apptVO;
	}

	public ApptVO updateAppt(String apptno, String memno, String sessionno, Integer seqno,
			 String symdesc, byte[] symphoto, Integer optstate) {

		ApptVO apptVO = new ApptVO();

		apptVO.setApptno(apptno);
		apptVO.setMemno(memno);
		apptVO.setSessionno(sessionno);
		apptVO.setSeqno(seqno);
		apptVO.setSymdesc(symdesc);
		apptVO.setSymphoto(symphoto);
		apptVO.setOptstate(optstate);
		dao.update(apptVO);

		return apptVO;
	}
	public ApptVO updateState(String apptno,Integer optstate) {

		ApptVO apptVO = new ApptVO();

		apptVO.setApptno(apptno);
		apptVO.setOptstate(optstate);
		dao.updateState(apptVO);

		return apptVO;
	}

	public void deleteAppt(String apptno) {
		dao.delete(apptno);
	}

	public ApptVO getOneAppt(String apptno) {
		return dao.findByPrimaryKey(apptno);
	}

	public List<ApptVO> getAll() {
		return dao.getAll();
	}
	
	public List<ApptVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	
	public ApptVO getApptInfo(String optDate,String optSession) {
		return dao.getApptInfo(optDate,optSession);
	}
}
