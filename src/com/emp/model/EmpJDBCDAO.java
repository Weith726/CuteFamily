package com.emp.model;

import java.util.*;
import java.sql.*;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (empID,empName,empGender,empBirth,empJob,empPhone,empAddress,"
			+ "empAcc,empPwd,empPic,hiredate,quitdate,empStatus) VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT empID,empName,empGender,to_char(empBirth,'yyyy-mm-dd') empBirth,empJob,empPhone,empAddress,empAcc,"
			+ "empPwd,empPic,to_char(hiredate,'yyyy-mm-dd') hiredate,to_char(quitdate,'yyyy-mm-dd') quitdate,empStatus FROM EMPLOYEE order by empID";

	private static final String GET_ONE_STMT = "SELECT empID,empName,empGender,to_char(empBirth,'yyyy-mm-dd') empBirth,empJob,empPhone,empAddress,empAcc,"
			+ "empPwd,empPic,to_char(hiredate,'yyyy-mm-dd') hiredate,to_char(quitdate,'yyyy-mm-dd') quitdate,empStatus FROM EMPLOYEE where empID = ?";

	private static final String DELETE = "DELETE FROM EMPLOYEE where empID = ?";

	private static final String UPDATE = "UPDATE EMPLOYEE set empName=?,empGender=?,empBirth=?,empJob=?,empPhone=?,empAddress=?,empAcc=?,"
			+ "empPwd=?,empPic=?,hiredate=?,quitdate=?,empStatus=? where empID = ?";

	private static final String GET_EMP_INFO = 
			"SELECT empID,empName,empPic FROM EMPLOYEE where empacc = ? AND empPwd = ?";
	@Override
	public void insert(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpGender());
			pstmt.setDate(3, empVO.getEmpBirth());
			pstmt.setString(4, empVO.getEmpJob());
			pstmt.setString(5, empVO.getEmpPhone());
			pstmt.setString(6, empVO.getEmpAddress());
			pstmt.setString(7, empVO.getEmpAcc());
			pstmt.setString(8, empVO.getEmpPwd());
			pstmt.setBytes(9, empVO.getEmpPic());
			pstmt.setDate(10, empVO.getHiredate());
			pstmt.setDate(11, empVO.getQuitdate());
			pstmt.setInt(12, empVO.getEmpStatus());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpGender());
			pstmt.setDate(3, empVO.getEmpBirth());
			pstmt.setString(4, empVO.getEmpJob());
			pstmt.setString(5, empVO.getEmpPhone());
			pstmt.setString(6, empVO.getEmpAddress());
			pstmt.setString(7, empVO.getEmpAcc());
			pstmt.setString(8, empVO.getEmpPwd());
			pstmt.setBytes(9, empVO.getEmpPic());
			pstmt.setDate(10, empVO.getHiredate());
			pstmt.setDate(11, empVO.getQuitdate());
			pstmt.setInt(12, empVO.getEmpStatus());
			pstmt.setString(13, empVO.getEmpID());

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String empID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empID);

			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public EmpVO findByPrimaryKey(String empID) {
		// TODO Auto-generated method stub
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpID(rs.getString("empID"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpGender(rs.getString("empGender"));
				empVO.setEmpBirth(rs.getDate("empBirth"));
				empVO.setEmpJob(rs.getString("empJob"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpAddress(rs.getString("empAddress"));
				empVO.setEmpAcc(rs.getString("empAcc"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPic(rs.getBytes("empPic"));
				empVO.setHiredate(rs.getDate("hiredate"));
				empVO.setQuitdate(rs.getDate("quitdate"));
				empVO.setEmpStatus(rs.getInt("empStatus"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}
	
	
	@Override
	public EmpVO findByAccAndPwd(String empAcc, String empPwd) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_EMP_INFO);

			pstmt.setString(1, empAcc);
			pstmt.setString(2, empPwd);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpID(rs.getString("empID"));
				empVO.setEmpName(rs.getString("empName"));		
				empVO.setEmpPic(rs.getBytes("empPic"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return empVO;
	}
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpID(rs.getString("empID"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpGender(rs.getString("empGender"));
				empVO.setEmpBirth(rs.getDate("empBirth"));
				empVO.setEmpJob(rs.getString("empJob"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpAddress(rs.getString("empAddress"));
				empVO.setEmpAcc(rs.getString("empAcc"));
				empVO.setEmpPwd(rs.getString("empPwd"));
				empVO.setEmpPic(rs.getBytes("empPic"));
				empVO.setHiredate(rs.getDate("hiredate"));
				empVO.setQuitdate(rs.getDate("quitdate"));
				empVO.setEmpStatus(rs.getInt("empStatus"));
				list.add(empVO); // Store the row in the list
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
//		EmpVO empVO = new EmpVO();
//		empVO.setEmpName("吳永志");
//		empVO.setEmpGender("男");
//		empVO.setEmpBirth(java.sql.Date.valueOf("1992-01-01"));
//		empVO.setEmpJob("獸醫");
//		empVO.setEmpPhone("0979282001");
//		empVO.setEmpAddress("台北市XXX");
//		empVO.setEmpAcc("wu123456");
//		empVO.setEmpPwd("wu123456");
//		empVO.setEmpPic();
//		empVO.setHiredate(java.sql.Date.valueOf("2020-01-01"));
//		empVO.setEmpStatus(1);
//		dao.insert(empVO);

//		// 修改
//		EmpVO empVO = new EmpVO();
//		empVO.setEmpID(1021);
//		empVO.setEmpName("吳永志");
//		empVO.setEmpGender("男");
//		empVO.setEmpBirth(java.sql.Date.valueOf("1992-01-01"));
//		empVO.setEmpJob("獸醫");
//		empVO.setEmpPhone("0979282001");
//		empVO.setEmpAddress("台北市XXX");
//		empVO.setEmpAcc("wu123456");
//		empVO.setEmpPwd("wu123456");
//		empVO.setEmpPic();
//		empVO.setHiredate(java.sql.Date.valueOf("2020-01-01"));
//		empVO.setQuitdate(java.sql.Date.valueOf("2020-01-02"));
//		empVO.setEmpStatus(3);
//		dao.update(empVO);
//
//		// 刪除
//		dao.delete(1020);
//
//		// 查詢
//		EmpVO empVO3 = dao.findByPrimaryKey("1003");
//		System.out.print(empVO3.getEmpID() + ",");
//		System.out.print(empVO3.getEmpName() + ",");
//		System.out.print(empVO3.getEmpPic() + ",");

		
		// 查詢依照帳密
//		EmpVO empVO = dao.findByAccAndPwd("Jared01@gmail.com", "123456");
//		System.out.print(empVO.getEmpID() + ",");
//		System.out.print(empVO.getEmpName() + ",");
//		System.out.print(empVO.getEmpPic() + ",");
//
//		System.out.println("---------------------");

		// 查詢
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpID() + ",");
//			System.out.print(aEmp.getEmpName() + ",");
//			System.out.print(aEmp.getEmpGender() + ",");
//			System.out.print(aEmp.getEmpBirth() + ",");
//			System.out.print(aEmp.getEmpJob() + ",");
//			System.out.print(aEmp.getEmpPhone() + ",");
//			System.out.print(aEmp.getEmpAddress()+",");
//			System.out.print(aEmp.getEmpAcc() + ",");
//			System.out.print(aEmp.getEmpPwd() + ",");
//			System.out.print(aEmp.getEmpPic() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getQuitdate() + ",");
//			System.out.print(aEmp.getEmpStatus());
//			System.out.println();
//		}
	}


}
