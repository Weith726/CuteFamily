<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mr.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MrService mrSvc = new MrService();
    List<MrVO> list = mrSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>

<%@ include file="../head.jsp" %>

	
</head>
<body>
<%@ include file="../header.jsp" %>
	<!-- ****************************以下為實際功能頁變更區域**************************** -->
	 
	 <iframe src='listAllMr.jsp'  scrolling="no" frameborder="0" width="100%" height="60%"></iframe>





<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>