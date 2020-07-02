package com.doc.controller;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ShowDocPic extends HttpServlet{
	
	java.sql.Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		res.setContentType("images/gif");
		javax.servlet.ServletOutputStream out = res.getOutputStream();
		
		try {
			String sql = "SELECT docpic FROM doctor WHERE docno = ?";
			java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
			String docno = req.getParameter("docno");
			pstmt.setString(1, docno);
			
			java.sql.ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				java.io.BufferedInputStream in = new java.io.BufferedInputStream(rs.getBinaryStream("docpic"));
				byte[] buf = new byte [4*1024];
				int len;
				while ((len = in.read(buf)) != -1){
					out.write(buf, 0, len);
				}
				in.close();
			}else {
				java.io.InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			
		} catch(Exception e) {
			java.io.InputStream in = getServletContext().getResourceAsStream("/NoData/none.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
	
	public void init() throws ServletException{
		try {
			javax.naming.Context ctx = new javax.naming.InitialContext();
			javax.sql.DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (javax.naming.NamingException ne) {
			ne.printStackTrace(System.err);
		} catch (java.sql.SQLException se) {
			se.printStackTrace(System.err);
		}
	}
		


public void destroy() {
	try {
		if (con != null) {
			con.close();
		}
	}catch (java.sql.SQLException se) {
		se.printStackTrace(System.err);
	}
}

}