<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.courtorder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    CourtOrderService courtOrderSvc = new CourtOrderService();
    List<CourtOrderVO> list = courtOrderSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有員工資料 - listAllCourtOrder.jsp</title>

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
	width: 1200px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>場館訂單資料 - listAllCourtOrder.jsp</h3>
		 <h4><a href="select_page_court_order.jsp"><img src="<%=request.getContextPath()%>/resource/images/back1.gif" width="120" height="32" border="0">回首頁</a></h4>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="courtOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<!-- 在表格前加入要刪 -->
	<c:if test="${not empty errorMsgs}">
	    <div style="color:red; margin-bottom: 10px;">
	        <h4>錯誤訊息:</h4>
	        <ul>
	            <c:forEach var="message" items="${errorMsgs}">
	                <li>${message}</li>
	            </c:forEach>
	        </ul>
	    </div>
	</c:if>
	<!-- 在表格前加入要刪 -->
		
		<tr>
			<td>${courtOrderVO.stadiumOrderId}</td>
			<td>${courtOrderVO.memberId}</td>
			<td>${courtOrderVO.stadiumId}</td>
			<td>
			    <c:choose>
			        <c:when test="${courtOrderVO.reservationStatus == false}">取消</c:when>
			        <c:otherwise>有效</c:otherwise>
			    </c:choose>
			</td>
			<td>${courtOrderVO.createdAt}</td>
			<td>${courtOrderVO.totalAmount}</td>
			<td>${courtOrderVO.cancelReason}</td>
			<td>${courtOrderVO.commentText}</td>
			<td>
			    <c:choose>
			        <c:when test="${courtOrderVO.rating == null}">尚未評等</c:when>
			        <c:otherwise>${courtOrderVO.rating}星</c:otherwise>
			    </c:choose>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="stadiumOrderId"  value="${courtOrderVO.stadiumOrderId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" >
			     <input type="hidden" name="stadiumOrderId"  value="${courtOrderVO.stadiumOrderId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>