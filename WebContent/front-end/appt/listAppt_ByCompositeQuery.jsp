<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.appt.model.*"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listAppt_ByCompositeQuery" scope="request" type="java.util.List<ApptVO>" />

 <% 
 ApptService apptSvc = new ApptService();
 List<ApptVO> list  = listAppt_ByCompositeQuery;
 pageContext.setAttribute("list",list);
 
 %>

<html>
<head><title>看診進度 </title>
<meta http-equiv="refresh" content="10;URL=<%=request.getContextPath()%>/front-end/appt/queue.jsp">
</head>
<body bgcolor='white'>

<h1>

目前看診號${fn:length(listAppt_ByCompositeQuery)+1}




<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>員工編號</th> -->
<!-- 		<th>員工姓名</th> -->
<!-- 		<th>職位</th> -->
<!-- 		<th>雇用日期</th> -->
<!-- 		<th>薪水</th> -->
<!-- 		<th>獎金</th> -->
<!-- 		<th>部門</th> -->
<!-- 	</tr> -->
	
<%-- 	<c:forEach var="apptVO" items="${listAppt_ByCompositeQuery}"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${apptVO.apptno}</td> --%>
<%-- 			<td>${apptVO.memno}</td> --%>
<%-- 			<td>${apptVO.sessionno}</td> --%>
<%-- 			<td>${apptVO.seqno}</td> --%>
<%-- 			<td>${apptVO.symdesc}</td> --%>
<!-- 			<td> -->
<%-- 			<img alt="" src="<%=request.getContextPath()%>/front-end/appt/ShowSymPhoto.do?apptno=${apptVO.apptno}" id="display"> --%>
<!-- 			</td> -->
<%-- 			<td>${apptVO.optstate}</td> --%>
			
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->

</body>
</html>