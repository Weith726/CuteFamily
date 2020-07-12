package com.appt.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.opt.model.*;
import com.appt.model.ApptService;
import com.appt.model.ApptVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.mail.MailService;
import com.mem.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
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
		//測試用session
		HttpSession session = req.getSession();	
		
			
		if ("addAppt".equals(action)) {
			
			try {		
			String sessionNo = req.getParameter("sessionNo");
			
			OptVO optVO = new OptVO();
			optVO.setSessionNo(sessionNo);
			
			OptService optSvc = new OptService();
			optVO = optSvc.getOneOptSession(sessionNo);
			
			req.setAttribute("optVO", optVO);
			
			//給予假資料
			MemberService mSvc = new MemberService();

			MemberVO member=mSvc.getOneEmp("M0003");
			session.setAttribute("member",member);
			
//			session.setAttribute("memNO", "M0001");
			
			System.out.println(optVO.getOptSession());
			
			String url = "/front-end/appt/addAppt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
			}catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/appt/addAppt.jsp");
				failureView.forward(req, res);
			}	
	}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
        	
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memNo = req.getParameter("memNo");
				
				String petNo = req.getParameter("petNo");
							
				Integer seqNo = new Integer(req.getParameter("seqNo"));
				
				String sessionNo = req.getParameter("sessionNo");
				
				Integer optState = 0;
				
				String symdesc = req.getParameter("symdesc");
											
				//上傳圖片
				Part part = req.getPart("symphoto");
				InputStream in = part.getInputStream();
				byte[] symphoto = new byte[in.available()];
				in.read(symphoto);
				in.close();
				
				
				ApptVO apptVO = new ApptVO();
				apptVO.setMemno(memNo);
				apptVO.setPetNo(petNo);
				apptVO.setSessionno(sessionNo);
				apptVO.setSeqno(seqNo);
				apptVO.setSymdesc(symdesc);;
				apptVO.setSymphoto(symphoto);
				apptVO.setOptstate(optState);
				
				/***************************2.開始新增資料***************************************/
				ApptService apptSvc = new ApptService();
				apptVO = apptSvc.addAppt(memNo, petNo, sessionNo, seqNo, symdesc, symphoto, optState);
				
				//更新門診時段人數
				OptService optSvc = new OptService();
				optSvc.updateCurrentCount(seqNo, sessionNo);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				
				String url = "addAppt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage()+"其他的錯誤");
				RequestDispatcher failureView = req
						.getRequestDispatcher("addAppt.jsp");
				failureView.forward(req, res);
			}
		}
		
}
}
