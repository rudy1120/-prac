<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko" >

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
		<title>부산도시공사 역사관</title>
		<!-- css -->
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/font/font.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/base.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/plugin/slick/slick.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/common.css">
	    <link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/main.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/history/css/print.css">
	
	    <!-- js -->
		<script src="${pageContext.request.contextPath}/history/plugin/jquery/jquery.min.js"></script>
		<script src="${pageContext.request.contextPath}/history/plugin/slick/slick.js"></script>
		<script src="${pageContext.request.contextPath}/history/js/common.js"></script>
		<script src="${pageContext.request.contextPath}/history/js/main.js"></script>
	</head>
	 
	<body>
		<!--건너뛰기 링크-->
	    <div id="skip"></div>
		<tiles:insertAttribute name="header" />
		
		<div id="container">
			<tiles:insertAttribute name="body" />
		</div>
		<tiles:insertAttribute name="footer" />
	</body>
</html>
