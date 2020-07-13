<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.appt.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ApptVO apptVO = (ApptVO) request.getAttribute("apptVO"); 
%>

<!DOCTYPE html>
<html>
<head>

	
</head>
<body>

	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">門診預約查詢</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回查詢主頁面</a></h6>

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
	<tr>
		<td><%=apptVO.getApptno()%></td>
		<td><%=apptVO.getMemno()%></td>
		<td><%=apptVO.getSessionno()%></td>
		<td><%=apptVO.getSeqno()%></td>
		<td><%=apptVO.getSymdesc()%></td>
		<td>
			<img alt="" src="<%=request.getContextPath()%>/front-end/appt/ShowSymPhoto.do?apptno=${apptVO.apptno}" id="display">
		</td>
		<td><%=apptVO.getOptstate()%></td>
	
	</tr>
</table>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->

<%@ include file="../footer.jsp" %>

</body>
</html>