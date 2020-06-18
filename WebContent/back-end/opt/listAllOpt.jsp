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
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���E�ɬq�`�C��</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

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

</body>
</html>