<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	
	
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
/*主內容標題下分隔線*/
.mainTitlehr {
	border: 2px solid lightcoral;
}

img{
max-width:220px;

}
</style>

</head>
<body bgcolor='white'>


	<span class="mainTitle">員工資料</span>


	<hr class="mainTitlehr">

	<table>
		<tr>
<!-- 			<th>員工編號</th> -->
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
		<tr>
<%-- 			<td>${empVO.empID}</td> --%>
			<td>${empVO.empName}</td>
			<td>${empVO.empGender}</td>
			
		    <% // <td>${empVO.empBirth}</td> 原本寫法， 下面改用JSTL %>
			
			<td><fmt:formatDate value="${empVO.empBirth}"
					pattern="yyyy/MM/dd" /></td>

			<td>${empVO.empJob}</td>
			<td>${empVO.empPhone}</td>
			<td>${empVO.empAddress}</td>
			<td>${empVO.empAcc}</td>
			<td>${empVO.empPwd}</td>
			<td><img src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td>
			<td><fmt:formatDate value="${empVO.hiredate}"
					pattern="yyyy/MM/dd" /></td>
			<td><fmt:formatDate value="${empVO.quitdate}"
					pattern="yyyy/MM/dd" /></td>
			<td>${empVO.empStatus}</td>
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
	</table>
	
		<input class="addEmpBtn" type="button" value="返回員工管理" onclick="location.href='listAllEmp.jsp'">

</body>
</html>

