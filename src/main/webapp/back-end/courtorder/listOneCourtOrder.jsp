<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.courtorder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	CourtOrderVO courtOrderVO = (CourtOrderVO) request.getAttribute("courtorderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>場館訂單資料 - listOneCourtOrder.jsp</title>

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
	width: 1400px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>預約訂單資料 - listOneCourtOrder.jsp</h3>
		 <h4><a href="select_page_court_order.jsp"><img src="<%=request.getContextPath()%>/resource/images/back1.gif" width="100" height="30" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>場館編號</th>
		<th>預約狀態</th>
		<th>訂單成立日期</th>
		<th>訂單總金額</th>
		<th>取消原因</th>
		<th>評論留言</th>
    	<th>評論星等</th>
	</tr>
	<tr>
		<td>${courtOrderVO.stadiumOrderId}</td>
		<td>${courtOrderVO.memberId}</td>
		<td>${courtOrderVO.stadiumId}</td>
		<td>${courtOrderVO.reservationStatus ? '有效' : '取消'}</td>
		<td>${courtOrderVO.createdAt}</td>
		<td>${courtOrderVO.totalAmount}</td>
		<td>${courtOrderVO.cancelReason}</td>
		<td>${empty courtOrderVO.commentText ? ' ' : courtOrderVO.commentText}</td>
    	<td>${empty courtOrderVO.rating ? '未評分' : courtOrderVO.rating}${not empty courtOrderVO.rating ? '星' : ''}</td>
		<%-- <td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
	</tr>
</table>

</body>
</html>