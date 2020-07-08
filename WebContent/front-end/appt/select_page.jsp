<%@page import="com.doc.model.DocService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>







<html>
<head>


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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script type="text/javascript">
            function getDivi(categoryID)
            {
                var url="showDocs.jsp";
                jQuery.post(url, {categoryID:categoryID}, callbackHandler);
                
                
            }
//             function callbackHandler(data)
//             {
                
//                 jQuery("#ccc").html(data);
                
//             }
        </script>



</head>

<body>




	<span class="mainTitle">預約管理</span>


	<hr class="mainTitlehr">



<%-- 	<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" /> --%>

<!-- 	<FORM METHOD="post" ACTION="appt.do"> -->

 
		
	<form  method="post" action="select_page.jsp" id ="passForm"> 
   <input id = 'test2' type = 'hidden' name="test2"> 
   </form>  
				<b>選擇科別</b>
				<select size="1" id="divno" onchange='getDivi(this.value)'>
						<option value="-1">請選擇
						<option value="DR01">犬科
						<option value="DR02">貓科
						<option value="DR03">其他科
				</select>
				


<% 
DocService docSvc = new DocService();
DocVO docVO = docSvc.getOneDoc("DR01");
pageContext.setAttribute("docVO", docVO);

%>				
				<b>選擇獸醫:</b> <select size="1" name="docNo">
				<option value="">請選擇
<%-- 			<c:forEach var="docVO" items="${docSvc.oneDoc[DR01]}"> --%>
				<option value="${docVO.docno}">${docVO.docname}
<%-- 			</c:forEach> --%>
		</select> 
<!-- 		<input type="hidden" name="action" value="getOne_For_Display"> -->
				
				

		
<!-- 		<input type="hidden" name="action" value="listAppt">  -->
<!-- 		<input class="submit" type="submit" value="查詢"> -->
<!-- 	</FORM> -->
	
	<script type="text/javascript">
//    function aa(){
//     document.getElementsByName('o').value=document.getElementById('s').value;
//     }
</script>

<select id="s" onchange="aa()">
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
</select>

<select size="1" name="divno">
<!-- 						<option value="-1" name="o"> -->
<!-- 						<option value="D01" name="o"> -->
<!-- 						<option value="D02" name="o"> -->
<!-- 						<option value="D03" >	 -->
<%-- 						<c:forEach var="optVO" items="${docSvc.all}"> --%> --%>
<%-- 				<option value="${docVO.docNo}">${optVO.docNo} --%>
<%-- 			</c:forEach> --%>
<!-- 				</select> -->

	







</body>


</html>