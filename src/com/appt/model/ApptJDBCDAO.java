package com.appt.model;

import java.util.*;

import com.appt.model.ApptJDBCDAO;
import com.appt.model.ApptVO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ApptJDBCDAO implements ApptDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456";
	
	private static final String INSERT_STMT = 
			"INSERT INTO APPOINTMENT (apptno,memno,sessionno,seqno,symdesc,symphoto,optstate) "
			+ "VALUES (to_char(sysdate,'yyyymmdd')||'-'||LPAD(to_char(APPOINTMENT_SEQ.NEXTVAL), 6, '0'), ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT apptno,memno,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT order by apptno";

	private static final String UPDATE = 
			"UPDATE APPOINTMENT set memno=?, sessionno=?, seqno=?, symdesc=?, symphoto=?, optstate=? where apptno = ?";
	
	//以下兩個用不到
	private static final String GET_ONE_STMT = 
			"SELECT apptno,memno,sessionno,seqno,symdesc,symphoto,optstate FROM APPOINTMENT where apptno = ?";
	private static final String DELETE = 
			"DELETE FROM APPOINTMENT where apptno = ?";
	

	@Override
	public void insert(ApptVO apptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, apptVO.getMemno());
			pstmt.setString(2, apptVO.getSessionno());
			pstmt.setInt(3, apptVO.getSeqno());
			pstmt.setString(4, apptVO.getSymdesc());
			pstmt.setBytes(5, apptVO.getSymphoto());
			pstmt.setInt(6, apptVO.getOptstate());

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
	public void update(ApptVO apptVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(String apptno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, apptno);

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
	public ApptVO findByPrimaryKey(String apptno) {

		ApptVO apptVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
//			image1 = getPictureByteArray("WebContent/Appt/image/.jpg");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		ApptJDBCDAO dao = new ApptJDBCDAO();
	
		// 新增
//		ApptVO apptVO1 = new ApptVO();
//		apptVO1.setMemno("M0001");
//		apptVO1.setSessionno("1");
//		apptVO1.setSeqno(5);
//		apptVO1.setSymdesc("食慾不振");
//
//		apptVO1.setOptstate(1);
//		dao.insert(apptVO1);
		
		//修改
//		ApptVO apptVO2 = new ApptVO();
//		apptVO2.setApptno("20200616-000002");
//		apptVO2.setMemno("M0002");
//		apptVO2.setSessionno("2");
//		apptVO2.setSeqno(15);
//		apptVO2.setSymdesc("一般外傷");
//		apptVO2.setSymphoto(image1);
//		apptVO2.setOptstate(2);
//		dao.insert(apptVO2);
		
		// 刪除
//				dao.delete("");
		// �d��
//		ApptVO apptVO3 = dao.findByPrimaryKey("20200727-000001");
//		System.out.print(apptVO3.getApptno() + ",");
//		System.out.print(apptVO3.getMemno() + ",");
//		System.out.print(apptVO3.getSessionno() + ",");
//		System.out.print(apptVO3.getSeqno() + ",");
//		System.out.print(apptVO3.getSymdesc() + ",");
//		System.out.print(apptVO3.getSymphoto() + ",");
//		System.out.println(apptVO3.getOptstate());
//		System.out.println("---------------------");

		// �d��
//		List<ApptVO> list = dao.getAll();
//		for (ApptVO aAppt : list) {
//			System.out.print(aAppt.getApptno() + ",");
//			System.out.print(aAppt.getMemno() + ",");
//			System.out.print(aAppt.getSessionno() + ",");
//			System.out.print(aAppt.getSeqno() + ",");
//			System.out.print(aAppt.getSymdesc() + ",");
//			System.out.print(aAppt.getSymphoto() + ",");
//			System.out.println(aAppt.getOptstate());
//			System.out.println();
//		}
//		
//		
//	}
	
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
	}
}
