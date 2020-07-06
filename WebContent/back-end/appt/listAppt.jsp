<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appt.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listAppt" scope="request" type="java.util.List<ApptVO>" />
<%-- <jsp:useBean id="DeptSvc" scope="page" class="com.dept.model.DeptService" /> --%>


<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
}

table, th, td {
	text-align: center;
}

th {
	padding: 5px;
	background-color: lightcoral;
}

th, td {
	padding: 5px;
	border-bottom: dotted;
	border-width: 1px;
	border-color: rgba(0, 0, 0, 0.5);
}

.seqno{
font-size:28px;
color:red;
}

img {
	max-width: 150px;
}
</style>

</head>
<body bgcolor='white'>


	<span class="mainTitle">查詢結果</span>


	<hr class="mainTitlehr">

	<table>
		<tr>
<!-- 			<th>員工編號</th> -->
			<th>掛號號碼</th>
			<th>預約人姓名</th>
			<th>預約獸醫</th>
			<th>日期</th>
			<th>時段</th>
			<th>寵物症狀</th>
			<th>症狀圖片</th>
			<th>預約狀態</th>

		</tr>
		<c:forEach var="apptVO" items="${listAppt}">
		<tr>
		
			<td class="seqno">${apptVO.seqno}</td>
			<td>${apptVO.memName}</td>
			<td>${apptVO.docname}</td>
			
			<td><fmt:formatDate value="${apptVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${apptVO.optSession}</td>
			<td>${apptVO.symdesc}</td>
			<td><img
					src="<%= request.getContextPath()%>/back-end/appt/img.do?apptno=${apptVO.apptno}"></td>
			<td>${apptVO.optstate}</td>
			
			
		</tr>
		</c:forEach>
	</table>
	<h1>${apptVO.apptno}</h1>
<!-- 		<input class="addEmpBtn" type="button" value="返回員工管理" onclick="location.href='listAllEmp.jsp'"> -->

</body>
</html>

