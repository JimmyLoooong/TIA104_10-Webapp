<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.courtorder.model.*"%>


<%
//��com.emp.controller.EmpServlet.java��163��s�Jreq��empVO���� (�����q��Ʈw���X��empVO, �]�i�H�O��J�榡�����~�ɪ�empVO����)
CourtOrderVO courtOrderVO = (CourtOrderVO) request.getAttribute("courtOrderVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>���u��ƭק� - update_court_order_input.jsp</title>

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
	width: 500px;
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
		<tr>
			<td>
				<h3>�q���ƭק� - update_court_order_input.jsp</h3>
				<h4>
					<a href="select_page_court_order.jsp"><img
						src="<%=request.getContextPath()%>/resource/images/back1.gif"
						width="100" height="32" border="0">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do"
		name="form1">
		<table>
			<tr>
				<td>���]�q��s��:<font color=red><b>*</b></font></td>
				<td><%=courtOrderVO.getStadiumOrderId()%></td>
			</tr>
			<tr>
				<td>�|���s��:</td>
				<td><%=courtOrderVO.getMemberId()%></td>
			</tr>
			<tr>
				<td>���]�s��:</td>
				<td><%=courtOrderVO.getStadiumId()%></td>
			</tr>
			<tr>
				<td>�w�����A:</td>
				<td><select name="reservationStatus">
						<option value="true"
							${courtOrderVO.reservationStatus ? 'selected' : ''}>����</option>
						<option value="false"
							${courtOrderVO.reservationStatus ? '' : 'selected'}>����</option>
				</select></td>
			</tr>
			<tr>
				<td>�q��إߤ��:</td>
				<td><%=courtOrderVO.getCreatedAt()%></td>
			</tr>
			<tr>
				<td>�q���`���B:</td>
				<td><%=courtOrderVO.getTotalAmount()%></td>
			</tr>
			<tr>
				<td>������]:</td>
				<td><input type="TEXT" name="cancelReason"
					value="<%=courtOrderVO.getCancelReason()%>" size="45" /></td>
			</tr>
			<tr>
				<td>���ׯd��:</td>
				<td><textarea name="commentText" rows="4" cols="45"><%=courtOrderVO.getCommentText()%></textarea></td>
			</tr>
			<tr>
				<td>���׬P��:</td>
				<td><select name="rating">
						<option value="">�п��</option>
						<option value="1" ${courtOrderVO.rating == 1 ? 'selected' : ''}>1�P</option>
						<option value="2" ${courtOrderVO.rating == 2 ? 'selected' : ''}>2�P</option>
						<option value="3" ${courtOrderVO.rating == 3 ? 'selected' : ''}>3�P</option>
						<option value="4" ${courtOrderVO.rating == 4 ? 'selected' : ''}>4�P</option>
						<option value="5" ${courtOrderVO.rating == 5 ? 'selected' : ''}>5�P</option>
				</select></td>
			</tr>

			<!-- �ϥ��������O�s��L���ܰʪ��� -->
			<input type="hidden" name="stadiumOrderId"
				value="${courtOrderVO.stadiumOrderId}">
			<input type="hidden" name="memberId" value="${courtOrderVO.memberId}">
			<input type="hidden" name="stadiumId"
				value="${courtOrderVO.stadiumId}">
			<input type="hidden" name="createdAt"
				value="${courtOrderVO.createdAt}">
			<input type="hidden" name="totalAmount"
				value="${courtOrderVO.totalAmount}">
			<input type="hidden" name="commentText"
				value="${courtOrderVO.commentText}">
			<input type="hidden" name="rating" value="${courtOrderVO.rating}">



			<!--<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" />
	<tr>
		<td>����:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${deptSvc.all}">
				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		</select></td>
	</tr>-->

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="stadiumOrderId"
			value="<%=courtOrderVO.getStadiumOrderId()%>"> <input
			type="submit" value="�e�X�ק�">
	</FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=courtOrderVO.getCreatedAt()%>
	', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
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

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
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

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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

<%
// �T�{�O�_�i�J�F����
System.out.println("Debug: update_emp_input.jsp �w�[��");
%>
