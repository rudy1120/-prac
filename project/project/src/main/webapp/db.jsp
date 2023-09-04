<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="oracle.jdbc.driver.OracleDriver"%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World</h1>
<table border="1">
	<tr>
		<th>학번</th>
		<th>이름</th>
		<th>학과번호</th>
	</tr>
<%
	// 클래스 로드
	Class.forName("oracle.jdbc.driver.OracleDriver");
 
	// connection 취득
	Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "student", "1234");
	out.print(conn);
	
	// 문장 생성
	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM STUDENT ORDER BY STUDNO");
	
	// 결과집합 생성
	ResultSet rs = pstmt.executeQuery();
	
	// 결과집합 순환 후 적절히 가공
	while(rs.next()) {
%>
	<tr>
		<td><%=rs.getInt("studno")%></td>
		<td><%=rs.getString("name")%></td>
		<td><%=rs.getString("deptno")%></td>
	</tr>
<%
	}
%>
</table>
</body>
</html>