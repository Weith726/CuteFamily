package com.appt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.appt.model.*;
import com.appt.model.ApptService;
import com.appt.model.ApptVO;
import com.doc.model.DocService;
import com.doc.model.DocVO;

@MultipartConfig
public class ApptServlet2 extends HttpServlet {

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
				String str = req.getParameter("apptno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("此編號查無門診預約");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/appt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				String apptno = null;
				try {
					apptno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入正確門診預約編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/appt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ApptService apptSvc = new ApptService();
				ApptVO apptVO = apptSvc.getOneAppt(apptno);
				if (apptVO == null) {
					errorMsgs.add("請輸入門診預約編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/appt/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("apptVO", apptVO); 
				String url = "/front-end/appt/listOneAppt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneDoc.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/appt/select_page.jsp");
				failureView.forward(req, res);
			}
			
			}
		//看診進度查詢
		if ("listAppt_ByCompositeQuery".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
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
				ApptService apptSvc = new ApptService();
				List<ApptVO> list  = apptSvc.getQueue(map);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("listAppt_ByCompositeQuery", list); // ��Ʈw���X��list����,�s�Jrequest
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/appt/listAppt_ByCompositeQuery.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/appt/select_page.jsp");
				failureView.forward(req, res);
			}
		}	
		
		//會員查詢預約
				if ("listAppt_ByCompositeQuery2".equals(action)) { // �Ӧ�select_page.jsp���ƦX�d�߽ШD
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
						ApptService apptSvc = new ApptService();
						List<ApptVO> list  = apptSvc.getQueue(map);
						
						/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
						req.setAttribute("listAppt_ByCompositeQuery2", list); // ��Ʈw���X��list����,�s�Jrequest
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/appt/listAppt_ByCompositeQuery2.jsp"); // ���\���listEmps_ByCompositeQuery.jsp
						successView.forward(req, res);
						
						/***************************��L�i�઺���~�B�z**********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/appt/select_page.jsp");
						failureView.forward(req, res);
					}
				}		
	
	
	//預約取消
	if ("cancel".equals(action)) { // �Ӧ�update_doc_input.jsp���ШD
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
	
		try {
			/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
			String apptno = req.getParameter("apptno").trim();
			
			Integer optstate = 2;
			
			ApptVO apptVO = new ApptVO();

			apptVO.setApptno(apptno);
			apptVO.setOptstate(optstate);

		
			
			/***************************2.開始修改資料*****************************************/
			ApptService apptSvc = new ApptService();
			apptVO = apptSvc.cancelAppt(apptno, optstate);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("apptVO", apptVO); // 資料庫update成功後,正確的的docVO物件,存入req

			String url = "/front-end/appt/apptquery.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDoc.jsp
			successView.forward(req, res);

			/***************************其他可能的錯誤處理*************************************/
		} catch (Exception e) {
//			String url = "/back-end/doc/select_page.jsp";
			errorMsgs.add("修改資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/appt/apptquery.jsp");
//			RequestDispatcher failureView = req
//					.getRequestDispatcher(url);
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
			String apptno = new String(req.getParameter("apptno"));
			
			/***************************2.開始刪除資料***************************************/
			ApptService apptSvc = new ApptService();
			apptSvc.deleteAppt(apptno);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/front-end/appt/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
			
			/***************************其他可能的錯誤處理**********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:"+e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/front-end/appt/select_page.jsp");
			failureView.forward(req, res);
		}
	}
	}
		//多一個}刪掉
		
	
	

	public byte[] getPartByteArray(HttpServletRequest req) throws ServletException, IOException{
		Part part = req.getPart("symphoto");
		java.io.InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		
		return buf;
	}
}
