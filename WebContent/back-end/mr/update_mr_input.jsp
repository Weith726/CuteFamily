<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mr.model.*"%>

<%
  MrVO mrVO = (MrVO) request.getAttribute("mrVO"); //DocServlet.java (Concroller) 存入req的docVO物件 (包括幫忙取出的docVO, 也包括輸入資料錯誤時的docVO物件)
%>
<%= mrVO==null %>--${mrVO.apptno}-- 
<!DOCTYPE html>
<html>
<head>
	
</head>
<body>

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">紀錄管理</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"  target="_parent"><img src="images/back.png" width="32" height="32" border="0">返回管理主頁面</a></h6>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="mr.do" name="form1" enctype="multipart/form-data">
<table>
<!-- 	<tr> -->
<!-- 		<td>科別編號:</td> -->
<!-- 		<td><input type="TEXT" name="divno" size="45"  -->
<%-- 			 value="<%= (docVO==null)? "20" : docVO.getDivno()%>" /></td> --%>
<!-- 	</tr> -->

	<tr>
		<td>約診編號:</td>
		<td><input type="TEXT" name="apptno" size="60"
			 value="<%= (mrVO==null)? "20200727-000001" : mrVO.getApptno()%>" /></td>
			 <input type="hidden" name="docno" size="60"
			 value="DR04" />
			 
			 <input type="hidden" name="petno" size="60"
			 value="P0001" />
<!-- 		<input name="apptno" type="text" id="apptno" /> -->
<!-- 		<script type="text/javascript"> -->
<!-- 		window.onload = function(){ -->
<!--     		 var date = new Date(); -->
<!--      	document.getElementById("apptno").value = "GZ" + new Date().toLocaleString().match(/\d+/g).join("") + "0001"; -->
<!--  		}; -->
<!-- </script> -->
	</tr>
<!-- 	<tr> -->
<!-- 		<td>醫生編號</td> -->
<!-- 		<td><input type="TEXT" name="docno" size="60" -->
<%-- 			 value="<%= (mrVO==null)? "1001" : mrVO.getDocno()%>" /></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>會員寵物編號</td> -->
<!-- 		<td><input type="TEXT" name="petno" size="60" -->
<%-- 			 value="<%= (mrVO==null)? "P001" : mrVO.getPetno()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>診斷症狀</td>
		<td><textarea name="symptom" rows="5" cols="65"><%=(mrVO==null)? "診斷症狀" : mrVO.getSymptom()%></textarea></td> 
	</tr>
	<tr>
		<td>診斷處方</td>
		<td><textarea name="prescription" rows="5" cols="65"><%=(mrVO==null)? "診斷處方" : mrVO.getPrescription()%></textarea>
		</td> 
	</tr>
	
	<tr>
		<td>預約費用</td>
		<td><input type="TEXT" name="apptfee" size="60"
			 value="<%=(mrVO==null)? "" : mrVO.getApptfee()%>" /></td>
	</tr>
	
	<tr>
		<td>藥品費用</td>
		<td><input type="TEXT" name="medfee" size="60"
			 value="<%=(mrVO==null)? "" : mrVO.getMedfee()%>" /></td>
	</tr>
	
	<tr>
		<td>手術費用</td>
		<td><input type="TEXT" name="operfee" size="60"
			 value="<%=(mrVO==null)? "" : mrVO.getOperfee()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mrno" value="<%=mrVO.getMrno()%>">
<input type="submit" value="送出修改"></FORM>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>