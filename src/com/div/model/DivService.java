package com.div.model;

import java.util.List;
import java.util.Set;

import com.doc.model.DocVO;

public class DivService {

	private DivDAO_interface dao;

	public DivService() {
		dao = new DivDAO();
	}

	public List<DivVO> getAll() {
		return dao.getAll();
	}

	public DivVO getOneDiv(String divno) {
		return dao.findByPrimaryKey(divno);
	}

	public Set<DocVO> getDocsByDivno(String divno){
		return dao.getDocsByDivno(divno);
	}
	
	public void deleteDiv(String divno) {
		dao.delete(divno);
	}
}
