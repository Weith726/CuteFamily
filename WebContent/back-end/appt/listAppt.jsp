<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	ApptVO apptVO = (ApptVO) request.getAttribute("apptVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	
	
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

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
}

.mainTitle {
	letter-spacing: 8px;
	color: #42454C;
	font-weight: bold;
	font-size: 28px;
	padding-left: 20px;
}
/*�D���e���D�U���j�u*/
.mainTitlehr {
	border: 2px solid lightcoral;
}

img{
max-width:220px;

}
</style>

</head>
<body bgcolor='white'>


	<span class="mainTitle">���u���</span>


	<hr class="mainTitlehr">

	<table>
		<tr>
<!-- 			<th>���u�s��</th> -->
			<th>�������X</th>
			<th>�w���H�m�W</th>
			<th>���</th>
			<th>�ɬq</th>
			<th>�d���g��</th>
			<th>�g���Ϥ�</th>
			<th>�w�����A</th>

		</tr>
		<tr>
			<td>${apptVO.seqno}</td>
			<td>${apptVO.memName}</td>
			
			<td><fmt:formatDate value="${apptVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${apptVO.optSession}</td>
			<td>${apptVO.symdesc}</td>
			<td>${apptVO.symphoto}</td>
			<td>${apptVO.optstate}</td>
<%-- 			<td><img src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td> --%>
<%-- 			<td><fmt:formatDate value="${empVO.hiredate}" --%>
<%-- 					pattern="yyyy/MM/dd" /></td> --%>
<%-- 			<td><fmt:formatDate value="${empVO.quitdate}" --%>
<%-- 					pattern="yyyy/MM/dd" /></td> --%>
<%-- 			<td>${empVO.empStatus}</td> --%>
<!-- 			<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="�ק�"> <input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="�R��"> <input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			
		</tr>
	</table>
	
<!-- 		<input class="addEmpBtn" type="button" value="��^���u�޲z" onclick="location.href='listAllEmp.jsp'"> -->

</body>
</html>

