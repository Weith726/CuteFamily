package com.opt.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;


public class OptDAO implements OptDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO OPTSESSION (sessionNo,docNo,optDate,optSession,"
			+ "currentCount,maximum) VALUES (optsession_seq.NEXTVAL, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT sessionNo,docNo,to_char(optDate,'yyyy-mm-dd')optDate,optSession,"
			+ "currentCount,maximum FROM OPTSESSION order by sessionNo";
	
	private static final String GET_ALL_STMT2 = "SELECT sessionNo,docName,to_char(optDate,'yyyy-mm-dd')optDate,"+
			"optSession,currentCount,maximum " + 
			"FROM OPTSESSION " + 
			"JOIN DOCTOR ON OPTSESSION.DOCNO = DOCTOR.DOCNO " + 
			"order by sessionNo";
	
	private static final String GET_ALL_BY_DOCNO = 
			"SELECT sessionNo,OPTSESSION.docno,docname,to_char(optDate,'yyyy-mm-dd')optDate,optSession,currentCount,maximum "+ 
			"FROM OPTSESSION "+
			"JOIN DOCTOR ON OPTSESSION.DOCNO = DOCTOR.DOCNO "+
			"where OPTSESSION.docno = ? and optDate >= sysdate-1 "+
			"order by sessionNo";

	private static final String GET_ONE_STMT = "SELECT sessionNo,docNo,to_char(optDate,'yyyy-mm-dd')optDate,optSession,"
			+ "currentCount,maximum FROM OPTSESSION where sessionNo = ?";

	private static final String DELETE = "DELETE FROM OPTSESSION where sessionNo = ?";

	private static final String UPDATE = "UPDATE OPTSESSION set docNo=?,optDate=?,optSession=?,"
			+ "currentCount=?,maximum=? where sessionNo = ?";
	
	private static final String UPDATECURRENTCOUNT = "UPDATE OPTSESSION set "
			+ "currentCount=? where sessionNo = ?";

	private static final String FIND_SESSIONNO = "SELECT sessionNo FROM OPTSESSION where docNo = ? AND optDate = ? AND optSession = ?";

	@Override
	public void insert(OptVO optVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, optVO.getDocNo());
			pstmt.setDate(2, optVO.getOptDate());
			pstmt.setString(3, optVO.getOptSession());
			pstmt.setInt(4, optVO.getCurrentCount());
			pstmt.setInt(5, optVO.getMaximum());


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
	public void update(OptVO optVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, optVO.getDocNo());
			pstmt.setDate(2, optVO.getOptDate());
			pstmt.setString(3, optVO.getOptSession());
			pstmt.setInt(4, optVO.getCurrentCount());
			pstmt.setInt(5, optVO.getMaximum());
			pstmt.setString(6, optVO.getSessionNo());

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
	public void updateCurrentCount(OptVO optVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATECURRENTCOUNT);

			pstmt.setInt(1, optVO.getCurrentCount());
			pstmt.setString(2, optVO.getSessionNo());

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
	public void delete(String sessionNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, sessionNo);

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
	public OptVO findByPrimaryKey(String sessionNo) {
		OptVO optVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, sessionNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				optVO = new OptVO();
				optVO.setSessionNo(rs.getString("sessionNo"));
				optVO.setDocNo(rs.getString("docNo"));
				optVO.setOptDate(rs.getDate("optDate"));
				optVO.setOptSession(rs.getString("optSession"));
				optVO.setCurrentCount(rs.getInt("currentCount"));
				optVO.setMaximum(rs.getInt("maximum"));

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
		return optVO;
	}
	
	
	@Override
	public OptVO findRepeat(String docNo, Date optDate, String optSession) {
		OptVO optVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_SESSIONNO);

			pstmt.setString(1, docNo);
			pstmt.setDate(2, optDate);
			pstmt.setString(3, optSession);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				optVO = new OptVO();
				optVO.setSessionNo(rs.getString("sessionNo"));

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
		return optVO;
	}

	@Override
	public List<OptVO> getAll() {
		List<OptVO> list = new ArrayList<OptVO>();
		OptVO optVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
			
				optVO = new OptVO();
				optVO.setSessionNo(rs.getString("sessionNo"));
				optVO.setDocNo(rs.getString("docNo"));
				optVO.setOptDate(rs.getDate("optDate"));
				optVO.setOptSession(rs.getString("optSession"));
				optVO.setCurrentCount(rs.getInt("currentCount"));
				optVO.setMaximum(rs.getInt("maximum"));

				list.add(optVO); // Store the row in the list
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
	public List<OptVO> getCalInfoByDoc(String docno) {
		List<OptVO> list = new ArrayList<OptVO>();
		OptVO optVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_DOCNO);
			pstmt.setString(1, docno);
			
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
			
				optVO = new OptVO();
				optVO.setTitle(rs.getString("docName"),rs.getInt("currentCount"),rs.getInt("maximum"),rs.getString("optSession"));
				optVO.setStart(rs.getDate("optDate"));
				optVO.setId(rs.getString("sessionNo"));
				list.add(optVO); // Store the row in the list
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
	public List<OptVO> getCalInfo() {
		List<OptVO> list = new ArrayList<OptVO>();
		OptVO optVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				
			
				optVO = new OptVO();
//		
				optVO.setTitle(rs.getString("docName"),rs.getInt("currentCount"),rs.getInt("maximum"),rs.getString("optSession"));
				optVO.setStart(rs.getDate("optDate"));
				list.add(optVO); // Store the row in the list
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
