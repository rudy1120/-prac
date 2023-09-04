<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // 사용자가 입력한 정보를 받아옵니다.
  String username = request.getParameter("username");
  String email = request.getParameter("email");
  String password = request.getParameter("password");

  // 이 예시에서는 세션에 사용자 정보를 저장합니다.
  // 실제로는 데이터베이스에 저장하거나 다른 처리를 해야합니다.
  session.setAttribute("username", username);
  session.setAttribute("email", email);

  // AJAX 요청에 대한 응답을 전송합니다.
  response.getWriter().write("success");
%>
