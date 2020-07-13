package com.div.model;

import java.util.*;
import java.sql.*;

import com.doc.model.DocVO;

public class DivJDBCDAO implements DivDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "EA101";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO division (divno,divname) VALUES (division_seq.NEXTVAL, ?)";
	private static final String GET_ALL_STMT = "SELECT divno,divname FROM division";
	private static final String GET_ONE_STMT = "SELECT divno,divname FROM division where divno = ?";
	private static final String GET_Docs_ByDivno_STMT = "SELECT docno, divno, docname, roomno, seniority, intro, docpic, docstatus FROM doctor where divno = ? order by docno";
	
	private static final String DELETE_DOCs = "DELETE FROM doctor where divno = ?";
	private static final String DELETE_DIV = "DELETE FROM division where divno = ?";	
	
	private static final String UPDATE = "UPDATE division set divname=? where divno = ?";

	@Override
	public void insert(DivVO divVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, divVO.getDivname());

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

	@Override
	public void update(DivVO divVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, divVO.getDivno());
			pstmt.setString(2, divVO.getDivname());

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

	@Override
	public void delete(String divno) {
		int updateCount_DOCs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除員工
			pstmt = con.prepareStatement(DELETE_DOCs);
			pstmt.setString(1, divno);
			updateCount_DOCs = pstmt.executeUpdate();
			// 再刪除部門
			pstmt = con.prepareStatement(DELETE_DIV);
			pstmt.setString(1, divno);
			pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除科別編號" + divno + "時,共有醫師" + updateCount_DOCs
					+ "位同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public DivVO findByPrimaryKey(String divno) {

		DivVO divVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, divno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				divVO = new DivVO();
				divVO.setDivno(rs.getString("divno"));
				divVO.setDivname(rs.getString("divname"));
			}

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
		return divVO;
	}

	@Override
	public List<DivVO> getAll() {
		List<DivVO> list = new ArrayList<DivVO>();
		DivVO divVO = null;


		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				divVO = new DivVO();
				divVO.setDivno(rs.getString("divno"));
				divVO.setDivname(rs.getString("divname"));
				list.add(divVO); // Store the row in the list
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
		return list;
	}

	@Override
	public Set<DocVO> getDocsByDivno(String divno) {
		Set<DocVO> set = new LinkedHashSet<DocVO>();
		DocVO docVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Docs_ByDivno_STMT);
			pstmt.setString(1, divno);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				docVO = new DocVO();
				docVO.setDocno(rs.getString("docno"));
				docVO.setDivno(rs.getString("divno"));
				docVO.setDocname(rs.getString("docname"));
				docVO.setRoomno(rs.getInt("roomno"));
				docVO.setSeniority(rs.getInt("seniority"));
				docVO.setIntro(rs.getString("intro"));
				docVO.setDocpic(rs.getBytes("docpic"));
				docVO.setDocstatus(rs.getInt("docstatus"));
				set.add(docVO);// Store the row in the vector
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

		DivJDBCDAO dao = new DivJDBCDAO();

		// 新增
//		DeptVO deptVO1 = new DeptVO();
//		deptVO1.setDname("製造部");
//		deptVO1.setLoc("中國江西");
//		dao.insert(deptVO1);

		// 修改
//		DeptVO deptVO2 = new DeptVO();
//		deptVO2.setDeptno(10);
//		deptVO2.setDname("財務部2");
//		deptVO2.setLoc("臺灣台北2");
//		dao.update(deptVO2);

		// 刪除
//		dao.delete(30);

		// 查詢
//		DeptVO deptVO3 = dao.findByPrimaryKey(10);
//		System.out.print(deptVO3.getDeptno() + ",");
//		System.out.print(deptVO3.getDname() + ",");
//		System.out.println(deptVO3.getLoc());
//		System.out.println("---------------------");

		// 查詢部門
		List<DivVO> list = dao.getAll();
		for (DivVO aDiv : list) {
			System.out.print(aDiv.getDivno() + ",");
			System.out.print(aDiv.getDivname() + ",");
			System.out.println();
		}
		
		// 查詢某部門的員工
		Set<DocVO> set = dao.getDocsByDivno("10");
		for (DocVO aDoc : set) {
			System.out.print(aDoc.getDocno() + ",");
			System.out.print(aDoc.getDivno() + ",");
			System.out.print(aDoc.getDocname() + ",");
			System.out.print(aDoc.getRoomno() + ",");
			System.out.print(aDoc.getSeniority() + ",");
			System.out.print(aDoc.getIntro() + ",");
			System.out.print(aDoc.getDocpic());
			System.out.print(aDoc.getDocstatus());
			System.out.println();
		}
	}
}