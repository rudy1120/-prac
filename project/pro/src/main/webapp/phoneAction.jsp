<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>휴대폰 본인인증 처리</title>
</head>
<body>
    <h1>휴대폰 본인인증 처리</h1>
    
    <% 
    String phone = request.getParameter("phone");
    String apikey = "YOUR_API_KEY"; // 아임포트에서 발급받은 API 키
    // 실제로는 아임포트 API를 사용하여 본인인증을 처리해야 함
    
    // 여기에 아임포트 API 호출 및 처리 로직을 추가해야 함
    
    // 아임포트 API 호출 결과에 따른 처리를 수행
    boolean authenticationSuccess = true; // 휴대폰 본인인증 결과에 따라 설정
    
    if (authenticationSuccess) {
        // 본인인증 성공 시, 회원가입 페이지로 이동
        response.sendRedirect("join.jsp");
    } else {
        out.println("<p>휴대폰 본인인증 결과: 실패</p>");
        out.println("<p><a href='phone.jsp'>다시 시도</a></p>");
    }
    %>
</body>
</html>