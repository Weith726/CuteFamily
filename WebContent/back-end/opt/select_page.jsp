<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%@ include file="../head.jsp"%>

<link href='../fullcalendar/main.css' rel='stylesheet' />
<script src='../fullcalendar/main.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      initialDate: '2020-06-12',
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectMirror: true,
      select: function(arg) {
        var title = prompt('Event Title:');
        if (title) {
          calendar.addEvent({
            title: title,
            start: arg.start,
            end: arg.end,
            allDay: arg.allDay
          })
        }
        calendar.unselect()
      },
      eventClick: function(arg) {
        if (confirm('Are you sure you want to delete this event?')) {
          arg.event.remove()
        }
      },
      editable: true,
      dayMaxEvents: true, // allow "more" link when too many events
      events: [
        {
          title: 'All Day Event',
          start: '2020-06-01'
        },
        {
          title: 'Long Event',
          start: '2020-06-07',
          end: '2020-06-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-06-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-06-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2020-06-11',
          end: '2020-06-13'
        },
        {
          title: 'Meeting',
          start: '2020-06-12T10:30:00',
          end: '2020-06-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2020-06-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2020-06-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2020-06-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2020-06-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2020-06-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2020-06-28'
        }
      ]
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

	<a href="select_page.jsp">回首頁</a>

	<hr class="mainTitlehr">


	<div>
		<a href='addOptSession.jsp'>開始排班</a>
	</div>
	<br>


	<hr>
	<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />
	依日期查詢:
	<br>


	<div>
		<a href='listByDate.jsp'>List</a> all Emps.
	</div>

	<FORM METHOD="post" ACTION="opt.do">
		<b>選擇員工姓名:</b> <select size="1" name="sessionNo">
			<c:forEach var="optVO" items="${optSvc.all}">
				<option value="${optVO.sessionNo}">${optVO.sessionNo}
			</c:forEach>
		</select> <input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
	</FORM>



	<div id='calendar'></div>






	<!--      <FORM METHOD="post" ACTION="opt.do" > -->
	<!--        <b>選擇員工姓名:</b> -->
	<!--        <select size="1" name="sessionNo"> -->
	<%--          <c:forEach var="optVO" items="${optSvc.all}" >  --%>
	<%--           <option value="${optVO.sessionNo}">${optVO.sessionNo} --%>
	<%--          </c:forEach>    --%>
	<!--        </select> -->
	<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
	<!--        <input type="submit" value="送出"> -->
	<!--      </FORM> -->


	<%@ include file="../footer.jsp"%>

</body>



</html>