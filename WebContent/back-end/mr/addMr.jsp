<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mr.model.*"%>
<%@ page import="com.appt.model.*"%>

<%
  MrVO mrVO = (MrVO) request.getAttribute("mrVO");
%>
<%= mrVO==null %>--${mrVO.apptno}--  
<jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<jsp:useBean id="mrSvc" scope="page" class="com.mr.model.MrService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />



<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

<span class="mainTitle">紀錄管理</span>

	<hr class="mainTitlehr">

	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回管理主頁面</a></h6>
<h3>紀錄新增:</h3>

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

	<tr>
	
		<jsp:useBean id="apptVO" scope="page" class="com.appt.model.ApptVO" />
	<tr>
		<td>就診編號:<font color=red><b>*</b></font></td>
		<td><select size="1" name="apptno">
			<c:forEach var="apptVO" items="${apptSvc.all}">
				<option value="${apptVO.apptno}" ${(mrVO.apptno==apptVO.apptno)?'selected':'' } >${apptVO.apptno}</option>
			</c:forEach>
		</select></td>
	</tr>
	</tr>
	<tr>
		
	<tr>
			
<!-- 			 <td>時段編號:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="sessionno"> -->
<%-- 			<c:forEach var="apptVO" items="${apptSvc.all}"> --%>
			
<%-- 	          <option value=" ${apptVO.sessionno}"> ${apptVO.sessionno} </option> --%>
<%--                 </c:forEach> --%>
<!-- 		</select></td> -->
		
			<input type="hidden" name="docno" size="60"
			 value="DR04" />
			 
			 <input type="hidden" name="petno" size="60"
			 value="P0001" />
		
<!-- 		<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" /> -->
<!-- 		<td><select size="1" name="docno"> -->
<!-- 			<c:forEach var="docVO" items="${docSvc.all}"> -->
<!-- 				<option value="${docVO.docno}" ${(mrVO.docno==docVO.docno)?'selected':'' } >${docVO.docno}-${docVO.docname}</option> -->
<!-- 			</c:forEach> -->
<!-- 		</select></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>會員寵物編號</td> -->
<!-- 		<td> -->
<!-- 			<input type="TEXT" name="petno" size="60" -->
<%-- 			 value="<%= (mrVO==null)? "P0001" : mrVO.getPetno()%>" /> --%>
<!-- 		</td> -->
	</tr>
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
			 value="<%= (mrVO==null)? "150" : mrVO.getApptfee()%>" /></td>
	</tr>
	
	<tr>
		<td>藥品費用</td>
		<td><input type="TEXT" name="medfee" size="60"
			 value="<%= (mrVO==null)? "0" : mrVO.getMedfee()%>" /></td>
	</tr>
	
	<tr>
		<td>手術費用</td>
		<td><input type="TEXT" name="operfee" size="60"
			 value="<%= (mrVO==null)? "0" : mrVO.getOperfee()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>



