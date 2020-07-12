<%@page import="com.doc.model.DocService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>


<% 

DocService docSvc = new DocService();
List<DocVO> d01 = docSvc.getAllByDiv("D01");
Gson gson = new Gson();
String jsonStr1 = gson.toJson(d01);
List<DocVO> d02 = docSvc.getAllByDiv("D02");
String jsonStr2 = gson.toJson(d02);
List<DocVO> d03 = docSvc.getAllByDiv("D03");
String jsonStr3 = gson.toJson(d03);

%>

<html>
<head>
<%@ include file="../head.jsp"%>

<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<style type="text/css">
.select {
	/* border-style: solid; */
	/*   border-color: red; */
	width: 900px;
}

.select td {
	/* border-style: solid; */
	/*   border-color: red; */
	
}

.select th {
	text-align: right;
	/* border-style: solid; */
	/*   border-color: red; */
}

/* form{ */
/* display:inline; */
/* margin:0px; */
/* } */
.divno{

width: 300px;
font-size:28px;
}
.doc{
width: 300px;
font-size:28px;
}

.submit{
font-size:28px;
}

.sel{
font-size:28px;
}

.allsel{
margin:0px auto;

}

</style>



</head>

<body>

<%@ include file="../header.jsp"%>


	<span class="mainTitle">預約</span>


	<hr class="mainTitlehr">

<FORM METHOD="post" ACTION="dispOpt.jsp">


<div class="allsel">

<div class="sel">科別<br>
	<select id="divno" class="divno">
		<option value="ALL" selected>請選擇
		<option value="D01">犬科</option>
		<option value="D02">貓科</option>
		<option value="D03">其他科</option>
	</select></div>

<div class="sel">醫生<br>
	<select id="doc" class="doc" name="doc">


	</select></div>
	

<!-- <input type="hidden" name="action" value="displayOpt">  -->
		<input class="submit" type="submit" value="查詢">
	</FORM>

</div>
<%@ include file="../footer.jsp"%>
</body>
<script type="text/javascript">
$(function() {
	$( "#divno" ).trigger( "onchange" );
});
	


	//json轉成 JS json陣列
	var json1 = '<%=jsonStr1%>'
	var json2 = '<%=jsonStr2%>'
	var json3 = '<%=jsonStr3%>'
	
	var divno =  document.getElementById("divno");
	var str ='';
	//option發生改變
	
	divno.onchange=function (){
			str = divno.value;
			if(str==='D01'){
				var obj = JSON.parse(json1);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			if(str==='D02'){
				var obj = JSON.parse(json2);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			
			if(str==='D03'){
				var obj = JSON.parse(json3);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			if(str==="ALL"){
				$('#doc').html("");
			}

	}



</script>

</html>