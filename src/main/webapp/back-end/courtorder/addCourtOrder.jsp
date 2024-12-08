<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.courtorder.model.*"%>

<% 
   CourtOrderVO courtOrderVO = (CourtOrderVO) request.getAttribute("courtOrderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>場館訂單新增 - addCourtOrder.jsp</title>

<style>
  table#table-1 {
    background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
    width: 450px;
    background-color: white;
    margin-top: 1px;
    margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
    <tr><td>
         <h3>場館訂單新增 - addCourtOrder.jsp</h3></td><td>
         <h4><a href="select_page_court_order.jsp"><img src="<%=request.getContextPath()%>/resource/images/tomcat.png" width="100" height="100" border="0">回首頁</a></h4>
    </td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do" name="form1">
<table>
    <tr>
        <td>會員編號:</td>
        <td><input type="TEXT" name="memberId" value="<%= (courtOrderVO==null)? "1" : courtOrderVO.getMemberId()%>" size="45"/></td>
    </tr>
    <tr>
        <td>場館編號:</td>
        <td><input type="TEXT" name="stadiumId" value="<%= (courtOrderVO==null)? "1" : courtOrderVO.getStadiumId()%>" size="45"/></td>
    </tr>
    <tr>
        <td>預約狀態:</td>
        <td>
            <select name="reservationStatus">
                <option value="true" selected>有效</option>
                <option value="false">取消</option>
            </select>
        </td>
    </tr>
    <tr>
        <td>訂單成立日期:</td>
        <td><input name="createdAt" id="f_date1" type="text"></td>
    </tr>
    <tr>
        <td>訂單總金額:</td>
        <td><input type="TEXT" name="totalAmount" value="<%= (courtOrderVO==null)? "1000" : courtOrderVO.getTotalAmount()%>" size="45"/></td>
    </tr>
    <tr>
        <td>取消原因:</td>
        <td><input type="TEXT" name="cancelReason" value="<%= (courtOrderVO==null)? "" : courtOrderVO.getCancelReason()%>" size="45"/></td>
    </tr>
    <tr>
        <td>評論內容:</td>
        <td>
            <textarea name="commentText" rows="4" cols="45"><%= (courtOrderVO==null || courtOrderVO.getCommentText()==null)? "" : courtOrderVO.getCommentText()%></textarea>
        </td>
    </tr>
    <tr>
        <td>評分星等:</td>
        <td>
            <select name="rating">
                <option value="">尚未評分</option>
                <option value="5" <%= (courtOrderVO!=null && courtOrderVO.getRating()!=null && courtOrderVO.getRating()==5)? "selected" : ""%>>5星</option>
                <option value="4" <%= (courtOrderVO!=null && courtOrderVO.getRating()!=null && courtOrderVO.getRating()==4)? "selected" : ""%>>4星</option>
                <option value="3" <%= (courtOrderVO!=null && courtOrderVO.getRating()!=null && courtOrderVO.getRating()==3)? "selected" : ""%>>3星</option>
                <option value="2" <%= (courtOrderVO!=null && courtOrderVO.getRating()!=null && courtOrderVO.getRating()==2)? "selected" : ""%>>2星</option>
                <option value="1" <%= (courtOrderVO!=null && courtOrderVO.getRating()!=null && courtOrderVO.getRating()==1)? "selected" : ""%>>1星</option>
            </select>
        </td>
    </tr>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date createdAt = null;
  try {
        createdAt = courtOrderVO.getCreatedAt();
   } catch (Exception e) {
        createdAt = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              
           timepicker:false,       
           step: 1,                
           format:'Y-m-d',         
           value: '<%=createdAt%>', 
        });
</script>
</html>