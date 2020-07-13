package com.div.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.div.model.*;
import com.doc.model.*;

public class DivServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		    // �Ӧ�select_page.jsp���ШD                                  // �Ӧ� dept/listAllDept.jsp���ШD
		if ("listDocs_ByDivno_C".equals(action)){

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String divno = new String(req.getParameter("divno"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				DivService divSvc = new DivService();
				Set<DocVO> set = divSvc.getDocsByDivno(divno);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listDocs_ByDivno", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				 if ("listDocs_ByDivno_C".equals(action))
					url = "/front-end/div/listDocs_ByDivno.jsp"; // ���\��� dept/listAllDept.jsp

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		

	}
}
