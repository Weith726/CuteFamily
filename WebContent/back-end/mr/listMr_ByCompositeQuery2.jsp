<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.mr.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listMr_ByCompositeQuery" scope="request" type="java.util.List<MrVO>" />
<jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />


<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../head.jsp" %>
	<title>Table V02</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="main.css">
<!--===============================================================================================-->
</head>
<body>
<%@ include file="../header.jsp" %>
<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
					<div class="table">
						<div class="row header">
							<div class="cell">
								診療紀錄編號
							</div>
							<div class="cell">
								約診編號
							</div>
							<div class="cell">
								醫生編號
							</div>
							<div class="cell">
								會員寵物編號
							</div>
							<div class="cell">
								診斷症狀
							</div>
							<div class="cell">
								診斷處方
							</div>
							<div class="cell">
								預約費用
							</div>
							<div class="cell">
								藥品費用
							</div>
							<div class="cell">
								手術費用
							</div>
							<div class="cell">
								修改
							</div>
							
						</div>	
	<c:forEach var="mrVO" items="${listMr_ByCompositeQuery}">
		
		<div class="row">
						
							<div class="cell" data-title="Full Name">
							${mrVO.mrno}
							</div>
							<div class="cell" data-title="Age">
							${mrVO.apptno}
							</div>
							<div class="cell" data-title="Job Title">
<%-- 							${mrVO.docno} --%>
							
							<c:forEach var="apptVO" items="${apptSvc.all}">
							<c:forEach var="optVO" items="${optSvc.all}">
            				<c:if test="${(mrVO.apptno==apptVO.apptno)&&(apptVO.sessionno==optVO.sessionNo)}">
	           				${optVO.docNo}<br>
                  			  </c:if>
                			</c:forEach>
                			</c:forEach>
							</div>
							
							<div class="cell" data-title="Location">
							<c:forEach var="apptVO" items="${apptSvc.all}">
            				<c:if test="${mrVO.apptno==apptVO.apptno}">
	           				${apptVO.memno}<br>
                  			  </c:if>
                			</c:forEach>
							</div>
							<div class="cell" data-title="Location">
							${mrVO.symptom}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.prescription}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.apptfee}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.medfee}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.operfee}
							</div>
							<div class="cell" data-title="Location">
							 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mr/mr.do" style="margin-bottom: 0px;">
			     <input type="image" src="images/icons/edit.jpg" width="20" height="20">
			     <input type="hidden" name="mrno"  value="${mrVO.mrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
							</div>
							
						</div></c:forEach>
			</div>
		
			</div>
		</div>
	</div><h7><a href="select_page.jsp" style="color:#3C3C3C;"><img src="images/icons/home.ico" width="20" height="20" border="0">返回主頁面</a></h7>



	

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
<%@ include file="../footer.jsp" %>
</body>
</html>