package com.auth.model;

import java.util.List;
import java.util.Set;



public class AuthService {
	
	private AuthDAO_interface dao;

	public AuthService() {
		dao = new AuthDAO();
	}

	public AuthVO addAuth(Integer empID,String bgFuncNo) {

		AuthVO authVO = new AuthVO();

		authVO.setEmpID(empID);
		authVO.setBgFuncNo(bgFuncNo);
		dao.insert(authVO);

		return authVO;
	}

	

	public void deleteAuth(Integer empID, String bgFuncNo) {
		dao.deleteAuth(empID,bgFuncNo);
	}



	public Set<AuthVO> getAll(Integer empID) {
		return dao.getAuthsByEmp(empID);
	}

}
