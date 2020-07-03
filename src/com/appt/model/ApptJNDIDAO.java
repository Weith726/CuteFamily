package com.appt.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.appt.model.ApptDAO_interface;

public class ApptJNDIDAO implements ApptDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO APPOINTMENT (apptno,memno,sessionno,seqno,symdesc,symphoto,optstate) VALUES (DOCTOR_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT apptno,memno,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT order by apptno";
	private static final String GET_ONE_STMT = 
			"SELECT apptno,memno,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT where apptno = ?";
	private static final String DELETE = 
			"DELETE FROM APPOINTMENT where apptno = ?";
	private static final String UPDATE = 
			"UPDATE APPOINTMENT set memno=?, sessionno=?, seqno=?, symdesc=?, symphoto=?, optstate=? where apptno = ?";

	@Override
	public void insert(ApptVO apptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, apptVO.getMemno());
			pstmt.setString(2, apptVO.getSessionno());
			pstmt.setInt(3, apptVO.getSeqno());
			pstmt.setString(4, apptVO.getSymdesc());
			pstmt.setBytes(5, apptVO.getSymphoto());
			pstmt.setInt(6, apptVO.getOptstate());

			pstmt.executeUpdate();

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
				apptVO.setSessionno(rs.getString("sessionno"));
				apptVO.setSeqno(rs.getInt("seqno"));
				apptVO.setSymdesc(rs.getString("symdesc"));
				apptVO.setSymphoto(rs.getBytes("symphoto"));
				apptVO.setOptstate(rs.getInt("optstate"));
				list.add(apptVO); // Store the row in the list
			}

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
		return list;
	}
}