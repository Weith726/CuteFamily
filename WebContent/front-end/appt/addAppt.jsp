<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.opt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	OptVO optVO = (OptVO) request.getAttribute("optVO"); //EmpServlet.java(Concroller), 存入req的optVO物件
%>

<html>
<head>


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

img {
	max-width: 100px;
}

.addEmpBtn {
	float: right;
	font-size: 22px;
	padding: 5px 20px;
	background-color: #e7e7e7;
	color: black;
	margin-bottom: 20px;
}
</style>


</head>
<body>




	<span class="mainTitle">班表管理</span>
	
	<a href="select_page.jsp">返回主頁</a>


	<hr class="mainTitlehr">

	<table>
		<tr>
			<!-- 			<th>員工編號</th> -->
			<th>時段編號</th>
			<th>醫生編號</th>
			<th>門診日期</th>
			<th>門診時段</th>
			<th>目前預約數</th>
			<th>最大預約數</th>
		</tr>
		<tr>

			<td>${optVO.sessionNo}</td>
			<td>${optVO.docNo}</td>
			<td><fmt:formatDate value="${optVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${optVO.optSession}</td>
			<td>${optVO.currentCount}</td>
			<td>${optVO.maximum}</td>
			

		</tr>
	</table>


		


</body>
</html>

