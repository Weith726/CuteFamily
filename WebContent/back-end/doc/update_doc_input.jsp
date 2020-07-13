<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doc.model.*"%>

<%
  DocVO docVO = (DocVO) request.getAttribute("docVO"); //DocServlet.java (Concroller) 存入req的docVO物件 (包括幫忙取出的docVO, 也包括輸入資料錯誤時的docVO物件)
%>
<%= docVO==null %>--${docVO.divno}-- 
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="style.css">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>萌寵家族後台</title>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">


	
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<style>
input{display: block;}
select{display: block;}
textarea{display: block;}
</style>
	<script>
		

	</script>
	

	
	<nav class="navbar fixed-top">
		<div class="icon">
			<a href="Index.html">Cute:)<span style="color:#00E8E8;">Family</span>
			</a>

			<span class="navbarTitle">
				後台管理系統
			</span>

		</div>



		<div class="action" id="navbarMenu">


			<div class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<span class="mem-name">員工姓名</span>
					<img src="https://img.zi.org.tw/kocpc/2016/03/1459154850-700b055d1420ad51e9c6ea269dbf2317.jpg" class="mem-pic">
				</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">預留功能列</a>

					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="login.html">登出</a>
				</div>
			</div>

			<!-- <button type="button" class="btn btn-primary btn-lg" id="loginBtn">登入</button> -->


		</div>


	</nav>
	

	<div class="container-fluid">



		<div class="row">


			<div class="aside col-xl-2">


				<ul class="nav flex-column">

					<li class="nav-item">
						<div class="nav-link active" href="#"><svg class="bi bi-calendar3" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
							<path fill-rule="evenodd" d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z"/>
							<path fill-rule="evenodd" d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
						</svg> 門診管理 ▾
						
						<ul>
							<li><a href="#">班表管理</a></li>
							<li><a href="#">預約管理</a></li>
							<li><a href="#">團隊資料管理</a></li>	
							<li><a href="#">診療紀錄</a></li>	
						</ul>

					</div>

				</li>


				<li class="nav-item">
					<div class="nav-link" href="#"><svg class="bi bi-basket" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
						<path fill-rule="evenodd" d="M10.243 1.071a.5.5 0 0 1 .686.172l3 5a.5.5 0 1 1-.858.514l-3-5a.5.5 0 0 1 .172-.686zm-4.486 0a.5.5 0 0 0-.686.172l-3 5a.5.5 0 1 0 .858.514l3-5a.5.5 0 0 0-.172-.686z"/>
						<path fill-rule="evenodd" d="M1 7v1h14V7H1zM.5 6a.5.5 0 0 0-.5.5v2a.5.5 0 0 0 .5.5h15a.5.5 0 0 0 .5-.5v-2a.5.5 0 0 0-.5-.5H.5z"/>
						<path fill-rule="evenodd" d="M14 9H2v5a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V9zM2 8a1 1 0 0 0-1 1v5a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V9a1 1 0 0 0-1-1H2z"/>
						<path fill-rule="evenodd" d="M4 10a.5.5 0 0 1 .5.5v3a.5.5 0 1 1-1 0v-3A.5.5 0 0 1 4 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 1 1-1 0v-3A.5.5 0 0 1 6 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 1 1-1 0v-3A.5.5 0 0 1 8 10zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 1 1-1 0v-3a.5.5 0 0 1 .5-.5zm2 0a.5.5 0 0 1 .5.5v3a.5.5 0 1 1-1 0v-3a.5.5 0 0 1 .5-.5z"/>
					</svg> 商城管理 ▾

					<ul>
						<li><a href="#">商品管理</a></li>
						<li><a href="#">訂單管理</a></li>	
					</ul>

				</div>
			</li>
			<li class="nav-item">
				<div class="nav-link" href="#"><svg class="bi bi-shop" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd" d="M0 15.5a.5.5 0 0 1 .5-.5h15a.5.5 0 0 1 0 1H.5a.5.5 0 0 1-.5-.5zM3.12 1.175A.5.5 0 0 1 3.5 1h9a.5.5 0 0 1 .38.175l2.759 3.219A1.5 1.5 0 0 1 16 5.37v.13h-1v-.13a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.13H0v-.13a1.5 1.5 0 0 1 .361-.976l2.76-3.22z"/>
					<path d="M2.375 6.875c.76 0 1.375-.616 1.375-1.375h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 0 0 2.75 0h1a1.375 1.375 0 1 0 2.75 0h1a2.375 2.375 0 0 1-4.25 1.458 2.371 2.371 0 0 1-1.875.917A2.37 2.37 0 0 1 8 6.958a2.37 2.37 0 0 1-1.875.917 2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.5h1c0 .76.616 1.375 1.375 1.375z"/>
					<path d="M4.75 5.5a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0zm3.75 0a.5.5 0 1 1-1 0 .5.5 0 0 1 1 0z"/>
					<path fill-rule="evenodd" d="M2 7.846V7H1v.437c.291.207.632.35 1 .409zm-1 .737c.311.14.647.232 1 .271V15H1V8.583zm13 .271a3.354 3.354 0 0 0 1-.27V15h-1V8.854zm1-1.417c-.291.207-.632.35-1 .409V7h1v.437zM3 9.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 .5.5V15H7v-5H4v5H3V9.5zm6 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-4zm1 .5v3h2v-3h-2z"/>
				</svg> 旅館管理 ▾


				<ul>
					<li><a href="#">訂房管理</a></li>
					<li><a href="#">房型資訊管理</a></li>
					<li><a href="#">房間管理</a></li>	
				</ul>


			</div>
		</li>
		<li class="nav-item">
			<div class="nav-link" href="#"><svg class="bi bi-heart" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				<path fill-rule="evenodd" d="M8 2.748l-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
			</svg> 領養管理 ▾


			<ul>
				<li><a href="#">收容管理</a></li>
				<li><a href="#">領養管理</a></li>
			</ul>

		</div>
	</li>
	<li class="nav-item-single">
		<a class="nav-link" href="#"><svg class="bi bi-people" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.995-.944v-.002.002zM7.022 13h7.956a.274.274 0 0 0 .014-.002l.008-.002c-.002-.264-.167-1.03-.76-1.72C13.688 10.629 12.718 10 11 10c-1.717 0-2.687.63-3.24 1.276-.593.69-.759 1.457-.76 1.72a1.05 1.05 0 0 0 .022.004zm7.973.056v-.002.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10c-1.668.02-2.615.64-3.16 1.276C1.163 11.97 1 12.739 1 13h3c0-1.045.323-2.086.92-3zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z"/>
		</svg> 會員資料管理</a>
	</li>
	<li class="nav-item-single">
		<a class="nav-link" href="#"><svg class="bi bi-card-text" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M14.5 3h-13a.5.5 0 0 0-.5.5v9a.5.5 0 0 0 .5.5h13a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z"/>
			<path fill-rule="evenodd" d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8zm0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z"/>
		</svg> 最新消息管理</a>
	</li>
	<li class="nav-item-single">
		<a class="nav-link" href="#"><svg class="bi bi-emoji-frown" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
			<path fill-rule="evenodd" d="M4.285 12.433a.5.5 0 0 0 .683-.183A3.498 3.498 0 0 1 8 10.5c1.295 0 2.426.703 3.032 1.75a.5.5 0 0 0 .866-.5A4.498 4.498 0 0 0 8 9.5a4.5 4.5 0 0 0-3.898 2.25.5.5 0 0 0 .183.683z"/>
			<path d="M7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
		</svg> 客訴管理</a>
	</li>
	<li class="nav-item-single">
		<a class="nav-link" href="#"><svg class="bi bi-unlock" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M9.655 8H2.333c-.264 0-.398.068-.471.121a.73.73 0 0 0-.224.296 1.626 1.626 0 0 0-.138.59V14c0 .342.076.531.14.635.064.106.151.18.256.237a1.122 1.122 0 0 0 .436.127l.013.001h7.322c.264 0 .398-.068.471-.121a.73.73 0 0 0 .224-.296 1.627 1.627 0 0 0 .138-.59V9c0-.342-.076-.531-.14-.635a.658.658 0 0 0-.255-.237A1.122 1.122 0 0 0 9.655 8zm.012-1H2.333C.5 7 .5 9 .5 9v5c0 2 1.833 2 1.833 2h7.334c1.833 0 1.833-2 1.833-2V9c0-2-1.833-2-1.833-2zM8.5 4a3.5 3.5 0 1 1 7 0v3h-1V4a2.5 2.5 0 0 0-5 0v3h-1V4z"/>
		</svg> 員工帳號管理</a>




	</li>




