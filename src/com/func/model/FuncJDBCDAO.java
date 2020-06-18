package com.func.model;

import java.util.*;
import java.sql.*;



public class FuncJDBCDAO implements FuncDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO BACKGROUNDFUNC (bgFuncNo,bgFuncName) VALUES (?, ?)";

	private static final String GET_ALL_STMT = "SELECT bgFuncNo,bgFuncName FROM BACKGROUNDFUNC order by bgFuncNo";


	private static final String DELETE = "DELETE FROM BACKGROUNDFUNC where bgFuncNo = ?";

	private static final String UPDATE = "UPDATE BACKGROUNDFUNC set bgFuncName=? where bgFuncNo = ?";


	@Override
	public void insert(FuncVO funcVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, funcVO.getBgFuncNo());
			pstmt.setString(2, funcVO.getBgFuncName());

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
	public void update(FuncVO funcVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, funcVO.getBgFuncName());
			pstmt.setString(2, funcVO.getBgFuncNo());

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
	public void delete(String bgFuncNo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, bgFuncNo);

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
	//功能清單不做單一查詢
	@Override
	public FuncVO findByPrimaryKey(String bgFuncNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FuncVO> getAll() {
		// TODO Auto-generated method stub
		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;

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
				funcVO = new FuncVO();
				funcVO.setBgFuncNo(rs.getString("bgFuncNo"));
				funcVO.setBgFuncName(rs.getString("bgFuncName"));
				
				list.add(funcVO); // Store the row in the list
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

		FuncJDBCDAO dao = new FuncJDBCDAO();

		// 新增
//		FuncVO funcVO = new FuncVO();
//		funcVO.setBgFuncNo("E1");
//		funcVO.setBgFuncName("員工管理");
//		dao.insert(funcVO);

		// 修改
//		FuncVO funcVO = new FuncVO();
//		funcVO.setBgFuncNo("E1");
//		funcVO.setBgFuncName("員工開除");
//		dao.update(funcVO);

		// 刪除
//		dao.delete("E1");



		// 查詢
		List<FuncVO> list = dao.getAll();
		for (FuncVO aFunc : list) {
			System.out.print(aFunc.getBgFuncNo() + ",");
			System.out.print(aFunc.getBgFuncName());
			System.out.println();
		}
	}

}
