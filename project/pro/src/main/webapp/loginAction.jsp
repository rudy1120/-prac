<%@ page language="java" contentType="text/html; charset=UTF-8 "
    pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="User" class="user.User" scope="page"/>
<jsp:setProperty name="User" property="userID"/>
<jsp:setProperty name="User" property="userPassword"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 화면</title>
</head>
<body>
	<%	
		UserDAO userDao=new UserDAO();
		String aa=(String)request.getParameter("loginid");
		String bb=(String)request.getParameter("loginpw");
		int result=userDao.login(aa, bb);

		if(result==1){
			String idi= request.getParameter("loginid");
			request.setAttribute("idi", idi);
			
			pageContext.forward("index.jsp");
		}
		else if(result==0){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 일치하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result==-1){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result==-2){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		%>
</body>
</html>