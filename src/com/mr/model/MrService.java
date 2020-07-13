package com.mr.model;

import java.util.List;
import java.util.Map;

import com.appt.model.ApptVO;
import com.mr.model.MrDAO_interface;

public class MrService {

	private MrDAO_interface dao;

	public MrService() {
		dao = new MrDAO();
	}

	public MrVO addMr(String apptno, String docno, String petno, String symptom,
			String prescription, Integer apptfee, Integer medfee, Integer operfee) {

		MrVO mrVO = new MrVO();

		mrVO.setApptno(apptno);
		mrVO.setDocno(docno);
		mrVO.setPetno(petno);
		mrVO.setSymptom(symptom);
		mrVO.setPrescription(prescription);
		mrVO.setApptfee(apptfee);
		mrVO.setMedfee(medfee);
		mrVO.setOperfee(operfee);
		dao.insert(mrVO);

		return mrVO;
	}

	public MrVO updateMr(String mrno, String apptno, String docno, String petno, String symptom,
			String prescription, Integer apptfee, Integer medfee, Integer operfee) {

		MrVO mrVO = new MrVO();

		mrVO.setMrno(mrno);
		mrVO.setApptno(apptno);
		mrVO.setDocno(docno);
		mrVO.setPetno(petno);
		mrVO.setSymptom(symptom);
		mrVO.setPrescription(prescription);
		mrVO.setApptfee(apptfee);
		mrVO.setMedfee(medfee);
		mrVO.setOperfee(operfee);
		dao.update(mrVO);

		return mrVO;
	}

	public void deleteMr(String mrVO) {
		dao.delete(mrVO);
	}

	public MrVO getOneMr(String mrVO) {
		return dao.findByPrimaryKey(mrVO);
	}

	public List<MrVO> getAll() {
		return dao.getAll();
	}
	
	//複合查詢
		public List<MrVO> getAll(Map<String, String[]> map) {
			return dao.getAll(map);
		}
}
