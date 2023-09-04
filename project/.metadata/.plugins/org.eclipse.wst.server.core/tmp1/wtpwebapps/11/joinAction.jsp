<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="USER" class="user.User" scope="page"/>
<jsp:setProperty name="USER" property="userID"/>
<jsp:setProperty name="USER" property="userPassword"/>
<jsp:setProperty name="USER" property="userName"/>
<jsp:setProperty name="USER" property="userGender"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content= "text/html; charset=UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
<%
		UserDAO userDao=new UserDAO();

		String userID= request.getParameter("userID");
		String userPassword= request.getParameter("userPassword");
		String userName= request.getParameter("userName");
		String userGender= request.getParameter("userGender");
		
		if(userID == ""||userPassword == ""||userName == ""|| userGender==""){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('모든 문항을 입력하여주십시오')");
			script.println("history.back()");
			script.println("</script>");
		}

		else{
			int tem= userDao.checkID(userID);
			if(tem==1){
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('중복된 아이디 입니다.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else{
				int result=userDao.join(userID, userPassword, userName, userGender);
				if(result==1){
					PrintWriter script=response.getWriter();
					script.println("<script>");
					script.println("alert('회원가입되었습니다.')");
					script.println("location.href='index.jsp'");
					script.println("</script>");
				}
				else{
					PrintWriter script=response.getWriter();
					script.println("<script>");
					script.println("alert('문제가 발생하였습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
		}
%>
</body>
</html>