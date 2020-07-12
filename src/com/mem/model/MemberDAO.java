package com.mem.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.mem.model.*;




public class MemberDAO implements MemberDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	private static final String INSERT_STMT = "INSERT INTO MEMBER (memNo,memName,memAccount,memPassword,memCreditCardId,memPhone,memEmail,memAddress,memStatus) VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL), 4, '0'),?,?,?,?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE MEMBER SET memName=?,memAccount=?,memPassword=?,memCreditCardId=?,memPhone=?,memEmail=?,memAddress=?,memStatus=?,mempic=? WHERE memNo=?";
	private static final String DELETE_STMT = "UPDATE MEMBER SET memStatus=2 WHERE memNO = ?";
	private static final String GETALL_STAT = "SELECT * FROM MEMBER ORDER BY memNO"; //WHERE MEMSTATUS = '0'
	private static final String GET_ONE_STMT = "SELECT memNo,memName,memAccount,memPassword,memCreditCardId,memPhone,memEmail,memAddress,memStatus,mempic FROM MEMBER where memno = ?";
	private static final String INSERT_PIC = "UPDATE MEMBER SET MEMPIC=? WHERE memNo=?";
	private static final String READ_PIC = "SELECT MEMPIC FROM MEMBER WHERE MEMNO=?";
	public static final String  INSERT_ALL = "INSERT INTO MEMBER (memNo,memName,memAccount,memPassword,memCreditCardId,memPhone,memEmail,memAddress,memStatus,mempic) VALUES ('M'||LPAD(to_char(MEMBER_SEQ.NEXTVAL), 4, '0'),?,?,?,?,?,?,?,?,?)";
	private static final String login = "SELECT memNo FROM MEMBER WHERE memAccount=? AND memPassword=?";

	
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, memberVO.getMemNo());
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassword());
			pstmt.setString(4, memberVO.getMemCreditCardId());
			pstmt.setString(5, memberVO.getMemPhone());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddress());
			pstmt.setInt(8, memberVO.getMemStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassword());
			pstmt.setString(4, memberVO.getMemCreditCardId());
			pstmt.setString(5, memberVO.getMemPhone());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddress());
			pstmt.setInt(8, memberVO.getMemStatus());
			pstmt.setBytes(9, memberVO.getMempic());
			pstmt.setString(10, memberVO.getMemNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public void delete(String memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, memNo);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<MemberVO> getAll() {
		MemberVO memberVO = null;
		List<MemberVO> list = new ArrayList<MemberVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GETALL_STAT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();

				memberVO.setMemNo(rs.getString("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemCreditCardId(rs.getString("memCreditCardId"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddress(rs.getString("memAddress"));
				memberVO.setMemStatus(rs.getInt("memStatus"));

				list.add(memberVO);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public MemberVO findByPrimaryKey(String memNo) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				memberVO = new MemberVO();

				memberVO.setMemNo(rs.getString("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemCreditCardId(rs.getString("memCreditCardId"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddress(rs.getString("memAddress"));
				memberVO.setMempic(rs.getBytes("mempic"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				
//				memberVO.setMempic(rs.getBytes("mempic"));
			}

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return memberVO;
	}

	// MAIN上傳圖片
	public void passpic(String filename, Integer memNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PIC);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(filename);
			int i = 0;
//        File file = new File(filename);
//        int len = (int)file.length();
			// ��_SQL�y�k

			pstmt.setBinaryStream(1, fis, fis.available());
			pstmt.setInt(2, memNo);
			pstmt.executeUpdate();
			fis.close();

			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//MAIN讀取圖片
	public void readpic(Integer memNo) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(READ_PIC);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			FileOutputStream file = new FileOutputStream("D:/aaa/_"+".png");
			pstmt.setInt(1, memNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				BufferedInputStream bs = new BufferedInputStream(rs.getBinaryStream(1));
				byte[] buf = new byte[4 * 1024];  // 4K buffer
                int len;
                try {
					while ((len = bs.read(buf, 0, buf.length)) != -1)
					{
					    //out.write(buf, 0, len);
					    file.write(buf, 0, len);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertall(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt= null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement( INSERT_ALL);
			
			
			
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassword());
			pstmt.setString(4, memberVO.getMemCreditCardId());
			pstmt.setString(5, memberVO.getMemPhone());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddress());
			pstmt.setInt(8, memberVO.getMemStatus());
			pstmt.setBytes(9, memberVO.getMempic());
			
			
			pstmt.executeUpdate();
			

		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	//測試查詢單一會員編號,用在登入登出
	public MemberVO findBymemNO(String memAccount,String memPassword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO memberVO = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(login);
			
			pstmt.setString(1, memAccount);
			pstmt.setString(2, memPassword);			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("memNO"));				
			}
			
									
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberVO;
	}
	
	public List<MemberVO> getAll(Map<String,String[]> map){
		List<MemberVO> list = new ArrayList<MemberVO>(); 
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			String finalSQL = "select * from member"
//					+jdbcUtil_CompositeQuery_member.get_WhereCondition(map)
					+"order by memno";
			System.out.println(finalSQL);
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemNo(rs.getString("memNO"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemCreditCardId(rs.getString("memCreditCardId"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddress(rs.getString("memAddress"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				list.add(memberVO);
			}
			
		} catch (Exception se) {
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
}
