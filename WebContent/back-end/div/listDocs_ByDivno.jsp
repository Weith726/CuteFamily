<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="com.div.model.*"%>

<jsp:useBean id="listDocs_ByDivno" scope="request" type="java.util.Set<DocVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />

<html>
<head><title>部門員工 - listEmps_ByDeptno.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>
<table id="table-2">
	<tr><td>
		 <h3>按科別查詢醫師 </h3>
		 
		 <h4><a href="<%=request.getContextPath()%>/back-end/doc/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>
<table>
		
</table>

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
		<th>科別</th>
		<th>醫生編號 </th>
		<th>醫生姓名</th>
		<th>診間號碼</th>
		<th>年資</th>
		<th>介紹</th>
		<th>醫生照片</th>
		<th>醫生在職狀態</th>
		<th>修改</th>
	</tr>
	
	<c:forEach var="docVO" items="${listDocs_ByDivno}" >
		<tr>
			<td><c:forEach var="divVO" items="${divSvc.all}">
                    <c:if test="${docVO.divno==divVO.divno}">
	                    ${divVO.divno}${divVO.divname}</font>
                    </c:if>
                </c:forEach>
			</td>
			<td>${docVO.docno}</td>
			<td>${docVO.docname}</td>
			<td>${docVO.roomno}</td>
			<td>${docVO.seniority}</td>
			<td>${docVO.intro}</td> 
			<td>
			<img alt="" src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" id="display">
			</td>
			<td>${docVO.docstatus}</td>	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/doc/doc.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="docno"  value="${docVO.docno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>		
		
		</tr>
	</c:forEach>
</table>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>

</body>
</html>