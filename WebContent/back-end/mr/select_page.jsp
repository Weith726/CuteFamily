<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="../head.jsp" %>

	
</head>
<body>
<%@ include file="../header.jsp" %>
	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->

	<span class="mainTitle">診療紀錄管理</span>

	<hr class="mainTitlehr">

	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
<h3>資料查詢</h3>

<table>
  <li><a href='listAllMr.jsp'>紀錄一覽</a><br></li>


<!--   <tr> -->
<!--     <td style="text-align:left;">  -->
<!--   	<li> -->
<!--     <FORM METHOD="post" ACTION="mr.do" > -->
<!--         <b>輸入診療紀錄編號:</b> -->
<!--         <input type="text" size="5" name="mrno" style="width:130px"> -->
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   	</li> -->
<!--   </td> -->
<!--   </tr> -->

<%--   <jsp:useBean id="mrSvc" scope="page" class="com.mr.model.MrService" /> --%>
<!-- <tr>  -->
<!--  <td style="text-align:left;">  -->
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="mr.do" > -->
<!--        <b>選擇診療紀錄編號:</b> -->
<!--        <select size="1" name="mrno" style="width:130px"> -->
<%--          <c:forEach var="mrVO" items="${mrSvc.all}" >  --%>
<%--           <option value="${mrVO.mrno}">${mrVO.mrno} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
<!--    </td> -->
<!-- </tr> -->
<li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mr/mr.do" name="form1">
        <b><font color=blue>條件查詢:</font></b> <br>
        <b>選擇日期:</b>
        <input type="text" name="mrno" id="f_date1" type="text" autocomplete="off"><br>
               
   <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
               
       <b>選擇醫師:</b>
       <select size="1" name="docno" >
          <option value="">
         <c:forEach var="docVO" items="${docSvc.all}" > 
          <option value="${docVO.docno}">${docVO.docname}
         </c:forEach>   
       </select><br>	        
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="listMr_ByCompositeQuery">
     </FORM>
  </li>
<!-- <tr>  -->
<!--  <td style="text-align:left;">  -->
  
<!-- </ul> -->
<!--  </td> -->
<!-- </tr> -->

<!-- </table> -->

<!-- <br> -->
<!-- <h3>診療紀錄管理</h3> -->

<!-- <ul> -->
<!--   <li><a href='addMr.jsp'>新增一筆診療記錄。</a></li> -->
<!-- </ul> -->

<!-- ****************************以上為實際功能頁變更區域*************************** -->
</div>




<%@ include file="../footer.jsp" %>


</div>

</div>


</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Ymd',         //format:'Y-m-d H:i:s',
		   value: '',              // value:   new Date(),
		   validateOnBlur: false  
		   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
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
        
</script>
</html>