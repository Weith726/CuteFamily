<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
	
	
%>


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

#addbtn {
	float: right;
	font-size: 26px;
	padding: 5px 20px;
	margin-bottom: 20px;
}


</style>

</head>
<body>


	<%@ include file="../header.jsp"%>

	<span class="mainTitle">員工帳號管理</span>

	<!-- 	<a href="select_page.jsp">回首頁</a> -->


	<hr class="mainTitlehr">


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工照片</th>
			<th>員工姓名</th>
			<th>性別</th>
			<th>生日</th>
			<th>職位</th>
			<th>電話</th>
	<!-- 			<th>地址</th> -->
	<!-- 			<th>員工帳號</th> -->
	<!-- 			<th>員工密碼</th> -->			
	<!-- 			<th>到職日</th> -->
	<!-- 			<th>離職日</th> -->
			<th>員工狀態</th>
			<th>修改</th>
<!-- 			<th>刪除</th> -->
			<th>詳細資料</th>

		</tr>

		<%@ include file="page1.file"%>

		<button type="button" class="btn btn-success" id="addbtn" onclick="location.href='addEmp.jsp'">新增員工</button>	
			
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${empVO.empID}</td>
				<td><img
					src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td>
				<td>${empVO.empName}</td>
				<td>${(empVO.empGender=='男')?'<font color="darkblue">男':'<font color="deeppink">女'}</td>
				<%
					//<td>${empVO.hiredate}</td> 原本寫法， 下面改用JSTL
				%>
				<td><fmt:formatDate value="${empVO.empBirth}"
						pattern="yyyy/MM/dd" /></td>

				<td>${empVO.empJob}</td>
				<td>${empVO.empPhone}</td>
<!-- 				以下做在詳細資料內 -->
<%-- 				<td>${empVO.empAddress}</td> --%>
<%-- 				<td>${empVO.empAcc}</td> --%>
<%-- 				<td>${empVO.empPwd}</td> --%>

				
<%-- 				<td>${empVO.hiredate}</td> --%>
<%-- 				<td>${empVO.quitdate}</td> --%>
<!-- 				以上做在詳細資料內 -->
				<td>${(empVO.empStatus =='1')?'<font color="green">在職中':(empVO.empStatus =='2')?'休假中':'<font color="darkred">已離職'}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-warning">修改</button>
						<input type="hidden" name="empID" value="${empVO.empID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" id="formDel" onsubmit=" return confirmDel();" -->
<%-- 						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<button type="submit" class="btn btn-danger">刪除</button><input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="delete" id="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-info" >詳細資料</button>
						<input type="hidden" name="empID" value="${empVO.empID}"> 
						<input type="hidden" name="action" value="getOne_For_Display">
					</FORM>
				</td>

			</tr>

		</c:forEach>
	</table>
	
	<!-- Button trigger modal -->
<input type="hidden" id="detile" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">



<!-- Modal -->
<c:if test="${empVO!=null}">
	
	
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">詳細資料</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">


	<table>
		<tr>
<!-- 			<th>員工編號</th> -->
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>地址</th>
			<th>到職日</th>
			<th>離職日</th>


		</tr>
		<tr>
<%-- 			<td>${empVO.empID}</td> --%>
			<td>${empVO.empName}</td>
			<td>${empVO.empAcc}</td>
			<td>${empVO.empPwd}</td>
			<td>${empVO.empAddress}</td>
			<td><fmt:formatDate value="${empVO.hiredate}"
					pattern="yyyy/MM/dd" /></td>
			<td><fmt:formatDate value="${empVO.quitdate}"
					pattern="yyyy/MM/dd" /></td>
	
			
			
		</tr>
	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</c:if>
	
	<%@ include file="page2.file"%>

	<%@ include file="../footer.jsp"%>

	<script type="text/javascript">
	$(function() {
		$( "#detile" ).trigger( "click" );
	});
	
	//刪除前的確認
		function confirmDel() {	

			var txt;
			var r = confirm("確定刪除?");
			if (r == true) {
				return true;
			} else {
// 				document.getElementById("formDel").value = "";//清空文本框  
				return false;
			}

		};
	</script>

</body>
</html>