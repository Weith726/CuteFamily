package com.mem.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.mem.model.MemberVO;
import com.mem.model.*;
@MultipartConfig(maxRequestSize=100*1024*1024)


public class MemberServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
//		HttpSession session = req.getSession();
		
		if("getOne_For_Display".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
	
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				
				String str = req.getParameter("memNo");
				
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/Member_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				//判斷是否格是正確
				String memno = null;
				if(!str.matches("[M]{1}\\d\\d\\d\\d")) {
					errorMsgs.add("會員編號格式不正確");
				}				
				//因為是字串所以先註解掉
//				try {
//					memno =str;
//				} catch (Exception e) {
//					errorMsgs.add("會員編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/Member_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
			
			/////////////////////////////////////////////
//			String str = req.getParameter("memNo");
			
//			List<MemberVO> memVO = memSvc.getAll();
			
			memno = str;  //為了讓下面有東西接
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getOneEmp(memno);
			if(memVO ==null) {
				errorMsgs.add("查無資料");	
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/Member_select.jsp");
				failureView.forward(req, res);
				return;//程式中斷
				
			}
			
			//因為過濾器重導有問題，嘗試放在session
			HttpSession session = req.getSession();
			session.setAttribute("memVO",memVO);
			req.setAttribute("memVO",memVO);
			String url = "/back-end/member/listonemem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			
				successView.forward(req, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//上傳全部包含照片
		if("picture".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//原本要寫在上面一點的,但是錯誤訊息判斷有問題先放到下面來
			Integer memStatus = null;
			try {
				
				
				Part part = req.getPart("pic");
				InputStream in =  part.getInputStream();
				byte[] buf = new byte[in.available()];
				in.read(buf);
				
				if (part == null || buf == null) {
					errorMsgs.add("會員照片請勿空白");
				}	
				
				String memName = req.getParameter("memName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAccount = req.getParameter("memAccount").trim();
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}	
			
				String memPassword = req.getParameter("memPassword").trim();
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String memCreditCardId = req.getParameter("memCreditCardId").trim();
				if (memCreditCardId == null || memCreditCardId.trim().length() == 0) {
					errorMsgs.add("信用卡號請勿空白");
				}
				
				String memPhone = req.getParameter("memPhone").trim();				
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				}
				
				String memEmail = req.getParameter("memEmail").trim();
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("會員信箱請勿空白");
				}
				
				String memAddress = req.getParameter("memAddress").trim();
				if (memAddress == null || memAddress.trim().length() == 0) {
					errorMsgs.add("會員地址請勿空白");
				}
				
			//	Integer memStatus = new Integer(req.getParameter("memStatus").trim());
				String Status = req.getParameter("memStatus").trim();
				if (Status == null || Status.toString().length() == 0) {
					errorMsgs.add("會員狀態請勿空白");
				}
				try {
				memStatus =new Integer(Status);
				}catch(Exception e) {
					errorMsgs.add("會員狀態型態不正確");
				}
				
				MemberVO memVO = new MemberVO();
				
				memVO.setMemName(memName);
				memVO.setMemAccount(memAccount);
				memVO.setMemPassword(memPassword);
				memVO.setMemCreditCardId(memCreditCardId);
				memVO.setMemPhone(memPhone);
				memVO.setMemEmail(memEmail);
				memVO.setMemAddress(memAddress);
				memVO.setMemStatus(memStatus);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); //含有輸入格式錯誤的memVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/addmember.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				MemberService memSvc = new MemberService();
				memSvc.addpic(memName, memAccount, memPassword, memCreditCardId, memPhone, memEmail, memAddress, memStatus,buf);
				
				String url = "/front-end/member/login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if("getOne_For_Update".equals(action) || "getOne_For_Update_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try { 
				
				/***************************1.接收請求參數****************************************/
				String memNO = req.getParameter("memNO");
				
				//為了讓未登入按下查詢不會有問題
				if (memNO == null || (memNO.trim()).length() == 0) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}	
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneEmp(memNO);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = null;
				if ("getOne_For_Update".equals(action))
					url = "/front-end/member/updatemem.jsp";        
				else if ("getOne_For_Update_B".equals(action))
					url = "/back-end/member/updatemem.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交  update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				//這邊有一個問題,登入頁面按編輯會員資料因為是空值所以會報錯導回listALL又因為listALL被過濾器導回登入頁面,視情況修改
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { //  來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				String memNO = req.getParameter("memNO");
				
				/***************************2.開始刪除資料***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteM(memNO);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/member/listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action) || "update_B".equals(action)) { // 來自updatemem.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			Integer memStatus = null;
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memNO = req.getParameter("memNO");
				String memname = req.getParameter("memname");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memname == null || memname.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!memname.trim().matches(nameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String account = req.getParameter("account").trim();
				if (account == null || account.trim().length() == 0) {
					errorMsgs.add("會員帳號請勿空白");
				}	
				
				String password = req.getParameter("password").trim();
				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("會員密碼請勿空白");
				}
				
				String creditCardId = req.getParameter("creditCardId").trim();
				if (creditCardId == null || creditCardId.trim().length() == 0) {
					errorMsgs.add("信用卡號請勿空白");
				}
				
				String phone = req.getParameter("phone").trim();
				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				}
				
				String email = req.getParameter("email").trim();
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("會員信箱請勿空白");
				}
				
				String address = req.getParameter("address").trim();
				if (address == null || address.trim().length() == 0) {
					errorMsgs.add("會員地址請勿空白");
				}
				
				String status = req.getParameter("status").trim();				
				if (status == null || status.toString().length() == 0) {
					errorMsgs.add("會員狀態請勿空白");
				}
				try {
				memStatus = new Integer(status);
				}catch(Exception e) {
					errorMsgs.add("會員狀態型態不正確");
				}
				byte[] mempic = null;
				MemberService memSvc = new MemberService();
				Part part = req.getPart("mempic");
				InputStream in =  part.getInputStream();
				
				if(in.available()>0) {		
					mempic = new byte[in.available()];
						in.read(mempic);
				        in.close();
				}else {
					MemberVO memVO = memSvc.getOneEmp(memNO);
					mempic = memVO.getMempic();
				}

				MemberVO memVO = new MemberVO();				
				memVO.setMemNo(memNO);
				memVO.setMemName(memname);
				memVO.setMemAccount(account);
				memVO.setMemPassword(password);
				memVO.setMemCreditCardId(creditCardId);
				memVO.setMemPhone(phone);
				memVO.setMemEmail(email);
				memVO.setMemAddress(address);
				memVO.setMemStatus(memStatus);
				memVO.setMempic(mempic);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/updatemem.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2..開始修改資料*****************************************/
				//MemberService memSvc = new MemberService();
				memVO = memSvc.updateM(memNO, memname, account, password,creditCardId,phone,email,address,memStatus,mempic);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = null;
				if ("update".equals(action))
					url = "/front-end/member/login.jsp";        
				else if ("update_B".equals(action))
					url = "/back-end/member//listAll.jsp";
				req.setAttribute("update", "新增成功");  //給前面的sweetalert用的
				RequestDispatcher successView = req.getRequestDispatcher(url); //修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/updatemem.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("list_Query".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				HttpSession session = req.getSession();
				Map<String,String[]> map = (Map<String,String[]>) session.getAttribute("map");
				if(req.getParameter("whichPage") ==null) {
					HashMap<String,String[]> map1 = new HashMap<String,String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				}
				
				MemberService memSvc = new MemberService();
				List<MemberVO> list = memSvc.getAll(map);
				
				req.setAttribute("list_Query",list);
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/list_Query.jsp"); // 成功轉交list_Query.jsp
				successView.forward(req, res);
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/Member_select.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
