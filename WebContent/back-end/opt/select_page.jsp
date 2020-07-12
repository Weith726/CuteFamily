<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.opt.model.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>


<%
OptService optSvc = new OptService();
List<OptVO> list = optSvc.getCalInfo();
Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
String jsonStr = gson.toJson(list);

System.out.println("Object to JSON: " + jsonStr);

pageContext.setAttribute("list", jsonStr);
%>



<html>
<head>
<%@ include file="../head.jsp"%>

<link href='../fullcalendar/main.css' rel='stylesheet' />
<script src='../fullcalendar/main.js'></script>
<script src='../fullcalendar/locales-all.js'></script>

<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      hiddenDays: [0],
      slotMinTime: "09:00:00",
      slotMaxTime: "22:00:00",
      locale: 'zh-tw',
      initialDate: new Date(),
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
//       eventClick: function(arg) {
//         if (confirm('Are you sure you want to delete this event?')) {
//           arg.event.remove()
//         }
//       },
//      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: ${list}
//       events: [{'title':123,'start':'2020-07-02'}]

    });
    calendar.render();
  });


  </script>
  
  <style>
  
  #calendar {
	    max-width: 1100px;
	    margin: 0 auto;
  </style>
  


</head>

<body>
	<%@ include file="../header.jsp"%>

	<span class="mainTitle">班表管理</span>

	<hr class="mainTitlehr">

	
	<div >
	<a style="font-size:24px" class="btn btn-primary" href="addOptSession.jsp" role="button">開始排班</a>
	</div>
	<br>


<!-- 	<FORM METHOD="post" ACTION="opt.do"> -->
<!-- 		<b>選擇醫生:</b> <select size="1" name="sessionNo"> -->
<%-- 			<c:forEach var="optVO" items="${optSvc.all}"> --%>
<%-- 				<option value="${optVO.sessionNo}">${optVO.sessionNo} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 		<input type="submit" value="送出"> -->
<!-- 	</FORM> -->



	<div id='calendar'></div>



	<%@ include file="../footer.jsp"%>

</body>



</html>