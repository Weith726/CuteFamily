<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
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
	border-bottom: dotted;
	border-width: 1px;
	border-color: rgba(0, 0, 0, 0.5);
}

.mainTitle {
	letter-spacing: 8px;
	color: #42454C;
	font-weight: bold;
	font-size: 28px;
	padding-left: 20px;
}

/*主內容標題下分隔線*/
.mainTitlehr {
	border: 2px solid lightcoral;
}

img{
max-width:220px;

}

.addEmpBtn{
	margin-left:50px;
	font-size: 22px;
 	padding: 5px 20px;
 	background-color: #e7e7e7; 
 	color: black;
}
</style>

</head>
<body bgcolor='white'>

	<span class="mainTitle">員工管理</span>

<!-- 	<a href="select_page.jsp">回首頁</a> -->

	<input class="addEmpBtn" type="button" value="新增員工" onclick="location.href='addEmp.jsp'">



	<hr class="mainTitlehr">


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>性別</th>
			<th>生日</th>
			<th>職位</th>
			<th>電話</th>
			<th>地址</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>員工照片</th>
			<th>到職日</th>
			<th>離職日</th>
			<th>員工狀態</th>
			<th>修改</th>
			<th>刪除</th>

		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${empVO.empID}</td>
				<td>${empVO.empName}</td>
				<td>${empVO.empGender}</td>
				<%
					//<td>${empVO.hiredate}</td> 原本寫法， 下面改用JSTL
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
 				<td>${(empVO.empStatus =='1')?'在職中':(empVO.empStatus =='2')?'休假中':'已離職'}</td> 		
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>


			</tr>

		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>