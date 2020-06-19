package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.emp.model.*;

@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("login".equals(action)) {
			PrintWriter out = res.getWriter();

			// 【取得使用者 帳號(account) 密碼(password)】
			String account = req.getParameter("account");
			String password = req.getParameter("password");

			EmpService empSvc = new EmpService();
			List<EmpVO> list =  empSvc.getAll();

			req.setAttribute("list", list);
			
			for(EmpVO empVO:list) {

			String empAcc = empVO.getEmpAcc();
			String empPwd = empVO.getEmpPwd();

			// 【檢查該帳號 , 密碼是否有效】
			if (!(empAcc.equals(account) && empPwd.equals(password))) { // 【帳號 , 密碼無效時】
				out.println("<HTML><HEAD><TITLE>Access Denied</TITLE></HEAD>");
				out.println("<BODY>你的帳號 , 密碼無效!<BR>");
				out.println("請按此重新登入 <A HREF=" + req.getContextPath() + "/login.html>重新登入</A>");
				out.println("</BODY></HTML>");
				System.out.println(empVO.getEmpAcc()+" "+account);
			} else { // 【帳號 , 密碼有效時, 才做以下工作】
				HttpSession session = req.getSession();
				session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}

				res.sendRedirect(req.getContextPath() + "/back-end/Index.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
			}
			}
		}
	}
}
