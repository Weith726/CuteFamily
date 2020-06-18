package com.emp.controller;

import java.io.*;
import java.io.BufferedInputStream;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.*;

/**
 * Servlet implementation class EmpShowImg
 */
public class EmpShowImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {

			String empID = req.getParameter("empID");
			EmpService empSvc = new EmpService();
			EmpVO empVO = empSvc.getOneEmp(empID);
			byte[] buf = empVO.getEmpPic();
			out.write(buf);

		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/Nodata/null.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}

	}

}
