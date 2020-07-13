<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mr.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
MrVO mrVO = (MrVO) request.getAttribute("mrVO");//DocServlet.java(Concroller), 存入req的docVO物件
%>

<jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />

<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">醫師檢索</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回管理主頁面</a></h6>

<table>
	<tr>
		<th>就診紀錄編號</th>
		<th>約診編號</th>
		<th>醫生編號</th>
		<th>會員寵物編號</th>
		<th>診斷症狀</th>
		<th>診斷處方</th>
		<th>預約費用</th>
		<th>藥品費用</th>
		<th>手術費用</th>
		<th>修改</th>

	</tr>
	<tr>
		<td>${mrVO.mrno}</td>
			<td>${mrVO.apptno}</td>
			<td>
			<c:forEach var="apptVO" items="${apptSvc.all}">
			<c:forEach var="optVO" items="${optSvc.all}">
            <c:if test="${(mrVO.apptno==apptVO.apptno)&&(apptVO.sessionno==optVO.sessionNo)}">
	        ${optVO.docNo}<br>
            </c:if>
            </c:forEach>
            </c:forEach></td>
			<td><c:forEach var="apptVO" items="${apptSvc.all}">
            				<c:if test="${mrVO.apptno==apptVO.apptno}">
	           				${apptVO.memno}<br>
                  			  </c:if>
                			</c:forEach></td>
			<td>${mrVO.symptom}</td>
			<td>${mrVO.prescription}</td>
			<td>${mrVO.apptfee}</td> 
			<td>${mrVO.medfee}</td>  
			<td>${mrVO.operfee}</td> 
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mr/mr.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mrno"  value="${mrVO.mrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
	</tr>
</table>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->

<%@ include file="../footer.jsp" %>

</body>
</html>