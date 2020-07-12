<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.opt.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	OptVO optVO = (OptVO) request.getAttribute("optVO"); //存入req的optVO物件

%>
<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
<jsp:useBean id="petSvc" scope="page"
	class="com.MemberPet.model.MemberPetService" />
<html>
<head>
<%@ include file="../head.jsp"%>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
	font-size: 24px;
}

table, th, td {
	text-align: center;
}

th {
	padding: 5px;
	background-color: lightcoral;
}

th, td {
	padding: 5px;
	border-bottom: dotted;
	border-width: 1px;
	border-color: rgba(0, 0, 0, 0.5);
}

img {
	max-width: 100px;
}

.mainTitle {
	font-size: 32px;
}

.memTable th {
	width: 300px;
}

.memTable td * {
	float: left;
	font-size: 24px;
}

.submit {
	/* margin-left:45%; */
	font-size: 24px;
}

.main {
	width: 80%;
	margin: 0 auto;
}
</style>


</head>
<body>

	<%@ include file="../header.jsp"%>

	<div class="main">
		<span class="mainTitle">掛號預約內容</span>

		<!-- 	<a href="dispOpt.jsp">重新選擇</a> -->


		<hr class="mainTitlehr">

		<!-- 	<div>時段編號</div> -->
		<!-- 	<div>醫生編號</div> -->
		<!-- 	<div>門診日期</div> -->
		<!-- 	<div>門診時段</div> -->
		<!-- 	<div>預約</div> -->
		<!-- 	<div></div> -->
		

			<table>
				<tr>

					<!-- <th>時段編號</th> -->
					<th>門診日期</th>
					<th>門診時段</th>
					<th>科別</th>
					<th>獸醫師</th>
					<th>診間</th>
					<th>預約順位</th>
					<!-- 			<th>最大預約數</th> -->
				</tr>
				<tr>

					<%-- 			<td>${optVO.sessionNo}</td> --%>
					<td><fmt:formatDate value="${optVO.optDate}"
							pattern="yyyy/MM/dd (E)" /></td>
					<td>${optVO.optSession}</td>
					<td>
						<!-- 			暫時寫死3個科別(整合後修改) --> <c:forEach var="docVO"
							items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${(docVO.divno == 'D01')?'犬科':(docVO.divno == 'D02')?'貓科':'其他科'}
                    </c:if>
						</c:forEach>

					</td>
					<td><c:forEach var="docVO" items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${docVO.docname}
                    </c:if>
						</c:forEach></td>
					<td><c:forEach var="docVO" items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${docVO.roomno}診
                    </c:if>
						</c:forEach></td>
					<td>${optVO.currentCount+1}</td>
					<%-- 			<td>${optVO.maximum}</td> --%>

				
				</tr>
			</table>
			<br>
			<div style="width: 100%" align="center">
				<img
					src="https://viacapo.com/wp-content/themes/GoDoCommerceTheme/img/successgif.gif"><br>
				<span style="font-size : 30px;color:#0E923A;"> 預約成功</span>
			</div>


			
		
		
	</div>



	<%@ include file="../footer.jsp"%>


</body>


<script>
	$(function() {

		function format_float(num, pos) {
			var size = Math.pow(10, pos);
			return Math.round(num * size) / size;
		}

		function preview(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.preview').attr('src', e.target.result);
					var KB = format_float(e.total / 1024, 2);
					$('.size').text("檔案大小：" + KB + " KB");
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("body").on("change", ".upl", function() {
			console.log("123");
			preview(this);
		})

	})
</script>
</html>

