<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appt.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listAppt" scope="request" type="java.util.List<ApptVO>" />
<%-- <jsp:useBean id="DeptSvc" scope="page" class="com.dept.model.DeptService" /> --%>


<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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


	<span class="mainTitle">�d�ߵ��G</span>


	<hr class="mainTitlehr">

	<table>
		<tr>
<!-- 			<th>���u�s��</th> -->
			<th>�������X</th>
			<th>�w���H�m�W</th>
			<th>�w���~��</th>
			<th>���</th>
			<th>�ɬq</th>
			<th>�d���g��</th>
			<th>�g���Ϥ�</th>
			<th>�w�����A</th>

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
<!-- 		<input class="addEmpBtn" type="button" value="��^���u�޲z" onclick="location.href='listAllEmp.jsp'"> -->

</body>
</html>

