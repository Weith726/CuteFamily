<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.opt.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    OptService optSvc = new OptService();
    List<OptVO> list = optSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 90%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>門診時段總列表</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>時段編號</th>
		<th>醫生編號</th>
		<th>門診日期</th>
		<th>門診時段</th>
		<th>目前預約數</th>
		<th>最大預約數</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="optVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${optVO.sessionNo}</td>
			<td>${optVO.docNo}</td>		
			<td><fmt:formatDate value="${optVO.optDate}" pattern="yyyy/MM/dd"/></td>

			<td>${optVO.optSession}</td>
			<td>${optVO.currentCount}</td> 
			<td>${optVO.maximum}</td> 


		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>