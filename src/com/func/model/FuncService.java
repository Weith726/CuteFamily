package com.func.model;

import java.util.List;


public class FuncService {
	
	private FuncDAO_interface dao;

	public FuncService() {
		dao = new FuncDAO();
	}

	public FuncVO addFunc(String bgFuncNo, String bgFuncName) {

		FuncVO funcVO = new FuncVO();

		funcVO.setBgFuncNo(bgFuncNo);
		funcVO.setBgFuncName(bgFuncName);
		
		dao.insert(funcVO);

		return funcVO;
	}

	public FuncVO updateFunc(String bgFuncNo, String bgFuncName) {

		FuncVO funcVO = new FuncVO();
		
		funcVO.setBgFuncNo(bgFuncNo);
		funcVO.setBgFuncName(bgFuncName);
		dao.update(funcVO);

		return funcVO;
	}

	public void deleteFunc(String bgFuncNo) {
		dao.delete(bgFuncNo);
	}

	public FuncVO getOneEmp(String bgFuncNo) {
		return dao.findByPrimaryKey(bgFuncNo);
	}
	

	public List<FuncVO> getAll() {
		return dao.getAll();
	}

}
