<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Blog Cards</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,700" rel="stylesheet">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css'><link rel="stylesheet" href="./style2.css">
 <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
  <jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />
</head>
<body>
<!-- partial:index.partial.html -->
<div class="blog-card">
    <div class="meta">
      <div class="photo" style="background-image: url(images/Doctor.png"></div>
      <ul class="details">
        <li class="author">Doctor</li>
        
        
        </li>
      </ul>
    </div>
    <div class="description">
      <h1>醫師查詢</h1>
      <h2>選擇醫師編號與姓名以查詢詳細</h2>
      <FORM METHOD="post" ACTION="doc.do" >
       <b></b>
       <select size="1" name="docno" style="width:130px">
         <c:forEach var="docVO" items="${docSvc.all}" > 
          <option value="${docVO.docno}">${docVO.docno}-${docVO.docname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM><br><br>
    <h1>醫師一覽</h1>
    <h2><a href='listAllDoc.jsp' style="text-decoration:none;">點此瀏覽全部醫師資訊</a></h2>
    </div>
  </div>
  <div class="blog-card alt">
    <div class="meta">
      <div class="photo" style="background-image: url(images/pet.png)"></div>
      <ul class="details">
        <li class="date">Department</a></li>
        
       
      </ul>
    </div>
    <div class="description">
      <h1>各科醫師</h1>
      <h2>選擇科別以查詢指定科別醫師</h2>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/div/div.do" >
       <b><font></font></b>
       <select size="1" name="divno">
         <c:forEach var="divVO" items="${divSvc.all}" > 
          <option value="${divVO.divno}">${divVO.divname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listDocs_ByDivno_C">
     </FORM>
    </div>
  </div>
<!-- partial -->
  
</body>
</html>

