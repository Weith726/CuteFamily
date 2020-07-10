package com.appt.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.opt.model.*;



public class ApptServletFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("addAppt".equals(action)) { 
			
			try {
//			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
			
			String sessionNo = req.getParameter("sessionNo");
			
			OptVO optVO = new OptVO();
			optVO.setSessionNo(sessionNo);
			
			OptService optSvc = new OptService();
			optVO = optSvc.getOneOptSession(sessionNo);
			
			req.setAttribute("optVO", optVO);
			
			System.out.println(optVO.getOptSession());
			
			String url = "/front-end/appt/addAppt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			}catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/appt/dispOpt.jsp");
				failureView.forward(req, res);
			}
	
		
	}
		

}
}
