package com.auth.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.auth.model.*;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		

		
			
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer empID = new Integer(req.getParameter("empID"));
//				
//				/***************************2.開始查詢資料****************************************/
//				EmpService empSvc = new EmpService();
//				EmpVO empVO = empSvc.getOneEmp(empID);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/emp/update_emp_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/listAllEmp.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer empno = new Integer(req.getParameter("empno").trim());
//				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				String job = req.getParameter("job").trim();
//				if (job == null || job.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}	
//				
//				java.sql.Date hiredate = null;
//				try {
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//
//				Double sal = null;
//				try {
//					sal = new Double(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}
//
//				Integer deptno = new Integer(req.getParameter("deptno").trim());
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/emp/update_emp_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);
//			
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("empVO", empVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/emp/listOneEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/emp/update_emp_input.jsp");
//				failureView.forward(req, res);
//			}
//		}

//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String empName = req.getParameter("empName");
//				String empNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (empName == null || empName.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!empName.trim().matches(empNameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
//				
//				
//				String empGender = req.getParameter("empGender");
//				if (empGender == null) {
//					empGender = "男";
//					errorMsgs.add("請選擇一個性別");
//				}
//				
//				java.sql.Date empBirth = null;
//				try {
//					//將前端日期字串轉成JAVA Date物件
//					empBirth = java.sql.Date.valueOf(req.getParameter("empBirth").trim());
//				} catch (IllegalArgumentException e) {
//					//例外把日期設為今天日期
//					empBirth=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				
//				String empJob = req.getParameter("empJob").trim();
//				if (empJob == null || empJob.trim().length() == 0) {
//					errorMsgs.add("職位請勿空白");
//				}
//				
//				String empPhone = req.getParameter("empPhone").trim();
//				String empPhoneReg = "^[(0-9)]{9,11}$";
//				if (empPhone == null || empPhone.trim().length() == 0) {
//					errorMsgs.add("電話請勿空白");
//				}else if(!empPhone.trim().matches(empPhoneReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("電話只能是數字 , 且長度不能大於11碼");
//	            }
//				
//				String empAddress = req.getParameter("empAddress").trim();
//				if (empAddress == null || empAddress.trim().length() == 0) {
//					errorMsgs.add("地址請勿空白");
//				}
//				
//				String empAcc = req.getParameter("empAcc").trim();
//				if (empAcc == null || empAcc.trim().length() == 0) {
//					errorMsgs.add("帳號請勿空白");
//				}
//				
//				String empPwd = req.getParameter("empPwd").trim();
//				if (empPwd == null || empPwd.trim().length() == 0) {
//					errorMsgs.add("密碼請勿空白");
//				}
//				
//				//這行有問題
//				byte[] empPic =null;
//				empPic = getPictureByteArray("C:\\c.jpg");
//				
//
//			
//				java.sql.Date hiredate = null;
//				try {
//					//將前端日期字串轉成JAVA Date物件
//					hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					//例外把日期設為今天日期
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//				java.sql.Date quitdate = null;
//				
//				
//				
//				Integer empStatus = null;
//				try {
//					empStatus = new Integer(req.getParameter("empStatus").trim());
//				} catch (NumberFormatException e) {
//					empStatus = 1;
//					errorMsgs.add("狀態請填數字1~3");
//				}
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpName(empName);
//				empVO.setEmpGender(empGender);
//				empVO.setEmpBirth(empBirth);
//				empVO.setEmpJob(empJob);
//				empVO.setEmpPhone(empPhone);
//				empVO.setEmpAddress(empAddress);
//				empVO.setEmpAcc(empAcc);
//				empVO.setEmpPwd(empPwd);
//				empVO.setEmpPic(empPic);
//				empVO.setHiredate(hiredate);
//				empVO.setQuitdate(quitdate);
//				empVO.setEmpStatus(empStatus);
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/back-end/emp/addEmp.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				EmpService empSvc = new EmpService();
//				empVO = empSvc.addEmp(empName,empGender,empBirth,
//						 empJob,  empPhone, empAddress,  empAcc,  empPwd,
//						 empPic, hiredate, quitdate,  empStatus);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/emp/listAllEmp.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage()+"其他的錯誤");
//				System.out.println("我的錯");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-end/emp/addEmp.jsp");
//				failureView.forward(req, res);
			}
		}
		
		
	
	