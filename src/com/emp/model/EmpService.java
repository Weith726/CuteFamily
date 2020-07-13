package com.emp.model;

import java.util.List;

public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(String empName, String empGender, java.sql.Date empBirth,
			String empJob, String empPhone, String empAddress, String empAcc, String empPwd,
			byte[] empPic, java.sql.Date hiredate, java.sql.Date quitdate, Integer empStatus) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpName(empName);
		empVO.setEmpGender(empGender);
		empVO.setEmpBirth(empBirth);
		empVO.setEmpJob(empJob);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpAddress(empAddress);
		empVO.setEmpAcc(empAcc);
		empVO.setEmpPwd(empPwd);
		empVO.setEmpPic(empPic);
		empVO.setHiredate(hiredate);
		empVO.setQuitdate(quitdate);
		empVO.setEmpStatus(empStatus);
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(String empID,String empName, String empGender, java.sql.Date empBirth,
			String empJob, String empPhone, String empAddress, String empAcc, String empPwd,
			byte[] empPic, java.sql.Date hiredate, java.sql.Date quitdate, Integer empStatus) {

		EmpVO empVO = new EmpVO();
		
		empVO.setEmpID(empID);
		empVO.setEmpName(empName);
		empVO.setEmpGender(empGender);
		empVO.setEmpBirth(empBirth);
		empVO.setEmpJob(empJob);
		empVO.setEmpPhone(empPhone);
		empVO.setEmpAddress(empAddress);
		empVO.setEmpAcc(empAcc);
		empVO.setEmpPwd(empPwd);
		empVO.setEmpPic(empPic);
		empVO.setHiredate(hiredate);
		empVO.setQuitdate(quitdate);
		empVO.setEmpStatus(empStatus);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(String empID) {
		dao.delete(empID);
	}

	public EmpVO getOneEmp(String empID) {
		return dao.findByPrimaryKey(empID);
	}
	
	public EmpVO getEmpInfo(String empAcc ,String empPwd) {
		return dao.findByAccAndPwd(empAcc, empPwd);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
}
