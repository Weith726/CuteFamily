<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../head.jsp" %>

	
</head>
<body>
<%@ include file="../header.jsp" %>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

	<span class="mainTitle">診療紀錄管理</span>

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
  <li><a href='listAllMr2.jsp'>紀錄一覽</a><br></li>


  <tr>
    <td style="text-align:left;"> 
  	<li>
    <FORM METHOD="post" ACTION="mr.do" >
        <b>輸入診療紀錄編號:</b>
        <input type="text" size="5" name="mrno" style="width:130px">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  	</li>
  </td>
  </tr>

  <jsp:useBean id="mrSvc" scope="page" class="com.mr.model.MrService" />
<tr> 
 <td style="text-align:left;"> 
  <li>
     <FORM METHOD="post" ACTION="mr.do" >
       <b>選擇診療紀錄編號:</b>
       <select size="1" name="mrno" style="width:130px">
         <c:forEach var="mrVO" items="${mrSvc.all}" > 
          <option value="${mrVO.mrno}">${mrVO.mrno}
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
  
</ul>
 </td>
</tr>

</table>

<br>
<h3>診療紀錄管理</h3>

<ul>
  <li><a href='addMr.jsp'>新增一筆診療記錄。</a></li>
</ul>

<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<%@ include file="../footer.jsp" %>


</div>

</div>


</body>
</html>