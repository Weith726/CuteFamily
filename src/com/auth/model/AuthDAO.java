package com.auth.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.emp.model.EmpVO;

public class AuthDAO implements AuthDAO_interface{
	
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, authVO.getEmpID());
			pstmt.setString(2, authVO.getBgFuncNo());

			pstmt.executeUpdate();

		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETEAUTH);

			pstmt.setInt(1, empID);
			pstmt.setString(2, bgFuncNo);

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
	public AuthVO findByPrimaryKey(Integer empID) {
		AuthVO authVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
			
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
				
				list.add(authVO); // Store the row in the list
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
	
	@Override
	public Set<AuthVO> getAuthsByEmp(Integer empID) {
		Set<AuthVO> set = new LinkedHashSet<AuthVO>();
		AuthVO authVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_EMPID);
			pstmt.setInt(1, empID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				authVO = new AuthVO();
				authVO.setEmpID(rs.getInt("empID"));
				authVO.setBgFuncNo(rs.getString("bgFuncNo"));
				set.add(authVO); // Store the row in the vector
			}
	
			
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
	


}