</ul>


</div>


<div class="main col-xl-10">

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">醫師管理</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回管理主頁面</a></h6>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="doc.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>醫師編號:<font color=red><b>*</b></font></td>
		<td><h6 align="left"><%=docVO.getDocno()%></h6></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>科別編號:</td> -->
<%-- 		<td><input type="TEXT" name="divno" size="45" value="<%=docVO.getDivno()%>" /></td> --%>
<!-- 	</tr> -->
<jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />
	<tr>
		<td>科別:<font color=red><b>*</b></font></td>
		<td><select size="1" name="divno">
			<c:forEach var="divVO" items="${divSvc.all}">
				<option value="${divVO.divno}" ${(docVO.divno==divVO.divno)?'selected':'' } >${divVO.divname}</option>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<td>醫生姓名:</td>
	<td><input type="TEXT"  name="docname" size="60" value="<%=docVO.getDocname()%>" /></td>
<%-- 	<td><input type="TEXT"  name="docname" size="60" readonly="readonly" style="border-style : hidden;" value="<%=docVO.getDocname()%>" /></td> --%>
	</tr>
	<tr>
		<td>診間號碼</td>
		<td><input type="TEXT" name="roomno" size="60"	value="<%=docVO.getRoomno()%>" /></td>
	</tr>
	<tr>
		<td>年資:</td>
		<td><input type="TEXT" name="seniority" size="60" value="<%=docVO.getSeniority()%>" /></td>
	</tr>
	<tr>
		<td>介紹:</td>
		<td><textarea name="intro" rows="5" cols="65"><%=docVO.getIntro()%></textarea></td>
