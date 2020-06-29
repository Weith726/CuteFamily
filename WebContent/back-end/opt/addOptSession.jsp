<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.opt.model.*"%>

<%
	OptVO optVO = (OptVO) request.getAttribute("optVO");
%>


<html>
<head>
<%@ include file="../head.jsp"%>



<style>

table {
width:100%;


	margin-top: 5px;
	margin-bottom: 5px;
	border: 7px solid;
	border-color:rgb(100,100,100,0.2);
	
}

th {


	text-align: right;
	width:100px;
}

th, td {

	padding: 5px;
/* 	border: 1px solid black; */
	height:40px;
}

.mainTitle {
	letter-spacing: 8px;
	color: #42454C;
	font-weight: bold;
	font-size: 28px;
	padding-left: 20px;
}

.mainTitlehr {
	border: 2px solid lightcoral;
}
</style>

</head>


<body>

<%@ include file="../header.jsp"%>

	<span class="mainTitle">新增班表</span>

	<a href="listAllEmp.jsp">返回員工資料</a>

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

	<FORM METHOD="post" ACTION="opt.do" name="form1" id="form1">
		<table>
			

<!-- 密碼不該讓管理者輸入 -->
<!-- 			<tr> -->
<!-- 				<th>密碼</th> -->
<!-- 				<td><input type="password" name="empPwd" size="20" -->
<%-- 					value="<%=(empVO == null) ? "" : empVO.getEmpPwd()%>" /></td> --%>
<!-- 			</tr> -->
		
		
			

			

			<tr>
				<th>看診日期</th>
				<td><input name="optDate" id="f_date1" type="text"></td>
			</tr>



			<tr>
				<th>醫生</th>
				<td>
<!-- 				<input type="text" name="empStatus" size="4" -->
<%-- 					value="<%=(empVO == null) ? "1" : empVO.getEmpStatus()%>" /> --%>
					<select name="docNo">
　						<option value="DR01">DR01</option>
　						<option value="DR02">DR02</option>
　						<option value="DR03">DR03</option>

					</select>
					
					</td>
			</tr>
			
			<th>時段</th>
				<td>
<!-- 				<input type="text" name="empStatus" size="4" -->
<%-- 					value="<%=(empVO == null) ? "1" : empVO.getEmpStatus()%>" /> --%>
					<select name="optSession">
　						<option value="09:00~12:00">早</option>
　						<option value="13:00~17:00">中</option>
　						<option value="18:00~20:00">晚上</option>

					</select>
					
					</td>
			</tr>

			




		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
	 <span id="lblMsg"></span>
	
	<%@ include file="../footer.jsp"%>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date optDate = null;
	try {
		optDate = optVO.getOptDate(); //非空值存到hiredate
	} catch (Exception e) {
		optDate = null; //空值給null

	}
%>




<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>


        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',          //format:'Y-m-d H:i:s',
	       value: '<%=optDate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '1980/01/01',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        
       
	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	
	
	 	 $(function () {
            $(":submit").click(function () {
                $("#lblMsg").text("處理中，請稍候...");
                $(this).prop("disabled", true);
                $("#form1").submit();
             
            });
        });
	    
// 	        $("#send").click(function(){
// 	        	$("#send").attr("disabled", true);
// 	            $("#form1").submit();
// 	        });
	
	
	</script>
</html>