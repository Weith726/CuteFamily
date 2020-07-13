<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>


	
</head>
<body>

	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

	<span class="mainTitle">門診預約查詢</span>

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
  <li><a href='listAllAppt.jsp'>預約一覽</a><br></li>


  <tr>
    <td style="text-align:left;"> 
  	<li>
    <FORM METHOD="post" ACTION="appt.do" >
        <b>輸入門診預約編號:</b>
        <input type="text" size="5" name="apptno" style="width:130px">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  	</li>
  </td>
  </tr>

  <jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<tr> 
 <td style="text-align:left;"> 
  <li>
     <FORM METHOD="post" ACTION="appt.do" >
       <b>選擇門診預約編號:</b>
       <select size="1" name="apptno" style="width:130px">
         <c:forEach var="apptVO" items="${apptSvc.all}" > 
          <option value="${apptVO.apptno}">${apptVO.apptno}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
   </td>
</tr>


</table>



<!-- ****************************以上為實際功能頁變更區域*************************** -->

<%@ include file="../footer.jsp" %>

</body>
</html>