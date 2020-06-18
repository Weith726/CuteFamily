package com.auth.model;

import java.sql.*;
import java.util.*;

import com.emp.model.EmpVO;

public class AuthJDBCDAO implements AuthDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = "INSERT INTO AUTHORIZATION (empID,bgFuncNo) VALUES (?,?)";

	private static final String GET_ALL_STMT = "SELECT empID,bgFuncNo FROM AUTHORIZATION order by empID";

	private static final String GET_ONE_STMT = "SELECT empID,bgFuncNo FROM AUTHORIZATION where empID = ?";
	
	private static final String GET_ALL_BY_EMPID = "SELECT empID,bgFuncNo FROM AUTHORIZATION where empID = ?";

	private static final String DELETE = "DELETE FROM AUTHORIZATION where empID = ?";

	private static final String DELETEAUTH = "DELETE FROM AUTHORIZATION where empID = ? AND bgFuncNo= ?";

	@Override
	public void insert(AuthVO authVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, authVO.getEmpID());
			pstmt.setString(2, authVO.getBgFuncNo());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	//單筆權限不會做更新，只有新增刪除，不覆寫
	@Override
	public void update(AuthVO authVO) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void delete(Integer empID) {
		
		
	}
	
	@Override
	public void deleteAuth(Integer empID, String bgFuncNo) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETEAUTH);

			pstmt.setInt(1, empID);
			pstmt.setString(2, bgFuncNo);

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
	public AuthVO findByPrimaryKey(Integer empID) {
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
			
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
		return authVO;
	}

	@Override
	public List<AuthVO> getAll() {
		List<AuthVO> list = new ArrayList<AuthVO>();
		AuthVO authVO = null;

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
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
				
				list.add(authVO); // Store the row in the list
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
	
	@Override
	public Set<AuthVO> getAuthsByEmp(Integer empID) {
		Set<AuthVO> set = new LinkedHashSet<AuthVO>();
		AuthVO authVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BY_EMPID);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
				set.add(authVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
	
	
	
	public static void main(String[] args) {

		AuthJDBCDAO dao = new AuthJDBCDAO();

		// 新增
		AuthVO authVO = new AuthVO();
		authVO.setEmpID(1007);
		authVO.setBgFuncNo("71");
		dao.insert(authVO);

//		// 修改(沒用)
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
//		dao.deleteAuth(1007,"71");
//
//		// 單筆查詢(沒用)
//		AuthVO authVO = dao.findByPrimaryKey(1001);
//		System.out.print(authVO.getEmpID() + ",");
//		System.out.print(authVO.getBgFuncNo());
//		System.out.println();
//		System.out.println("---------------------");

		// 查詢(沒用)
//		List<AuthVO> list = dao.getAll();
//		for (AuthVO aAuth : list) {
//			System.out.print(aAuth.getEmpID() + ",");
//			System.out.print(aAuth.getBgFuncNo());
//			System.out.println();
//		}
		// 查詢某員工的權限
		Set<AuthVO> set = dao.getAuthsByEmp(1007);
		for (AuthVO aEmp : set) {
			System.out.print(aEmp.getEmpID() + ",");
			System.out.print(aEmp.getBgFuncNo());
			System.out.println();
		}
	}




	

}
