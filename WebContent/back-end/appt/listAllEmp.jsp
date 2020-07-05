<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ApptVO apptVO = (ApptVO) request.getAttribute("apptVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	
	
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
			<th>掛號號碼</th>
			<th>預約人姓名</th>
			<th>日期</th>
			<th>時段</th>
			<th>寵物症狀</th>
			<th>症狀圖片</th>
			<th>預約狀態</th>

		</tr>
		<tr>
			<td>${apptVO.seqno}</td>
			<td>${apptVO.memName}</td>
			
			<td><fmt:formatDate value="${apptVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${apptVO.optSession}</td>
			<td>${apptVO.empPhone}</td>
			<td>${apptVO.empAddress}</td>
			<td>${apptVO.empAcc}</td>
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
<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
			
		</tr>
	</table>
	
<!-- 		<input class="addEmpBtn" type="button" value="返回員工管理" onclick="location.href='listAllEmp.jsp'"> -->

</body>
</html>

