<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>


	
</head>
<body>

	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

	<span class="mainTitle">前台醫師查詢</span>

	<hr class="mainTitlehr">

	
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
<h3>資料查詢</h3>

<table>
  <li><a href='listAllDoc.jsp'>醫師一覽</a><br></li>


  <tr>
    <td style="text-align:left;"> 
  	<li>
    <FORM METHOD="post" ACTION="doc.do" >
        <b>輸入醫師編號:</b>
        <input type="text" size="5" name="docno" style="width:130px">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  	</li>
  </td>
  </tr>

  <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
  <jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />
<tr> 
 <td style="text-align:left;"> 
  <li>
     <FORM METHOD="post" ACTION="doc.do" >
       <b>選擇醫師編號:</b>
       <select size="1" name="docno" style="width:130px">
         <c:forEach var="docVO" items="${docSvc.all}" > 
          <option value="${docVO.docno}">${docVO.docno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
   </td>
</tr>
<tr> 
 <td style="text-align:left;"> 
  <li>
     <FORM METHOD="post" ACTION="doc.do" >
       <b>選擇醫師姓名:</b>
       <select style="width:130px" name="docno">
         <c:forEach var="docVO" items="${docSvc.all}" > 
          <option value="${docVO.docno}">${docVO.docname}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>
 </td>
</tr>

<li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/div/div.do" >
       <b><font>選擇科別:</font></b>
       <select size="1" name="divno">
         <c:forEach var="divVO" items="${divSvc.all}" > 
          <option value="${divVO.divno}">${divVO.divname}
         </c:forEach>   
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="listDocs_ByDivno_C">
     </FORM>
  </li>

</table>



<!-- ****************************以上為實際功能頁變更區域*************************** -->

<%@ include file="../footer.jsp" %>

</body>
</html>