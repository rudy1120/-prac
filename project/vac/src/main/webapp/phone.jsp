<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>휴대폰 본인인증 처리</title>
</head>
<body>
    <h1>휴대폰 본인인증 처리</h1>
    <form action="phoneAction.jsp" method="post">
        <label for="phone">휴대폰 번호:</label>
        <input type="text" id="phone" name="phone" required>
        <button type="submit">인증번호 전송</button>
    </form>

</body>
</html>