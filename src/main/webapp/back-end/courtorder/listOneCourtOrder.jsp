<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.courtorder.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	CourtOrderVO courtOrderVO = (CourtOrderVO) request.getAttribute("courtorderVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���]�q���� - listOneCourtOrder.jsp</title>

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

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�w���q���� - listOneCourtOrder.jsp</h3>
		 <h4><a href="select_page_court_order.jsp"><img src="<%=request.getContextPath()%>/resource/images/back1.gif" width="100" height="30" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�q��s��</th>
		<th>�|���s��</th>
		<th>���]�s��</th>
		<th>�w�����A</th>
		<th>�q�榨�ߤ��</th>
		<th>�q���`���B</th>
		<th>������]</th>
		<th>���ׯd��</th>
    	<th>���׬P��</th>
	</tr>
	<tr>
		<td>${courtOrderVO.stadiumOrderId}</td>
		<td>${courtOrderVO.memberId}</td>
		<td>${courtOrderVO.stadiumId}</td>
		<td>${courtOrderVO.reservationStatus ? '����' : '����'}</td>
		<td>${courtOrderVO.createdAt}</td>
		<td>${courtOrderVO.totalAmount}</td>
		<td>${courtOrderVO.cancelReason}</td>
		<td>${empty courtOrderVO.commentText ? ' ' : courtOrderVO.commentText}</td>
    	<td>${empty courtOrderVO.rating ? '������' : courtOrderVO.rating}${not empty courtOrderVO.rating ? '�P' : ''}</td>
		<%-- <td>${empVO.deptno}-[${empVO.deptVO.dname}]</td> --%>
	</tr>
</table>

</body>
</html>