<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "user.UserDAO" %>
<%@ page import = "java.io.PrintWriter" %> 
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPW"/>
<jsp:setProperty name="user" property="name"/>
<jsp:setProperty name="user" property="gender"/>
<jsp:setProperty name="user" property="email"/>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�쉶�썝媛��엯 以�</title>
</head>
<body>
<%
		UserDAO userDao=new UserDAO();

		String userID= request.getParameter("userID");
		String userPW= request.getParameter("userPW");
		String userName= request.getParameter("name");
		String userGender= request.getParameter("Gender");
		String userEmail= request.getParameter("email");
		
		if(userID == ""||userPW == ""||userName == ""||userGender == ""|| userEmail==""){
			PrintWriter script=response.getWriter();
			script.println("<script>");
			script.println("alert('紐⑤뱺 臾명빆�쓣 �엯�젰�븯�뿬二쇱떗�떆�삤')");
			script.println("history.back()");
			script.println("</script>");
		}

		else{
			int tem= userDao.checkID(userID);
			if(tem==1){
				PrintWriter script=response.getWriter();
				script.println("<script>");
				script.println("alert('以묐났�맂 �븘�씠�뵒 �엯�땲�떎.')");
				script.println("history.back()");
				script.println("</script>");
			}
			else{
				int result=userDao.joining(userID, userPW, userGender, userName, userEmail);
				if(result==1){
					PrintWriter script=response.getWriter();
					script.println("<script>");
					script.println("alert('�쉶�썝媛��엯�릺�뿀�뒿�땲�떎.')");
					script.println("location.href='Main_Page.jsp'");
					script.println("</script>");
				}
				else{
					PrintWriter script=response.getWriter();
					script.println("<script>");
					script.println("alert('臾몄젣媛� 諛쒖깮�븯���뒿�땲�떎.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
		}
%>
</body>
</html>