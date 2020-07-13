package com.doc.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DocDAO implements DocDAO_interface {
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
			"INSERT INTO DOCTOR (docno,divno,docname,roomno,seniority,intro,docpic,docstatus) VALUES ('DR'||LPAD(TO_CHAR(DOCTOR_SEQ.NEXTVAL),2,'0'), ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT docno,divno,docname,roomno,seniority,intro,docpic,docstatus FROM DOCTOR order by docno";
	private static final String GET_ONE_STMT = 
			"SELECT docno,divno,docname,roomno,seniority,intro,docpic,docstatus FROM DOCTOR where docno = ?";
	private static final String DELETE = 
			"DELETE FROM DOCTOR where docno = ?";
	private static final String UPDATE = 
			"UPDATE DOCTOR set divno=?, docname=?, roomno=?, seniority=?, intro=?, docpic=?, docstatus=? where docno = ?";
	private static final String GET_ALL_BYDIVNO = 
			"SELECT docno,divno,docname,roomno,seniority,intro,docpic,docstatus FROM DOCTOR where divno = ? order by docno ";
	@Override
	public void insert(DocVO docVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, docVO.getDivno());
			pstmt.setString(2, docVO.getDocname());
			pstmt.setInt(3, docVO.getRoomno());
			pstmt.setInt(4, docVO.getSeniority());
			pstmt.setString(5, docVO.getIntro());
			pstmt.setBytes(6, docVO.getDocpic());
			pstmt.setInt(7, docVO.getDocstatus());

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
	public void update(DocVO docVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, docVO.getDivno());
			pstmt.setString(2, docVO.getDocname());
			pstmt.setInt(3, docVO.getRoomno());
			pstmt.setInt(4, docVO.getSeniority());
			pstmt.setString(5, docVO.getIntro());
			pstmt.setBytes(6, docVO.getDocpic());
			pstmt.setInt(7, docVO.getDocstatus());
			pstmt.setString(8, docVO.getDocno());


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
	@Override
	public void delete(String docno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, docno);

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
		public DocVO findByPrimaryKey(String docno) {

			DocVO docVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setString(1, docno);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// docVo �]�٬� Domain objects
					docVO = new DocVO();
					docVO.setDocno(rs.getString("docno"));
					docVO.setDivno(rs.getString("divno"));
					docVO.setDocname(rs.getString("docname"));
					docVO.setRoomno(rs.getInt("roomno"));
					docVO.setSeniority(rs.getInt("seniority"));
					docVO.setIntro(rs.getString("intro"));
					docVO.setDocpic(rs.getBytes("docpic"));
					docVO.setDocstatus(rs.getInt("docstatus"));
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
			return docVO;
		}
		
		@Override
		public List<DocVO> getAllByDiv(String divno) {
			List<DocVO> list = new ArrayList<DocVO>();
			DocVO docVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_BYDIVNO);

				pstmt.setString(1, divno);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// docVo �]�٬� Domain objects
					docVO = new DocVO();
					docVO.setDocno(rs.getString("docno"));
					docVO.setDivno(rs.getString("divno"));
					docVO.setDocname(rs.getString("docname"));

					list.add(docVO);
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
		
		@Override
		public List<DocVO> getAll() {
			List<DocVO> list = new ArrayList<DocVO>();
			DocVO docVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// docVo �]�٬� Domain objects
					docVO = new DocVO();
					docVO.setDocno(rs.getString("docno"));
					docVO.setDivno(rs.getString("divno"));
					docVO.setDocname(rs.getString("docname"));
					docVO.setRoomno(rs.getInt("roomno"));
					docVO.setSeniority(rs.getInt("seniority"));
					docVO.setIntro(rs.getString("intro"));
					docVO.setDocpic(rs.getBytes("docpic"));
					docVO.setDocstatus(rs.getInt("docstatus"));
					list.add(docVO); // Store the row in the list
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
	
	