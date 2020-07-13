<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    DocService docSvc = new DocService();
    List<DocVO> list = docSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<!DOCTYPE html>
<html>
<head>
	
</head>
<body>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

	<%@ include file="page1A.file" %> 
	



<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>