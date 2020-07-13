<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mr.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MrService mrSvc = new MrService();
    List<MrVO> list = mrSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../head.jsp" %>
	<title>Table V04</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<%@ include file="page1.file" %> 
<body>
	<%@ include file="../header.jsp" %>
	<span class="mainTitle">診療紀錄管理</span>

	<hr class="mainTitlehr">
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">

				<div class="table100 ver5 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">紀錄編號</th>
									<th class="cell100 column2">約診編號</th>
									<th class="cell100 column3">醫生編號</th>
									<th class="cell100 column4">寵物編號</th>
									<th class="cell100 column5">診斷症狀</th>
									<th class="cell100 column6">診斷處方</th>
									<th class="cell100 column7">預約費用</th>
									<th class="cell100 column8">藥品費用</th>
									<th class="cell100 column9">手術費用</th>
									<th class="cell100 column10"></th>
									
									
								</tr>
							</thead>
						</table>
					</div>

					<div class="table100-body js-pscroll">
						<table>
							<tbody>
						
	<c:forEach var="mrVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<tr class="row100 body">
									<td class="cell100 column1">${mrVO.mrno}</td>
									<td class="cell100 column2">${mrVO.apptno}</td>
									<td class="cell100 column3">${mrVO.docno}
<%-- 							<c:forEach var="apptVO" items="${apptSvc.all}"> --%>
<%-- 							<c:forEach var="optVO" items="${optSvc.all}"> --%>
<%--             				<c:if test="${(mrVO.apptno==apptVO.apptno)&&(apptVO.sessionno==optVO.sessionNo)}"> --%>
<%-- 	           				${optVO.docNo}<br> --%>
<%--                   			  </c:if> --%>
<%--                 			</c:forEach> --%>
<%--                 			</c:forEach> --%>
                			</td>
									<td class="cell100 column4"><c:forEach var="apptVO" items="${apptSvc.all}">
            				<c:if test="${mrVO.apptno==apptVO.apptno}">
	           				${apptVO.petno}<br>
                  			  </c:if>
                			</c:forEach></td>
									<td class="cell100 column5">${mrVO.symptom}</td>
									<td class="cell100 column6">${mrVO.prescription}</td>
									<td class="cell100 column7">${mrVO.apptfee}</td>
									<td class="cell100 column8">${mrVO.medfee}</td>
									<td class="cell100 column9">${mrVO.operfee}</td>
									<td class="cell100 column10"> 
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mr/mr.do" style="margin-bottom: 0px;">
			    					<input type="image" src="images/icons/edit.jpg" width="20" height="20">
			     					<input type="hidden" name="mrno"  value="${mrVO.mrno}">
			     					<input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>
									
								</tr>
								</c:forEach>
						
<!-- 							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
			
		
	</script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>