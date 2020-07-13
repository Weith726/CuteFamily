package com.mr.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.appt.model.ApptService;
import com.appt.model.ApptVO;
import com.mr.model.*;

@MultipartConfig
public class MrServlet extends HttpServlet {

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
				String str = req.getParameter("mrno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("此編號查無診療紀錄");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mr/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				String mrno = null;
				try {
					mrno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入診療紀錄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mr/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				MrService mrSvc = new MrService();
				MrVO mrVO = mrSvc.getOneMr(mrno);
				if (mrVO == null) {
					errorMsgs.add("請輸入正確的診療紀錄編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mr/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("mrVO", mrVO); 
				String url = "/back-end/mr/listOneMr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneDoc.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mr/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String mrno = new String(req.getParameter("mrno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MrService mrSvc = new MrService();
				MrVO mrVO = mrSvc.getOneMr(mrno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("mrVO", mrVO);         // ��Ʈw���X��docVO����,�s�Jreq
				String url = "/back-end/mr/update_mr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mr/listAllMr.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_doc_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String mrno = req.getParameter("mrno").trim();
				String apptno = req.getParameter("apptno").trim();
				if (apptno == null || apptno.trim().length() == 0) {
					errorMsgs.add("請填約診編號");
				}	
				
				String docno = req.getParameter("docno").trim();
				if (docno == null || docno.trim().length() == 0) {
					errorMsgs.add("醫師編號請勿空白");
				}	
				
				String petno = req.getParameter("petno").trim();
				if (petno == null || petno.trim().length() == 0) {
					errorMsgs.add("會員寵物編號請勿空白");
				}	
				
				String symptom = req.getParameter("symptom").trim();
				String prescription = req.getParameter("prescription").trim();
				
				Integer apptfee = null;
				try {
					apptfee = new Integer(req.getParameter("apptfee").trim());
				} catch (NumberFormatException e) {
					apptfee = 0;
					errorMsgs.add("預約費用請輸入數字");
				}
//				
				
				Integer medfee =  new Integer(req.getParameter("medfee").trim());
				Integer operfee = new Integer(req.getParameter("operfee").trim());
				

				MrVO mrVO = new MrVO();

				mrVO.setMrno(mrno);
				mrVO.setApptno(apptno);
				mrVO.setDocno(docno);
				mrVO.setPetno(petno);
				mrVO.setSymptom(symptom);
				mrVO.setPrescription(prescription);
				mrVO.setApptfee(apptfee);
				mrVO.setMedfee(medfee);
				mrVO.setOperfee(operfee);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					String url = "/back-end/doc/select_page.jsp";
					req.setAttribute("mrVO", mrVO); // 含有輸入格式錯誤的docVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mr/update_mr_input.jsp");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; //程式中斷

				}
				
				/***************************2.開始修改資料*****************************************/
				MrService mrSvc = new MrService();
				mrVO = mrSvc.updateMr(mrno, apptno, docno, petno, symptom, prescription, apptfee, medfee, operfee);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mrVO", mrVO); // 資料庫update成功後,正確的的docVO物件,存入req

				String url = "/back-end/mr/listOneMr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDoc.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
//				String url = "/back-end/doc/select_page.jsp";
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mr/update_mr_input.jsp");
//				RequestDispatcher failureView = req
//						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // { // 來自addDoc.jsp的請求    
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1..接收請求參數 - 輸入格式的錯誤處理*************************/
				String apptno = req.getParameter("apptno").trim();
				if (apptno == null || apptno.trim().length() == 0) {
					errorMsgs.add("請填約診編號");
				}	
				
				String docno = req.getParameter("docno").trim();
				if (docno == null || docno.trim().length() == 0) {
					errorMsgs.add("醫師編號請勿空白");
				}	
				
				String petno = req.getParameter("petno").trim();
				if (petno == null || petno.trim().length() == 0) {
					errorMsgs.add("會員寵物編號請勿空白");
				}	
				
				String symptom = req.getParameter("symptom").trim();
				String prescription = req.getParameter("prescription").trim();
				
				Integer apptfee = null;
				try {
					apptfee = new Integer(req.getParameter("apptfee").trim());
				} catch (NumberFormatException e) {
					apptfee = 0;
					errorMsgs.add("預約費用請輸入數字");
				}
//				
				
				Integer medfee =  new Integer(req.getParameter("medfee").trim());
				Integer operfee = new Integer(req.getParameter("operfee").trim());
				

				MrVO mrVO = new MrVO();

				mrVO.setApptno(apptno);
				mrVO.setDocno(docno);
				mrVO.setPetno(petno);
				mrVO.setSymptom(symptom);
				mrVO.setPrescription(prescription);
				mrVO.setApptfee(apptfee);
				mrVO.setMedfee(medfee);
				mrVO.setOperfee(operfee);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("mrVO", mrVO); // 含有輸入格式錯誤的mrVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/addMr.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MrService mrSvc = new MrService();
				mrVO = mrSvc.addMr(apptno, docno, petno, symptom, prescription, apptfee, medfee, operfee);
				
				/***************************3..新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/mr/listAllMr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/mr/addMr.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllDoc.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String mrno = new String(req.getParameter("mrno"));
				
				/***************************2.開始刪除資料***************************************/
				MrService mrSvc = new MrService();
				mrSvc.deleteMr(mrno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/mr/listAllMr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/listAllMr.jsp");
				failureView.forward(req, res);
			}
		}
	
	//複合查詢
			if ("listMr_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					
					/***************************1.�N��J����ରMap**********************************/ 
					//�ĥ�Map<String,String[]> getParameterMap()����k 
					//�`�N:an immutable java.util.Map 
					Map<String, String[]> map = req.getParameterMap();
					
					/***************************2.�}�l�ƦX�d��***************************************/
					MrService mrSvc = new MrService();
					List<MrVO> list  = mrSvc.getAll(map);
					
					/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
					req.setAttribute("listMr_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
					RequestDispatcher successView = req.getRequestDispatcher("/back-end/mr/listMr_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
					successView.forward(req, res);
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/mr/select_page.jsp");
					failureView.forward(req, res);
				}
			}		
		}

}
