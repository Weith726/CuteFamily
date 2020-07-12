package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mem.model.MemberVO;

public class PicServlet extends HttpServlet{
	
	private static final String show_PIC = "SELECT MEMPIC FROM MEMBER WHERE MEMNO=?";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		OutputStream outs = res.getOutputStream();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(show_PIC);
			String str = req.getParameter("memNo");
			pstmt.setString(1,str);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
			Blob b = rs.getBlob("mempic");
			long size = b.length();
			//out.print(size);
			byte[] bs = b.getBytes(1, (int)size);
			res.setContentType("image/gif");
//			outs = res.getOutputStream();
			outs.write(bs);
									
			}
						
			outs.flush();
			rs.close();
			pstmt.executeUpdate();
			
		}
		catch(NullPointerException e) {
			InputStream in = getServletContext().getResourceAsStream("/images/nopeople.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			outs.write(b);
			in.close();	
		}
		catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} 
		finally {
			if(outs !=null) {
				outs.close();			
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
				
	}
}
