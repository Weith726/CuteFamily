<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>



<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" name="form1">
        <b><font color=blue>門診預約查詢:</font></b> <br>
        
           
<!--        <b>設定會員編號:</b> -->
       <input type="hidden" name="memno" value="M0004"><br>
       
       
      
    
<!--        <b>設定看診狀態為0未看診:</b> -->
       <input type="hidden" name="optstate" value="0"><br>
           
       
		        
        <input type="submit" value="門診預約查詢">
        <input type="hidden" name="action" value="listAppt_ByCompositeQuery2">
     </FORM>
  </li>
</ul>



</html>