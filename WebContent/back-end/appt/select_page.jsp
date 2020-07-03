<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appt.model.*"%>






<html>
<head>
<%@ include file="../head.jsp"%>





</head>

<body>
	<%@ include file="../header.jsp"%>

	<span class="mainTitle">預約管理</span>


	<hr class="mainTitlehr">



	<FORM METHOD="post" ACTION="appt.do">
		<tr>
			<th>日期:</th>
			<td><input name="empBirth" id="f_date1" type="text" autocomplete="off" 
			value="<%=(empVO == null) ? "" : ((empVO.getEmpBirth() == null) ? "" : empVO.getEmpBirth())%>" /></td>
		</tr>
	</FORM>







	<%@ include file="../footer.jsp"%>

</body>



</html>