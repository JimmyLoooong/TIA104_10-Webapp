<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM CourtOrder: Home</title>

<style>
table#table-1 {
    width: 450px;
    background-color: #CCCCFF;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
    <tr><td><h3>IBM  CourtOrder: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM CourtOrder: Home</p>

<h3>查詢:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font style="color:red">請修正以下錯誤:</font>
    <ul>
        <c:forEach var="message" items="${errorMsgs}">
            <li style="color:red">${message}</li>
        </c:forEach>
    </ul>
</c:if>

<ul>
    <li><a href='listAllCourtOrder.jsp'>List</a> all CourtOrders. <br><br></li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do">
            <b>輸入訂單編號 (如7001):</b>
            <input type="text" name="stadiumOrderId">
            <input type="hidden" name="action" value="getOne_For_Display_Court_Order">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <jsp:useBean id="courtOrderSvc" scope="page" class="com.courtorder.model.CourtOrderService"/>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do">
            <b>選擇訂單編號:</b>
            <select size="1" name="stadiumOrderId">
                <c:forEach var="courtOrderVO" items="${courtOrderSvc.all}">
                    <option value="${courtOrderVO.stadiumOrderId}">${courtOrderVO.stadiumOrderId}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display_Court_Order">
            <input type="submit" value="送出">
        </FORM>
    </li>

    <li>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do">
            <b>選擇場館編號:</b>
            <select size="1" name="stadiumOrderId">
                <c:forEach var="courtorderVO" items="${courtOrderSvc.all}">
                    <option value="${courtorderVO.stadiumOrderId}">${courtorderVO.stadiumId}
                </c:forEach>
            </select>
            <input type="hidden" name="action" value="getOne_For_Display_Court_Order">
            <input type="submit" value="送出">
        </FORM>
    </li>
    <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/courtorder/courtorder.do">
        <b>依評分查詢:</b>
        <select size="1" name="rating">
            <option value="">請選擇</option>
            <option value="5">5星</option>
            <option value="4">4星</option>
            <option value="3">3星</option>
            <option value="2">2星</option>
            <option value="1">1星</option>
        </select>
        <input type="hidden" name="action" value="getByRating">
        <input type="submit" value="送出">
    </FORM>
</li>
</ul>

<h3>場館訂單管理</h3>

<ul>
    <li><a href='addCourtOrder.jsp'>Add</a> a new CourtOrder.</li>
</ul>

</body>
</html>