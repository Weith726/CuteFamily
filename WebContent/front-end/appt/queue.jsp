<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

</head>

<title>IBM Emp: Home</title>

<%--自動送出 --%>
<body onload="document.form1.submit()"> 
<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
  <%--自動刷新--%>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" name="form1">
        
        
           
<!--        <b>設定編號日期:</b> -->
       <input type="hidden" name="apptno" id="f_date1" type="text" autocomplete="off"><br>
       
  
<!--   時段條件      -->
<%--   <jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" /> --%>
<%--   <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" /> --%>

<!--  	<td style="text-align:left;">  -->
<!--   	<li> -->
<!--      <b><font>選擇時段:</font></b> -->
<!--        <select size="1" name="sessionno"> -->
<%--          <c:forEach var="optVO" items="${optSvc.all}" >  --%>
<%--          <c:forEach var="docVO" items="${docSvc.all}" >  --%>
<%--          <c:if test="${(optVO.docNo==docVO.docno)}"> --%>
<%--           <option value="${optVO.sessionNo}">${docVO.docname} --%>
<%--           </c:if> --%>
<%--          </c:forEach> --%>
<%--          </c:forEach>     --%>
<!--        </select> -->

    
    
<!--        <b>設定看診狀態為1已看診:</b> -->
       <input type="hidden" name="optstate" value="1"><br>
           
       
		        
<!--         <input type="submit" value="送出"> -->
        <input type="hidden" name="action" value="listAppt_ByCompositeQuery">
     </FORM>
  </li>
</ul>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Ymd',         //format:'Y-m-d H:i:s',
		   value: 'new Date()',              // value:   new Date(),
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