package com.doc.model;

import java.util.*;

import com.doc.model.DocJDBCDAO;
import com.doc.model.DocVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class DocJDBCDAO implements DocDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456"; 
	
	private static final String INSERT_STMT = 
			"INSERT INTO DOCTOR (docno,divno,docname,roomno,seniority,intro,docpic,docstatus) VALUES (DOCTOR_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, docVO.getDivno());
			pstmt.setString(2, docVO.getDocname());
			pstmt.setInt(3, docVO.getRoomno());
			pstmt.setInt(4, docVO.getSeniority());
			pstmt.setString(5, docVO.getIntro());
			pstmt.setBytes(6, docVO.getDocpic());
			pstmt.setInt(7, docVO.getDocstatus());

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
	public void update(DocVO docVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String docno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, docno);

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
	public DocVO findByPrimaryKey(String docno) {

		DocVO docVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYDIVNO);

			pstmt.setString(1, divno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// docVo �]�٬� Domain objects
				docVO = new DocVO();
				docVO.setDocno(rs.getString("docno"));
				docVO.setDivno(rs.getString("divno"));
				docVO.setDocname(rs.getString("docname"));
				docVO.setRoomno(rs.getInt("roomno"));

				list.add(docVO); // Store the row in the list
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
					return list;
				}
	
	public static void main(String[] args) {
		
//		byte[] image1 = null;
//		
//		try {
//			image1 = getPictureByteArray("WebContent/Doctor/image/doctor1.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		DocJDBCDAO dao = new DocJDBCDAO();
	
		// �s�W
//		DocVO docVO1 = new DocVO();
//		docVO1.setDivno("20");
//		docVO1.setDocname("�i��l3");
//		docVO1.setRoomno(1);
//		docVO1.setSeniority(27);
//		docVO1.setIntro("�ؤH�Ĥ@����ߦ�G�z�R�v���M�a�A�����x�_���~��v���|�z�ƪ��Τ��إ����~��v���|�����p�X�|�Ʋz�ƪ��A�{���@�ɤp�ʪ��~��v�|WSAVA�N����~��Ш|���v�A�o�_�ХߨȬw�p�ʪ��~��v�|FASAVA�ξ�����v�C");
//		docVO1.setDocpic(image1);
//		docVO1.setDocstatus(2);
//		dao.insert(docVO1);
		
		//�ק�
//		DocVO docVO2 = new DocVO();
//		docVO2.setDocno("1009");
//		docVO2.setDivno("20");
//		docVO2.setDocname("�i��l2");
//		docVO2.setRoomno(2);
//		docVO2.setSeniority(37);
//		docVO2.setIntro("�ؤH�ĤG����ߦ�G�z�R�v���M�a�A�����x�_���~��v���|�z�ƪ��Τ��إ����~��v���|�����p�X�|�Ʋz�ƪ��A�{���@�ɤp�ʪ��~��v�|WSAVA�N����~��Ш|���v�A�o�_�ХߨȬw�p�ʪ��~��v�|FASAVA�ξ�����v�C");
//		docVO2.setDocpic(image1);
//		docVO2.setDocstatus(1);
//		dao.insert(docVO2);
		
		// �R��
//				dao.delete("");
		// �d��
//		DocVO docVO3 = dao.findByPrimaryKey("1002");
//		System.out.print(docVO3.getDocno() + ",");
//		System.out.print(docVO3.getDivno() + ",");
//		System.out.print(docVO3.getDocname() + ",");
//		System.out.print(docVO3.getRoomno() + ",");
//		System.out.print(docVO3.getSeniority() + ",");
//		System.out.print(docVO3.getIntro() + ",");
//		System.out.print(docVO3.getDocpic() + ",");
//		System.out.println(docVO3.getDocstatus());
//		System.out.println("---------------------");

		// �d��
//		List<DocVO> list = dao.getAll();
//		for (DocVO aDoc : list) {
//			System.out.print(aDoc.getDocno() + ",");
//			System.out.print(aDoc.getDivno() + ",");
//			System.out.print(aDoc.getDocname() + ",");
//			System.out.print(aDoc.getRoomno() + ",");
//			System.out.print(aDoc.getSeniority() + ",");
//			System.out.print(aDoc.getIntro() + ",");
//			System.out.print(aDoc.getDocpic());
//			System.out.print(aDoc.getDocstatus());
//			System.out.println();
//		}
		List<DocVO> list2 = dao.getAllByDiv("D01");
		for (DocVO aDoc : list2) {
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
	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		File file = new File(path);
//		FileInputStream fis = new FileInputStream(file);
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		byte[] buffer = new byte[8192];
//		int i;
//		while ((i = fis.read(buffer)) != -1) {
//			baos.write(buffer, 0, i);
//		}
//		baos.close();
//		fis.close();
//
//		return baos.toByteArray();
//	
//	}

	
}
