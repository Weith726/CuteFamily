package com.mr.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mr.model.MrVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Mr;

public class MrDAO implements MrDAO_interface {

	// �@�����ε{����,�w��@�Ӹ�Ʈw ,�@�Τ@��DataSource�Y�i
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
			"INSERT INTO medicalrecord (mrno,apptno,docno,petno,symptom,prescription,apptfee,medfee,operfee) VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(MEDICALRECORD_SEQ.NEXTVAL),6,'0'), ?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT mrno,apptno,docno,petno,symptom,prescription,apptfee,medfee,operfee FROM medicalrecord order by mrno";
		private static final String GET_ONE_STMT = 
			"SELECT mrno,apptno,docno,petno,symptom,prescription,apptfee,medfee,operfee FROM medicalrecord where mrno = ?";
		private static final String DELETE = 
			"DELETE FROM medicalrecord where mrno = ?";
		private static final String UPDATE = 
			"UPDATE medicalrecord set apptno=?, docno=?, petno=?, symptom=?, prescription=?, apptfee=?, medfee=?, operfee=? where mrno = ?";

	@Override
	public void insert(MrVO mrVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mrVO.getApptno());
			pstmt.setString(2, mrVO.getDocno());
			pstmt.setString(3, mrVO.getPetno());
			pstmt.setString(4, mrVO.getSymptom());
			pstmt.setString(5, mrVO.getPrescription());
			pstmt.setInt(6, mrVO.getApptfee());
			pstmt.setInt(7, mrVO.getMedfee());
			pstmt.setInt(8, mrVO.getOperfee());

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
	public void update(MrVO mrVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, mrVO.getApptno());
			pstmt.setString(2, mrVO.getDocno());
			pstmt.setString(3, mrVO.getPetno());
			pstmt.setString(4, mrVO.getSymptom());
			pstmt.setString(5, mrVO.getPrescription());
			pstmt.setInt(6, mrVO.getApptfee());
			pstmt.setInt(7, mrVO.getMedfee());
			pstmt.setInt(8, mrVO.getOperfee());
			pstmt.setString(9, mrVO.getMrno());


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
	public void delete(String mrno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mrno);

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
	public MrVO findByPrimaryKey(String mrno) {

		MrVO mrVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mrno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				mrVO = new MrVO();
				mrVO.setMrno(rs.getString("mrno"));
				mrVO.setApptno(rs.getString("apptno"));
				mrVO.setDocno(rs.getString("docno"));
				mrVO.setPetno(rs.getString("petno"));
				mrVO.setSymptom(rs.getString("symptom"));
				mrVO.setPrescription(rs.getString("prescription"));
				mrVO.setApptfee(rs.getInt("apptfee"));
				mrVO.setMedfee(rs.getInt("medfee"));
				mrVO.setOperfee(rs.getInt("operfee"));
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
		return mrVO;
	}

	@Override
	public List<MrVO> getAll() {
		List<MrVO> list = new ArrayList<MrVO>();
		MrVO mrVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mrVO �]�٬� Domain objects
				mrVO = new MrVO();
				mrVO.setMrno(rs.getString("mrno"));
				mrVO.setApptno(rs.getString("apptno"));
				mrVO.setDocno(rs.getString("docno"));
				mrVO.setPetno(rs.getString("petno"));
				mrVO.setSymptom(rs.getString("symptom"));
				mrVO.setPrescription(rs.getString("prescription"));
				mrVO.setApptfee(rs.getInt("apptfee"));
				mrVO.setMedfee(rs.getInt("medfee"));
				mrVO.setOperfee(rs.getInt("operfee"));
				list.add(mrVO); // Store the row in the list
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
	//複合查詢開始//
			@Override
			public List<MrVO> getAll(Map<String, String[]> map) {
				List<MrVO> list = new ArrayList<MrVO>();
				MrVO mrVO = null;
			
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				try {
					
					con = ds.getConnection();
					String finalSQL = "select * from medicalrecord "
				          + jdbcUtil_CompositeQuery_Mr.get_WhereCondition(map)
				          + "order by mrno";
					pstmt = con.prepareStatement(finalSQL);
					System.out.println("����finalSQL(by DAO) = "+finalSQL);
					rs = pstmt.executeQuery();
			
					while (rs.next()) {
						mrVO = new MrVO();
						mrVO.setMrno(rs.getString("mrno"));
						mrVO.setApptno(rs.getString("apptno"));
						mrVO.setDocno(rs.getString("docno"));
						mrVO.setPetno(rs.getString("petno"));
						mrVO.setSymptom(rs.getString("symptom"));
						mrVO.setPrescription(rs.getString("prescription"));
						mrVO.setApptfee(rs.getInt("apptfee"));
						mrVO.setMedfee(rs.getInt("medfee"));
						mrVO.setOperfee(rs.getInt("operfee"));
						list.add(mrVO); // Store the row in the List
					}
			
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
		//複合查詢結束//	
		}