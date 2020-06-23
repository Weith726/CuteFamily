<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%@ include file="../head.jsp"%>

</head>
<body>
<%@ include file="../header.jsp"%>

<span class="mainTitle">班表管理</span>

	<!-- 	<a href="select_page.jsp">回首頁</a> -->


	<hr class="mainTitlehr">



  <jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />
<h3>依日期查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li>
  
  



   
  
  
  <li>
     <FORM METHOD="post" ACTION="opt.do" >
       <b>選擇員工姓名:</b>
       <select size="1" name="sessionNo">
         <c:forEach var="optVO" items="${optSvc.all}" > 
          <option value="${optVO.sessionNo}">${optVO.sessionNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>




<ul>
  <li><a href='addOptSession.jsp'>開始排班</a></li>
</ul>
<%@ include file="../footer.jsp"%>
</body>
</html>