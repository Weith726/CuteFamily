<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appt.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<jsp:useBean id="listAppt" scope="request" type="java.util.List<ApptVO>" />



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

td{
text-align: center;
 vertical-align: middle;
}

.seqno{
font-size:28px;
color:red;
font-weight:bold;
}

img {
	max-width: 150px;
}
</style>

</head>
<body bgcolor='white'>


	<span class="mainTitle">查詢結果</span>


	<hr class="mainTitlehr">
	
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
<!-- 			<th>員工編號</th> -->
			<th>掛號號碼</th>
			<th>預約人姓名</th>
			<th>預約獸醫</th>
			<th>日期</th>
			<th>時段</th>
			<th>寵物名稱</th>
			<th>寵物症狀</th>
			<th>症狀圖片</th>
			<th>狀態</th>
			<th>確認</th>

		</tr>
		<c:forEach var="apptVO" items="${listAppt}">
		<tr>
		<jsp:useBean id="petSvc" scope="page" class="com.MemberPet.model.MemberPetService" />
			<td class="seqno">${apptVO.seqno}</td>
			<td>${apptVO.memName}</td>
			<td>${apptVO.docname}</td>
			
			<td><fmt:formatDate value="${apptVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${apptVO.optSession}</td>
			<td>
			<c:forEach var="petVO" items="${petSvc.all}">
									<c:if test="${apptVO.petNo==petVO.petNo}">
	                    ${petVO.petName}<br>(${apptVO.petNo})
                    </c:if>
								</c:forEach></td>
			<td>${apptVO.symdesc}</td>
			<td><img
					src="<%= request.getContextPath()%>/back-end/appt/img.do?apptno=${apptVO.apptno}"></td>
			<td>${(apptVO.optstate =='0')?'<font color="goldenrod">未看診':(apptVO.optstate =='1')?'<font color="green">已看診':'<font color="red">已取消'}</td>
			<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/appt/appt.do"
						style="margin-bottom: 0px;">
						<c:if test="${apptVO.optstate =='0'}">
						<button type="submit" class="btn btn-success">看診完畢</button>
                    	</c:if>
                    	<c:if test="${apptVO.optstate !='0'}">
	                    <button type="submit" class="btn btn-success" disabled>看診完畢</button>
                    	</c:if>
						
						<input type="hidden" name="apptno" value="${apptVO.apptno}"> 
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
					</FORM></td>
			
			
		</tr>
		</c:forEach>
	</table>
<!-- 		<input class="addEmpBtn" type="button" value="返回員工管理" onclick="location.href='listAllEmp.jsp'"> -->

</body>
</html>

