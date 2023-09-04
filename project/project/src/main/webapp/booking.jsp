<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>내 예매 내역</title>
</head>
<body>
    <h1>내 예매 내역</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>이벤트</th>
            <th>일자</th>
            <th>시간</th>
            <th>좌석</th>
            <th>결제 금액</th>
        </tr>
        <% for (Booking booking : bookings) { %>
        <tr>
            <td><%= booking.getId() %></td>
            <td><%= booking.getEventName() %></td>
            <td><%= booking.getDate() %></td>
            <td><%= booking.getTime() %></td>
            <td><%= booking.getSeat() %></td>
            <td><%= booking.getAmount() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
