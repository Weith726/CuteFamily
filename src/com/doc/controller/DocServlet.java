package com.doc.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.doc.model.*;

@MultipartConfig
public class DocServlet extends HttpServlet {

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
				String str = req.getParameter("docno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("此編號查無醫師");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				String docno = null;
				try {
					docno = new String(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入正確醫師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				DocService docSvc = new DocService();
				DocVO docVO = docSvc.getOneDoc(docno);
				if (docVO == null) {
					errorMsgs.add("請輸入正確醫師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷

				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("docVO", docVO); 
				String url = "/back-end/doc/listOneDoc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\��� listOneDoc.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/select_page.jsp");
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
				String docno = new String(req.getParameter("docno"));

				/***************************2.�}�l�d�߸��****************************************/
				DocService docSvc = new DocService();
				DocVO docVO = docSvc.getOneDoc(docno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("docVO", docVO);         // ��Ʈw���X��docVO����,�s�Jreq
				String url = "/back-end/doc/update_doc_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("錯誤訊息:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/listAllDoc.jsp");
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
				String docno = req.getParameter("docno").trim();
				String divno = req.getParameter("divno").trim();
				if (divno == null || divno.trim().length() == 0) {
					errorMsgs.add("請填科別編號");
				}	
				
				String docname = req.getParameter("docname");
				
				String docnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (docname == null || docname.trim().length() == 0) {
					errorMsgs.add("請填醫師姓名");
				} else if(!docname.trim().matches(docnameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("醫師姓名請填中英文數字，最大長度為10");
	            }
				
				Integer roomno = null;
				try {
					roomno = new Integer(req.getParameter("roomno").trim());
				} catch (NumberFormatException e) {
					roomno = 0;
					errorMsgs.add("診間號碼請填數字");
				}
//				if (roomno == null) {
//					errorMsgs.add("請填診間號碼");
//				}	
				
				Integer seniority = null;
				try {
					seniority = new Integer(req.getParameter("seniority").trim());
				} catch (NumberFormatException e) {
					seniority = 0;
					errorMsgs.add("年資請填數字.");
				}
//				if (seniority == null) {
//					errorMsgs.add("請填年資");
//				}	

				String intro = req.getParameter("intro").trim();
				if (intro == null || intro.trim().length() == 0) {
					errorMsgs.add("請填介紹");
				}	
				
				byte[] docpic = null;
				Part part = req.getPart("docpic");
				InputStream in = part.getInputStream();
				System.out.println(in.available());
				//先判斷Part裡面有沒有資料,Part裡沒有資料代表沒有上或修改新照片
				if (in.available() >0) {
				docpic = new byte[in.available()];
				in.read(docpic);
				in.close();
				} else {
				//Part裡沒有資料,NEW 一個Service去拿原本VO的byte[]
				DocService docsvc = new DocService();
				DocVO docvo = docsvc.getOneDoc(docno);
				docpic = docvo.getDocpic();
				}
				
				Integer docstatus = null;
				try {
					docstatus = new Integer(req.getParameter("docstatus").trim());
				} catch (NumberFormatException e) {
					docstatus = 0;
					errorMsgs.add("在職狀態請填數字.");
				}
//				if (docstatus == null) {
//					errorMsgs.add("請填在職狀態");
//				}	
				

				DocVO docVO = new DocVO();

				docVO.setDocno(docno);
				docVO.setDivno(divno);
				docVO.setDocname(docname);
				docVO.setRoomno(roomno);
				docVO.setSeniority(seniority);
				docVO.setIntro(intro);
				docVO.setDocpic(docpic);
				docVO.setDocstatus(docstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
//					String url = "/back-end/doc/select_page.jsp";
					req.setAttribute("docVO", docVO); // 含有輸入格式錯誤的docVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/update_doc_input.jsp");
//					RequestDispatcher failureView = req
//							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; //程式中斷

				}
				
				/***************************2.開始修改資料*****************************************/
				DocService docSvc = new DocService();
				docVO = docSvc.updateDoc(docno, divno, docname, roomno, seniority, intro, docpic, docstatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("docVO", docVO); // 資料庫update成功後,正確的的docVO物件,存入req

				String url = "/back-end/doc/listOneDoc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneDoc.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
//				String url = "/back-end/doc/select_page.jsp";
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/update_doc_input.jsp");
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
				String divno = req.getParameter("divno").trim();
				if (divno == null || divno.trim().length() == 0) {
					errorMsgs.add("請填科別編號");
				}	
				
				String docname = req.getParameter("docname");
				String docnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (docname == null || docname.trim().length() == 0) {
					errorMsgs.add("請填醫師姓名");
				} else if(!docname.trim().matches(docnameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("醫師姓名請填中英文數字，最大長度為10");
	            }
				
				Integer roomno = new Integer(req.getParameter("roomno").trim());
				if (roomno == null) {
					errorMsgs.add("請填診間號碼");
				}	
				try {
					roomno = new Integer(req.getParameter("roomno").trim());
				} catch (NumberFormatException e) {
					roomno = 0;
					errorMsgs.add("診間號碼請填數字");
				}
				
				Integer seniority = new Integer(req.getParameter("seniority").trim());
				if (seniority == null) {
					errorMsgs.add("請填年資");
				}	
				try {
					seniority = new Integer(req.getParameter("seniority").trim());
				} catch (NumberFormatException e) {
					seniority = 0;
					errorMsgs.add("年資請填數字.");
				}

				String intro = req.getParameter("intro").trim();
				if (intro == null || intro.trim().length() == 0) {
					errorMsgs.add("請填介紹");
				}	
				
				byte[] docpic = getPartByteArray(req);
				
				Integer docstatus = new Integer(req.getParameter("docstatus").trim());
				if (docstatus == null) {
					errorMsgs.add("請填在職狀態");
				}	
				try {
					docstatus = new Integer(req.getParameter("docstatus").trim());
				} catch (NumberFormatException e) {
					docstatus = 0;
					errorMsgs.add("在職狀態請填數字.");
				}

				DocVO docVO = new DocVO();

				docVO.setDivno(divno);
				docVO.setDocname(docname);
				docVO.setRoomno(roomno);
				docVO.setSeniority(seniority);
				docVO.setIntro(intro);
				docVO.setDocpic(docpic);
				docVO.setDocstatus(docstatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("docVO", docVO); // 含有輸入格式錯誤的empVO物件,也存入req

					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/doc/addDoc.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				DocService docSvc = new DocService();
				docVO = docSvc.addDoc(divno, docname, roomno, seniority, intro,docpic, docstatus);
				
				/***************************3..新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/doc/listAllDoc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/addDoc.jsp");
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
				String docno = new String(req.getParameter("docno"));
				
				/***************************2.開始刪除資料***************************************/
				DocService docSvc = new DocService();
				docSvc.deleteDoc(docno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/doc/listAllDoc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/doc/listAllDoc.jsp");
				failureView.forward(req, res);
			}
		}
	}

	public byte[] getPartByteArray(HttpServletRequest req) throws ServletException, IOException{
		Part part = req.getPart("docpic");
		java.io.InputStream in = part.getInputStream();
		byte[] buf = new byte[in.available()];
		in.read(buf);
		in.close();
		
		return buf;
	}

}
