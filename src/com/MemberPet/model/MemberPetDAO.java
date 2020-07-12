package com.MemberPet.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class MemberPetDAO implements MemberPetDAO_interface {

//	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB4");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "TEAM3";
	String passwd = "123456";
	
	
	
	//新增
	private static final String INSERT_STMT = "INSERT INTO MEMBERPET (petNo,memNo,petName,petVariety,petAge,petGender,petStatus,petPic) VALUES ('P'||LPAD(to_char(MEMBERPET_SEQ.NEXTVAL), 4, '0'),?,?,?,?,?,?,?)";
	//修改
	private static final String UPDATE_STMT = "UPDATE MEMBERPET SET memNo=?,petName=?,petVariety=?,petAge=?,petGender=?,petStatus=?,petPic=? WHERE petNo=?";
	//刪除
	private static final String DELETE_STMT = "DELETE FROM MEMBERPET WHERE petNo=?";
	//查單一寵物
	private static final String GET_ONE_STMT = "SELECT * FROM MEMBERPET WHERE petNo=?";
	//查全部寵物
	private static final String GET_ALL_STMT ="SELECT * FROM MEMBERPET ORDER BY petNo";
	//查詢相對應的會員所擁有的全部寵物
	private static final String GET_MEMBERPETS_FROM_THISMEMBER = "SELECT PETNO,PEtNAME FROM MEMBERPET WHERE MEMNO=?";
	
	
	@Override
	public void insert(MemberPetVO memberPetVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberPetVO.getMemNo());
			pstmt.setString(2, memberPetVO.getPetName());
			pstmt.setString(3, memberPetVO.getPetVariety());
			pstmt.setInt(4, memberPetVO.getPetAge());
			pstmt.setString(5, memberPetVO.getPetGender());
			pstmt.setInt(6, memberPetVO.getPetStatus());
			pstmt.setBytes(7, memberPetVO.getPetPic());

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(MemberPetVO memberPetVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, memberPetVO.getMemNo());
			pstmt.setString(2, memberPetVO.getPetName());
			pstmt.setString(3, memberPetVO.getPetVariety());
			pstmt.setInt(4, memberPetVO.getPetAge());
			pstmt.setString(5, memberPetVO.getPetGender());
			pstmt.setInt(6, memberPetVO.getPetStatus());
			pstmt.setBytes(7, memberPetVO.getPetPic());
			pstmt.setString(8, memberPetVO.getPetNo());

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(String petNo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setString(1, petNo);

			pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public MemberPetVO findByPrimaryKey(String petNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberPetVO memberPetVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, petNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberPetVO = new MemberPetVO();
				memberPetVO.setPetNo(rs.getString("petNo"));
				memberPetVO.setMemNo(rs.getString("memNo"));
				memberPetVO.setPetName(rs.getString("petName"));
				memberPetVO.setPetVariety(rs.getString("petVariety"));
				memberPetVO.setPetAge(rs.getInt("petAge"));
				memberPetVO.setPetGender(rs.getString("petGender"));
				memberPetVO.setPetStatus(rs.getInt("petStatus"));
				memberPetVO.setPetPic(rs.getBytes("petPic"));
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return memberPetVO;
	}

	@Override
	public List<MemberPetVO> getAll() {
		List<MemberPetVO> list = new ArrayList<MemberPetVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberPetVO memberPetVO = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberPetVO = new MemberPetVO();
				memberPetVO.setPetNo(rs.getString("petNo"));
				memberPetVO.setMemNo(rs.getString("memNo"));
				memberPetVO.setPetName(rs.getString("petName"));
				memberPetVO.setPetVariety(rs.getString("petVariety"));
				memberPetVO.setPetAge(rs.getInt("petAge"));
				memberPetVO.setPetGender(rs.getString("petGender"));
				memberPetVO.setPetStatus(rs.getInt("petStatus"));
				memberPetVO.setPetPic(rs.getBytes("petPic"));
				list.add(memberPetVO);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	@Override
	public List<MemberPetVO> getPetsFromThisMember(String memNo) {
		List<MemberPetVO> list = new ArrayList<MemberPetVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberPetVO memberPetVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBERPETS_FROM_THISMEMBER);

			pstmt.setString(1, memNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberPetVO = new MemberPetVO();
				memberPetVO.setPetNo(rs.getString("petNo"));
				memberPetVO.setPetName(rs.getString("petName"));
				list.add(memberPetVO);
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
}
