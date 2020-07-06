package com.appt.controller;

import java.io.*;
import java.io.BufferedInputStream;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.appt.model.*;

/**
 * Servlet implementation class EmpShowImg
 */
public class ApptShowImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {

			String apptno = req.getParameter("apptno");
			ApptService apptSvc = new ApptService();
			ApptVO apptVO = apptSvc.getOneAppt(apptno);
			byte[] buf = apptVO.getSymphoto();
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
