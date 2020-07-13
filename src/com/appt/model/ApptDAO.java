package com.appt.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.appt.model.ApptDAO;
import com.appt.model.ApptVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Appt;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Appt2;

import java.sql.*;

public class ApptDAO implements ApptDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO APPOINTMENT (apptno,memno,petNo,sessionno,seqno,symdesc,symphoto,optstate) "
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(APPOINTMENT_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT apptno,memno,petNo,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT order by apptno";
	private static final String GET_ONE_STMT = "SELECT apptno,memno,petNo,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT where apptno = ?";
	private static final String DELETE = "DELETE FROM APPOINTMENT where apptno = ?";
	private static final String UPDATE = "UPDATE APPOINTMENT set memno=?, sessionno=?, seqno=?, symdesc=?, symphoto=?, optstate=? where apptno = ?";
	private static final String UPDATESTATE = "UPDATE APPOINTMENT set optstate= ? where apptno = ?";
	private static final String CANCEL = "UPDATE APPOINTMENT set optstate=? where apptno = ?";

	@Override
	public void insert(ApptVO apptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, apptVO.getMemno());
			pstmt.setString(2, apptVO.getPetNo());
			pstmt.setString(3, apptVO.getSessionno());
			pstmt.setInt(4, apptVO.getSeqno());
			pstmt.setString(5, apptVO.getSymdesc());
			pstmt.setBytes(6, apptVO.getSymphoto());
			pstmt.setInt(7, apptVO.getOptstate());

			pstmt.executeUpdate();

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
	public void update(ApptVO apptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, apptVO.getMemno());
			pstmt.setString(2, apptVO.getSessionno());
			pstmt.setInt(3, apptVO.getSeqno());
			pstmt.setString(4, apptVO.getSymdesc());
			pstmt.setBytes(5, apptVO.getSymphoto());
			pstmt.setInt(6, apptVO.getOptstate());
			pstmt.setString(7, apptVO.getApptno());

			pstmt.executeUpdate();
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
	public void updateState(ApptVO apptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATESTATE);

			pstmt.setInt(1, apptVO.getOptstate());
			pstmt.setString(2, apptVO.getApptno());

			pstmt.executeUpdate();
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
	public void delete(String apptno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, apptno);

			pstmt.executeUpdate();
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
	public ApptVO findByPrimaryKey(String apptno) {

		ApptVO apptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, apptno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// apptVo �]�٬� Domain objects
				apptVO = new ApptVO();
				apptVO.setApptno(rs.getString("apptno"));
				apptVO.setMemno(rs.getString("memno"));
				apptVO.setSessionno(rs.getString("sessionno"));
				apptVO.setSeqno(rs.getInt("seqno"));
				apptVO.setSymdesc(rs.getString("symdesc"));
				apptVO.setSymphoto(rs.getBytes("symphoto"));
				apptVO.setOptstate(rs.getInt("optstate"));
			}
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
		return apptVO;
	}

	@Override
	public List<ApptVO> getAll() {
		List<ApptVO> list = new ArrayList<ApptVO>();
		ApptVO apptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// apptVo �]�٬� Domain objects
				apptVO = new ApptVO();
				apptVO.setApptno(rs.getString("apptno"));
				apptVO.setMemno(rs.getString("memno"));
				apptVO.setPetNo(rs.getString("petNo"));
				apptVO.setSessionno(rs.getString("sessionno"));
				apptVO.setSeqno(rs.getInt("seqno"));
				apptVO.setSymdesc(rs.getString("symdesc"));
				apptVO.setSymphoto(rs.getBytes("symphoto"));
				apptVO.setOptstate(rs.getInt("optstate"));
				list.add(apptVO); // Store the row in the list
			}
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
	public List<ApptVO> getAll(Map<String, String[]> map) {
		List<ApptVO> list = new ArrayList<ApptVO>();
		ApptVO apptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "SELECT apptno,memName,docname,to_char(optDate,'yyyy-mm-dd')optDate,optSession,seqno,petNo,symdesc,symphoto,optstate "
					+ "FROM APPOINTMENT " + "JOIN OPTSESSION ON APPOINTMENT.sessionNo = OPTSESSION.sessionNo "
					+ "JOIN MEMBER ON APPOINTMENT.MEMNO = MEMBER.MEMNO "
					+ "JOIN DOCTOR ON OPTSESSION.DOCNO = DOCTOR.DOCNO "
					+ jdbcUtil_CompositeQuery_Appt.get_WhereCondition(map) + " order by seqno";

			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				apptVO = new ApptVO();
				apptVO.setApptno(rs.getString("apptno"));
				apptVO.setMemName(rs.getString("memName"));
				apptVO.setOptDate(rs.getDate("optDate"));
				apptVO.setDocname(rs.getString("docname"));
				apptVO.setOptSession(rs.getString("optSession"));
				apptVO.setPetNo(rs.getString("petNo"));
				apptVO.setSeqno(rs.getInt("seqno"));
				apptVO.setSymdesc(rs.getString("symdesc"));
				apptVO.setSymphoto(rs.getBytes("symphoto"));
				apptVO.setOptstate(rs.getInt("optstate"));
				list.add(apptVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 看診進度查詢開始//
	@Override
	public List<ApptVO> getQueue(Map<String, String[]> map) {
		List<ApptVO> list = new ArrayList<ApptVO>();
		ApptVO apptVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			String finalSQL = "select * from APPOINTMENT " + jdbcUtil_CompositeQuery_Appt2.get_WhereCondition(map)
					+ "order by apptno";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("����finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				apptVO = new ApptVO();
				apptVO.setApptno(rs.getString("apptno"));
				apptVO.setMemno(rs.getString("memno"));
				apptVO.setPetNo(rs.getString("petNo"));
				apptVO.setSessionno(rs.getString("sessionno"));
				apptVO.setSeqno(rs.getInt("seqno"));
				apptVO.setSymdesc(rs.getString("symdesc"));
				apptVO.setSymphoto(rs.getBytes("symphoto"));
				apptVO.setOptstate(rs.getInt("optstate"));
				list.add(apptVO); // Store the row in the List
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//看診進度查詢結束//	
//取消//
	@Override
	public void cancel(ApptVO apptVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(CANCEL);

			pstmt.setInt(1, apptVO.getOptstate());
			pstmt.setString(2, apptVO.getApptno());

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

}
