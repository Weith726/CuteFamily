<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.opt.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    OptService optSvc = new OptService();
    List<OptVO> list = optSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<%@ include file="../head.jsp"%>

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

<%@ include file="../header.jsp"%>

<span class="mainTitle">���E�}��ɬq</span>

	<a href="select_page.jsp">��^�Z��޲z</a>

	<hr class="mainTitlehr">

<table>
	<tr>
		<th>�ɬq�s��</th>
		<th>��ͽs��</th>
		<th>���E���</th>
		<th>���E�ɬq</th>
		<th>�ثe�w����</th>
		<th>�̤j�w����</th>

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

<%@ include file="../footer.jsp"%>

</body>
</html>