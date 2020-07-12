package com.emp.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.emp.model.*;
import com.mail.MailService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {
	   // 將由底下的第26~30行用 java.io.File 於 ContextPath 之下, 自動建立目地目錄
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
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
				String empID = req.getParameter("empID");
				if (empID == null || (empID.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				empID = null;
				try {
					empID = req.getParameter("empID");
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empID);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		

		
			
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String empID = req.getParameter("empID");
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String empID = req.getParameter("empID").trim();
				
				
				String empName = req.getParameter("empName");
				
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String empGender = req.getParameter("empGender");
				if (empGender == null) {
					errorMsgs.add("請選擇性別");
				}
				
				java.sql.Date empBirth = null;
				try {
					//將前端日期字串轉成JAVA Date物件
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					//例外把日期設為今天日期
					empBirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				String empJob = req.getParameter("empJob").trim();
				if (empJob == null || empJob.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				String empPhone = req.getParameter("empPhone").trim();
				String empPhoneReg = "^[(0-9)]{9,11}$";
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!empPhone.trim().matches(empPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話只能是數字 , 且長度不能大於11碼");
	            }
				
				String empAddress = req.getParameter("empAddress").trim();
				if (empAddress == null || empAddress.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String empAcc = req.getParameter("empAcc").trim();
				String empAccReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (empAcc == null || empAcc.trim().length() == 0) {
					errorMsgs.add("郵件請勿空白");
				}else if(!empAcc.trim().matches(empAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("郵件地址格式錯誤");
	            }
				
				String empPwd = req.getParameter("empPwd").trim();
				if (empPwd == null || empPwd.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}
				

				//上傳圖片
				byte[] empPic = null;
				Part part = req.getPart("empPic");
				InputStream in = part.getInputStream();
			if(in.available()>0) {	
				empPic = new byte[in.available()];
				in.read(empPic);
				in.close();
			} else {
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empID);
				empPic = empVO.getEmpPic();
			}
				

			
				java.sql.Date hiredate = null;
				try {
					//將前端日期字串轉成JAVA Date物件
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					//例外把日期設為今天日期
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				java.sql.Date quitdate = null;
				try {
					//將前端日期字串轉成JAVA Date物件
					quitdate = java.sql.Date.valueOf(req.getParameter("quitdate").trim());
				} catch (IllegalArgumentException e) {
					//例外把日期設為今天日期
					quitdate = null;
					
				}
				
				
				
				Integer empStatus = null;
				try {
					empStatus = new Integer(req.getParameter("empStatus").trim());
				} catch (NumberFormatException e) {
					empStatus = 1;
					errorMsgs.add("狀態請填數字1~3");
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmpID(empID);
				empVO.setEmpName(empName);
				empVO.setEmpGender(empGender);
				empVO.setEmpBirth(empBirth);
				empVO.setEmpJob(empJob);
				empVO.setEmpPhone(empPhone);
				empVO.setEmpAddress(empAddress);
				empVO.setEmpAcc(empAcc);
				empVO.setEmpPwd(empPwd);
				empVO.setEmpPic(empPic);
				empVO.setHiredate(hiredate);
				empVO.setQuitdate(quitdate);
				empVO.setEmpStatus(empStatus);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empID, empName, empGender, empBirth, empJob,empPhone, empAddress,empAcc,empPwd,empPic,hiredate,quitdate,empStatus);
			
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
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
				String empName = req.getParameter("empName");
				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				String empGender = req.getParameter("empGender");
				if (empGender == null) {
					errorMsgs.add("請選擇性別");
					
				}
				
				
				java.sql.Date empBirth = null;
				try {
					//將前端日期字串轉成JAVA Date物件
					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
				} catch (IllegalArgumentException e) {
					//例外把日期設為null
					empBirth = null;
					errorMsgs.add("請輸入生日");
				}
				
				
				String empJob = req.getParameter("empJob").trim();
				if (empJob == null || empJob.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}
				
				String empPhone = req.getParameter("empPhone").trim();
				String empPhoneReg = "^[(0-9-)]{9,11}$";
				if (empPhone == null || empPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}else if(!empPhone.trim().matches(empPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("電話只能是數字 , 且長度不能大於11碼");
	            }
				
				String empAddress = req.getParameter("empAddress").trim();
				if (empAddress == null || empAddress.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				String empAcc = req.getParameter("empAcc").trim();
				String empAccReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (empAcc == null || empAcc.trim().length() == 0) {
					errorMsgs.add("郵件請勿空白");
				}else if(!empAcc.trim().matches(empAccReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("郵件地址格式錯誤");
	            }
				
				
				
				
				String empPwd = getRandomPwd();
				
				
				
//				String empPwd = req.getParameter("empPwd").trim();
//				if (empPwd == null || empPwd.trim().length() == 0) {
//					errorMsgs.add("密碼請勿空白");
//				}
				
				//上傳圖片
				Part part = req.getPart("empPic");
				InputStream in = part.getInputStream();
				byte[] empPic = new byte[in.available()];
				in.read(empPic);
				in.close();

				

			
				java.sql.Date hiredate = null;
				try {
					//將前端日期字串轉成JAVA Date物件
					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
				} catch (IllegalArgumentException e) {
					//例外把日期設為今天日期
					hiredate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入到職日!");
				}
				
				java.sql.Date quitdate = null;
				
				
				
				Integer empStatus = null;
				try {
					empStatus = new Integer(req.getParameter("empStatus").trim());
				} catch (NumberFormatException e) {
					empStatus = 1;
					errorMsgs.add("狀態請填數字1~3");
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmpName(empName);
				empVO.setEmpGender(empGender);
				empVO.setEmpBirth(empBirth);
				empVO.setEmpJob(empJob);
				empVO.setEmpPhone(empPhone);
				empVO.setEmpAddress(empAddress);
				empVO.setEmpAcc(empAcc);
				empVO.setEmpPwd(empPwd);
				empVO.setEmpPic(empPic);
				empVO.setHiredate(hiredate);
				empVO.setQuitdate(quitdate);
				empVO.setEmpStatus(empStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(empName,empGender,empBirth,
						 empJob,  empPhone, empAddress,  empAcc,  empPwd,
						 empPic, hiredate, quitdate,  empStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String to = empAcc;
			      
			      String subject = "萌寵家族員工密碼通知";
			      
			      String ch_name = empName;
			      String passRandom = empPwd;
			      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
			       
			      MailService mailService = new MailService();
			      mailService.sendMail(to, subject, messageText);
			      
			     

				
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage()+"其他的錯誤");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
        
        if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String empID = req.getParameter("empID");//前端取得的empno是string，存入前先轉成Integer跟VO一致
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(empID);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:此員工的權限未清除");
//				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
		
	
	}
	
	public String getRandomPwd() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer randomPwd = new StringBuffer("");
		for (int i = 1; i <= 8; i++) {
			
			randomPwd.append(str.charAt((int) (Math.random() * 62)));
		}
		return randomPwd.toString();
	}
	


	
}
