<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String memNO = (String) session.getAttribute("memNO");
	String memName = (String) session.getAttribute("memName");
%>


<header>
	<nav class="navbar navbar-expand-lg navbar-light ">
		<a href="<%=request.getContextPath()%>/front-end/frontEndIndex/index.jsp" class="navbar-brand ml-3">
			Cute:)
			<span style="color: #00E8E8;">Family</span>
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse"></div>
		<div class="collapse navbar-collapse" id="navbarMenu">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a href="index.html" class="nav-link">首頁</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 會員專區 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#">會員登入</a>
						<form action="<%=request.getContextPath()%>/Puppy/mem.do" METHOD="post" style="margin-bottom: 0px;">
							<input type="submit" value="編輯會員資料" class="dropdown-item">
							<input type="hidden" name="memNO" value="${memNO}">
							<input type="hidden" name="action" value="getOne_For_Update">
						</form>
						<a class="dropdown-item" href="#">管理您的寵物</a>
						<form METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
							<input type="submit" value="瀏覽最新通知" class="dropdown-item">
							<input type="hidden" name="mli" value="${memNO}">
							<input type="hidden" name="action" value="getAll_For_Display">
						</form>
					</div>
				</li>
				<li class="nav-item dropdown">
					<a href="#" class="nav-link">門診專區</a>
				</li>
				<li class="nav-item dropdown">
					<a href="<%=request.getContextPath()%>/front-end/Hotel/myIndex.jsp" class="nav-link">寵物旅館</a>
				</li>
				<li class="nav-item dropdown">
					<a href="#" class="nav-link">寵物商城</a>
				</li>
				<li class="nav-item dropdown">
					<a href="#" class="nav-link">領養專區</a>
				</li>
			</ul>
			<div style="<%=(memNO == null) ? "visibility:hidden" : "visibility:"%>">
				<img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memNO}" style="height: 50px" id="mempic">
				<%=memName%>您好~
			</div>
			<a href="#">
				<button class="btn menu-right-btn border" type="button" style="<%=(memNO == null) ? "display:" : "display:none"%>">註冊</button>
			</a>
			<a href="#">
				<button class="btn menu-right-btn border" type="submit" id="login" style="<%=(memNO == null) ? "display:" : "display:none"%>">登入</button>
			</a>
			<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/Puppy/logout.do">
				<button class="btn menu-right-btn border" type="submit" id="logout" style="<%=(memNO != null) ? "display:" : "display:none"%>">登出</button>
			</form>
		</div>
	</nav>
</header>