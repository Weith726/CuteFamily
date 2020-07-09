<%@page import="com.doc.model.DocService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>







<html>
<head>

<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<style type="text/css">

.select{
/* border-style: solid; */
/*   border-color: red; */
  width:900px;

}
.select td{
/* border-style: solid; */
/*   border-color: red; */
}
.select th{
text-align: right;
/* border-style: solid; */
/*   border-color: red; */
}

/* form{ */
/* display:inline; */
/* margin:0px; */
/* } */

</style>


</head>

<body>




	<span class="mainTitle">預約管理</span>


	<hr class="mainTitlehr">



<%-- 	<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" /> --%>

<!-- 	<FORM METHOD="post" ACTION="appt.do"> -->

 
		
	<form  method="post" action="select_page.jsp" id ="passForm"> 
   <input id = 'test2' type = 'hidden' name="test2"> 
   </form>  
				
				


<% 
DocService docSvc = new DocService();
DocVO docVO = docSvc.getOneDoc("DR01");
pageContext.setAttribute("docVO", docVO);

%>				
<input type="hidden" id="inp" /><br/>
	<select id="divno" >
	<option value="ALL">請選擇
	<option value="D01">犬科</option>
	<option value="D02">貓科</option>
	<option value="D03">其他科</option>
	</select> 


	<select id="doc" >

	</select> 
				

		
<!-- 		<input type="hidden" name="action" value="listAppt">  -->
<!-- 		<input class="submit" type="submit" value="查詢"> -->
<!-- 	</FORM> -->
	



	







</body>
<script type="text/javascript">
var ttt =  document.getElementById("inp");
var divno =  document.getElementById("divno");
var str ='';
divno.onchange=function (){
inp.value=divno.value;
str = divno.value;
inp.addEventListener("input",changeValue(),false);
}
function changeValue(){
	$("#doc").html("");
	$("#doc").append("<option value='" +str+ "'>"+str+"</option>");
console.log(str);
}

</script>

</html>