package com.opt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import javax.servlet.http.*;
import com.opt.model.*;

/**
 * Servlet implementation class OptServlet
 */

public class OptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
			
						try {
							/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
							String sessionNo = req.getParameter("sessionNo");
							if (sessionNo == null || (sessionNo.trim()).length() == 0) {
								errorMsgs.add("請輸入員工編號");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/back-end/opt/select_page.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							sessionNo = null;
							try {
								sessionNo = req.getParameter("sessionNo");
							} catch (Exception e) {
								errorMsgs.add("員工編號格式不正確");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/back-end/opt/select_page.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							/***************************2.開始查詢資料*****************************************/
							OptService optSvc = new OptService();
							OptVO optVO = optSvc.getOneOptSession(sessionNo);
							if (optVO == null) {
								errorMsgs.add("查無資料");
							}
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								RequestDispatcher failureView = req
										.getRequestDispatcher("/back-end/opt/select_page.jsp");
								failureView.forward(req, res);
								return;//程式中斷
							}
							
							/***************************3.查詢完成,準備轉交(Send the Success view)*************/
							req.setAttribute("optVO", optVO); // 資料庫取出的empVO物件,存入req
							String url = "/back-end/opt/listOneOpt.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
							successView.forward(req, res);
			
							/***************************其他可能的錯誤處理*************************************/
						} catch (Exception e) {
							errorMsgs.add("無法取得資料:" + e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/opt/select_page.jsp");
							failureView.forward(req, res);
						}
					}

		
		
	}

}
