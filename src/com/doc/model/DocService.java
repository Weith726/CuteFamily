package com.doc.model;

import java.util.List;

import com.doc.model.DocDAO_interface;

public class DocService {

	private DocDAO_interface dao;

	public DocService() {
		dao = new DocDAO();
	}

	public DocVO addDoc(String divno, String docname, Integer roomno,
			Integer seniority, String intro, byte[] docpic, Integer docstatus) {

		DocVO docVO = new DocVO();

		docVO.setDivno(divno);
		docVO.setDocname(docname);
		docVO.setRoomno(roomno);
		docVO.setSeniority(seniority);
		docVO.setIntro(intro);
		docVO.setDocpic(docpic);
		docVO.setDocstatus(docstatus);
		dao.insert(docVO);

		return docVO;
	}

	public DocVO updateDoc(String docno, String divno, String docname, Integer roomno,
			Integer seniority, String intro, byte[] docpic, Integer docstatus) {

		DocVO docVO = new DocVO();

		docVO.setDocno(docno);
		docVO.setDivno(divno);
		docVO.setDocname(docname);
		docVO.setRoomno(roomno);
		docVO.setSeniority(seniority);
		docVO.setIntro(intro);
		docVO.setDocpic(docpic);
		docVO.setDocstatus(docstatus);
		dao.update(docVO);

		return docVO;
	}

	public void deleteDoc(String docno) {
		dao.delete(docno);
	}

	public DocVO getOneDoc(String docno) {
		return dao.findByPrimaryKey(docno);
	}

	public List<DocVO> getAll() {
		return dao.getAll();
	}
	
	public List<DocVO> getAllByDiv(String divno) {


		return dao.getAllByDiv(divno);
	}
	
}
