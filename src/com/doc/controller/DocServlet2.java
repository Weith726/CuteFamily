package com.doc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.doc.model.*;

@MultipartConfig
public class DocServlet2 extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("docno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("此編號查無醫師");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				String docno = null;
				try {
					docno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入正確醫師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				DocService docSvc = new DocService();
				DocVO docVO = docSvc.getOneDoc(docno);
				if (docVO == null) {
					errorMsgs.add("請輸入正確醫師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("docVO", docVO); 
				String url = "/front-end/doc/listOneDoc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneDoc.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/doc/select_page.jsp");
				failureView.forward(req, res);
			}
		}
			
	}

	public byte[] getPartByteArray(HttpServletRequest req) throws ServletException, IOException{
		Part part = req.getPart("docpic");
		java.io.InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		
		return buf;
	}

}
