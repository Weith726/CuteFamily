<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>
<%
    DocService docSvc = new DocService();
    List<DocVO> list = docSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Fork this Card! #CodepenChallenge</title>
  <script src="https://kit.fontawesome.com/79c137b893.js"></script><link rel="stylesheet" href="./style3.css">

</head>
<body>
<%@ include file="page1.file" %> <h6><a href="<%=request.getContextPath()%>/front-end/doc/select_page.jsp">返回查詢主頁面</a></h6>
	<c:forEach var="docVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
<!-- partial:index.partial.html -->

<div class="container">
<div class="card">

  <h2>${docVO.docname}</h2>${docVO.docno}
  <br>
  <div class="desc">${docVO.intro}</div>
 <img src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" height=200px width=200px; alt="meow">
    <div class="actions">
    <button class="actions__like">
     <c:forEach var="divVO" items="${divSvc.all}">
            <c:if test="${docVO.divno==divVO.divno}">
	           ${divVO.divname}<br>${divVO.divno}
                    </c:if>
                </c:forEach>  </button>
    <button class="actions__trade">診間號碼 <br>${docVO.roomno}</button>
    <button class="actions__cancel">年資<br>${docVO.seniority}年</button>
  </div>
</div>
</c:forEach>
</div>
</div>
<!-- partial -->
</body>
<footer>
<%@ include file="page2.file" %>
</footer>
</html>

