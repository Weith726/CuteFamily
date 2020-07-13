package com.emp.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class EmpDAO implements EmpDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (empID,empName,empGender,empBirth,empJob,empPhone,empAddress,"
			+ "empAcc,empPwd,empPic,hiredate,quitdate,empStatus) VALUES (emp_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT empID,empName,empGender,to_char(empBirth,'yyyy-mm-dd') empBirth,empJob,empPhone,empAddress,empAcc,"
			+ "empPwd,empPic,to_char(hiredate,'yyyy-mm-dd') hiredate,to_char(quitdate,'yyyy-mm-dd') quitdate,empStatus FROM EMPLOYEE order by empID";

	private static final String GET_ONE_STMT = "SELECT empID,empName,empGender,to_char(empBirth,'yyyy-mm-dd') empBirth,empJob,empPhone,empAddress,empAcc,"
			+ "empPwd,empPic,to_char(hiredate,'yyyy-mm-dd') hiredate,to_char(quitdate,'yyyy-mm-dd') quitdate,empStatus FROM EMPLOYEE where empID = ?";

	private static final String DELETE = "DELETE FROM EMPLOYEE where empID = ?";

	private static final String UPDATE = "UPDATE EMPLOYEE set empName=?,empGender=?,empBirth=?,empJob=?,empPhone=?,empAddress=?,empAcc=?,"
			+ "empPwd=?,empPic=?,hiredate=?,quitdate=?,empStatus=? where empID = ?";

	private static final String GET_EMP_INFO = "SELECT empID,empName,empPic FROM EMPLOYEE where empacc = ? AND empPwd = ?";

	@Override
	public void insert(EmpVO empVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, empID);

			pstmt.executeUpdate();
		
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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

	

}