<%-- 		<td><input type="TEXT" name="intro" size="60" value="<%=docVO.getIntro()%>" /></td> --%>
	</tr>
	<tr>
		<td>醫生照片:</td>
		<td>
			<label for ="upload">
				<img alt="" src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=<%=docVO.getDocno()%>" id="display">
			</label>
			<input type="FILE" name="docpic" id = "upload"">
		</td>
	</tr>
	<tr>
		<td>醫生在職狀態:</td>
		<td>
<!-- 			<select name="docstatus" size="1"> -->
<%-- 			<option value="<%=docVO.getDocstatus()%>"  disabled selected hidden></option> --%>
<!-- 			<option value="1">在職中</option> -->
<!-- 			<option value="2">休假中</option> -->
<!-- 　			<option value="3">已離職</option> -->
			<input type="TEXT" name="docstatus" size="60" value="<%=docVO.getDocstatus()%>" />
		</td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="docno" value="<%=docVO.getDocno()%>">
<input type="submit" value="送出修改"></FORM>
<script>
	function init(){
		var display = document.getElementById("display");
		var upload = document.getElementById("upload");
		
		upload.addEventListener("change", function(e){
			var files = upload.files;
			if (files && files[0]){
				for (i = 0; i< files.length; i++){
					if (files[i].type.indexOf("image") <0){
						alert("上傳格式不符");
						upload.value = null;
					} else {
						var file = files[i];
						var reader = new FileReader();
						
						reader.onload = function(e){
							var result = e.target.result;
							
							display.setAttribute("src", result);
						}
						reader.readAsDataURL(file);
					}
				}
			}
		});
	}
	window.onload = init;

</script>
	<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<footer class="Footer">Copyright © 萌寵家族 Cute Family
</footer>


</div>

</div>


</body>
</html>