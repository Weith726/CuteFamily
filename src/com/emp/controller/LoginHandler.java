package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.*;

public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		

		
		HttpSession session = null;

		if ("login".equals(action)) {
			PrintWriter out = res.getWriter();

			// 【取得使用者 帳號(account) 密碼(password)】
			String account = req.getParameter("account");
			String password = req.getParameter("password");

			EmpService empSvc = new EmpService();
			EmpVO empVO =  empSvc.getEmpInfo(account, password);
			
			

			req.setAttribute("empVO", empVO);
			
			String empID = null;
			try {
			empID = empVO.getEmpID();//把查詢的ID結果放入empID

			}catch (Exception e) {
				if (empID == null) { //看查詢結果是不是空值
					out.print("<script language='javascript'>alert('帳號或密碼輸入錯誤，請重新輸入!!');window.location.href='login.html';</script>");                    //彈框提示,點選確定後返回登入介面
					return;
				}
			}
			//【帳號 , 密碼有效時, 才做以下工作】
					session = req.getSession();
					session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識
					session.setAttribute("empVONav",empVO); // *工作2: 把員工編號 姓名 照片放入empVONav物件

					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
					}

					res.sendRedirect(req.getContextPath() + "/back-end/Index.jsp"); // *工作3: (-->如無來源網頁:則重導首頁)
				}
			
			
		
			
		
		
			
		
		if ("logout".equals(action)) {
			session = req.getSession();
			session.removeAttribute("account");
			session.removeAttribute("empVO");
			System.out.println("登出有被執行");
			res.sendRedirect(req.getContextPath() + "/back-end/login.html");
		}
	}
}
