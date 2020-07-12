package com.mem.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public logout() {
        super();
    }

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		String session2= session.getAttribute("memNO").toString(); 
		String url = req.getContextPath();
		if(session2 == null){
			session.setAttribute("location",req.getRequestURI());
			res.sendRedirect(url+"/front-end/member/login.jsp");
			return;
		}else {
			session.removeAttribute("memNO");
			res.sendRedirect(url+"/front-end/member/login.jsp");
			return;
		}
		
		
	}

}
