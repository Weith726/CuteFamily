<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
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

	<span class="mainTitle">���u�b���޲z</span>

	<!-- 	<a href="select_page.jsp">�^����</a> -->


	<hr class="mainTitlehr">


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>���u�s��</th>
			<th>���u�m�W</th>
			<th>�ʧO</th>
			<th>�ͤ�</th>
			<th>¾��</th>
			<th>�q��</th>
			<th>�a�}</th>
			<th>���u�b��</th>
			<th>���u�K�X</th>
			<th>���u�Ӥ�</th>
			<th>��¾��</th>
			<th>��¾��</th>
			<th>���u���A</th>
			<th>�ק�</th>
			<th>�R��</th>

		</tr>

		<%@ include file="page1.file"%>
		<input class="addEmpBtn" type="button" value="�s�W���u"
			onclick="location.href='addEmp.jsp'">
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${empVO.empID}</td>
				<td>${empVO.empName}</td>
				<td>${empVO.empGender}</td>
				<%
					//<td>${empVO.hiredate}</td> �쥻�g�k�A �U�����JSTL
				%>
				<td><fmt:formatDate value="${empVO.empBirth}"
						pattern="yyyy/MM/dd" /></td>

				<td>${empVO.empJob}</td>
				<td>${empVO.empPhone}</td>
				<td>${empVO.empAddress}</td>
				<td>${empVO.empAcc}</td>
				<td>${empVO.empPwd}</td>
				<td><img
					src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td>
				<td>${empVO.hiredate}</td>
				<td>${empVO.quitdate}</td>
				<td>${(empVO.empStatus =='1')?'�b¾��':(empVO.empStatus =='2')?'�𰲤�':'�w��¾'}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post" id="formDel" onsubmit=" return confirmDel();"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="delete" id="delete">
					</FORM>
				</td>


			</tr>

		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
	
	//�R���e���T�{
		function confirmDel() {	

			var txt;
			var r = confirm("�T�w�R��?");
			if (r == true) {
				return true;
			} else {
// 				document.getElementById("formDel").value = "";//�M�Ť奻��  
				return false;
			}

		};
	</script>

</body>
</html>