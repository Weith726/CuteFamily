package com.mem.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemberService;
import com.mem.model.MemberVO;

public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();	
		
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
						
		if("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
									
		try {
			MemberService mSvc = new MemberService();
			String memAccount = req.getParameter("memAccount");
			String memPassword = req.getParameter("memPassword");			
			MemberVO memberVO = new MemberVO();
		
			if (memAccount == null || (memAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
			}
						
			if (memAccount == null || (memAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入會員帳號");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher(req.getContextPath() +"/front-end/member/login.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			
			memberVO =mSvc.getOnememNO(memAccount,memPassword);
			
			
			if(memberVO ==null) {
				errorMsgs.add("會員帳密錯誤");	
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			session.setAttribute("memNO", memberVO.getMemNo());
			System.out.println(memberVO.getMemNo());
			
			Object memNO = session.getAttribute("memNO");
			//為了讓之後的網頁能取道會員名稱	
			MemberVO memvo=mSvc.getOneEmp(memberVO.getMemNo());
			String memName=memvo.getMemName();
			session.setAttribute("memName",memName);
			
			
			//為了讓之後的網頁能取道會員全部
			MemberVO member=mSvc.getOneEmp(memberVO.getMemNo());
			session.setAttribute("member",member);
			
			
			if(memNO == null) {
				session.setAttribute("location",req.getRequestURI());
				res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
				return;
			}else{						
			      //session.setAttribute("memNO",rs.getString("memNO"));   //*工作1: 才在session內做已經登入過的標識
			      
			       try {                                                        
			         String location = (String) session.getAttribute("location");
			         if (location != null) {
			           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
			           res.sendRedirect(location);            
			           return;
			         }
			       }catch (Exception ignored) { }

			      res.sendRedirect(req.getContextPath()+"/front-end/member/login.jsp");  //*工作3: (-->如無來源網頁:則重導至login_success.jsp)
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}
		
	}

}
