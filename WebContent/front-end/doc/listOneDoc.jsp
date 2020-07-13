<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="com.div.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  DocVO docVO = (DocVO) request.getAttribute("docVO"); //DocServlet.java(Concroller), 存入req的docVO物件
%>

<%
  DivService divSvc = new DivService();
  DivVO divVO = divSvc.getOneDiv(docVO.getDivno());
%>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Fork this Card! #CodepenChallenge</title>
  <script src="https://kit.fontawesome.com/79c137b893.js"></script><link rel="stylesheet" href="./style3.css">

</head>
<body>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp">返回查詢主頁面</a></h6>
<div class="container">
<div class="card">

	
		<h2>${docVO.docname}</h2>${docVO.docno}
  <br>
  <div class="desc">${docVO.intro}</div>
 <img src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" height=200px width=200px; alt="meow">
    <div class="actions">
    <button class="actions__like">
            <%=docVO.getDivno()%><br><%=divVO.getDivname()%>
                 </button>
    <button class="actions__trade">診間號碼 <br>${docVO.roomno}</button>
    <button class="actions__cancel">年資<br>${docVO.seniority}年</button>
	
	</tr>
</table>

</div>
</div>

</div>
</div>
<!-- partial -->
</body>
<footer>

</footer>
</html>