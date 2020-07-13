<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appt.model.*"%>
<%
    ApptService apptSvc = new ApptService();
    List<ApptVO> list = apptSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">所有門診預約</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回查詢主頁面</a></h6>


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
		<th>約診編號</th>
		<th>會員編號</th>
		<th>時段編號</th>
		<th>看診號碼</th>
		<th>寵物症狀描述</th>
		<th>症狀圖片</th>
		<th>看診狀態</th>

	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="apptVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${apptVO.apptno}</td>
			<td>${apptVO.memno}</td>
			<td>${apptVO.sessionno}</td>
			<td>${apptVO.seqno}</td>
			<td>${apptVO.symdesc}</td>
			<td>
			<img alt="" src="<%=request.getContextPath()%>/front-end/appt/ShowSymPhoto.do?apptno=${apptVO.apptno}" id="display">
			</td>
			<td>${apptVO.optstate}</td>
			<td> 
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消">
			     <input type="hidden" name="apptno"  value="${apptVO.apptno}">
			     <input type="hidden" name="action" value="cancel"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>