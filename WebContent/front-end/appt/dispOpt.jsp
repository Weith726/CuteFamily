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
String docno = request.getParameter("doc");

System.out.print(docno);


OptService optSvc = new OptService();
List<OptVO> list = optSvc.getCalInfoByDoc(docno);
Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
String jsonStr = gson.toJson(list);

System.out.println("Object to JSON: " + jsonStr);

pageContext.setAttribute("jsonStr", jsonStr);


%>



<html>
<head>
<%@ include file="../head.jsp"%>

<link href='../fullcalendar/main.css' rel='stylesheet' />
<script src='../fullcalendar/main.js'></script>
<script src='../fullcalendar/locales-all.js'></script>


<script>
	var eventDate = '';

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth'//,timeGridWeek,timeGridDay'
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
      
      eventClick: function(arg) {
    	  var str = arg.event.id;
    	  //JQ post寫法，無法使用，但可接收後端處理資料
//     	  $.post("apptStart.do?action=addAppt&sessionNo="+str+"");
    	  window.location.href='apptStart.do?action=addAppt&sessionNo='+str+'';
        
    	  console.log(arg.event.start);
    	  console.log(arg.event.title);	  
    	  console.log(arg.event.id);
	
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: ${jsonStr}
//       events: [{'title':123,'start':'2020-07-02'}]

    });
    calendar.render();
  });
  
  console.log(eventDate);


  </script>
  
  <style>
  
  #calendar {
	    max-width: 1000px;
	    margin: 0 auto;
  </style>
  


</head>

<body>
<%@ include file="../header.jsp"%>	




	<div id='calendar'></div>



	
<%@ include file="../footer.jsp"%>
</body>


</html>