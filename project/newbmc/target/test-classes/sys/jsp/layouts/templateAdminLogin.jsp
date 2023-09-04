<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html>
	<head>
		<title>통합관리자</title>
		<meta charset="UTF-8">
		<meta name="description" content="Smart CMS"/>
		<meta name="keywords" content=""/>
		<meta name="author" content="YHDatabase Co., Ltd"/>
		<meta name="Title" content="Smart CMS"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/common.css" />		
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/login.css" />
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/prefixfree.min.js" ></script>
	
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<!--[if IE 8]>
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/ie8_only.css" />
		<![endif]-->
	
	
		<!--[if lt IE 8]>
			<link rel="stylesheet" type="text/css" href="/sys/css/ie_access.css" />
			<script type="text/javascript">
			    document.write('<div  class="ie_06"><p>본사이트는 IE8이상의 브라우저에 최적화가 되어있습니다.<br/>현재 브라우저 버전에서는 편리하게 이용 하실 수 없습니다. Windows Internet Explorer를 업데이트를 받으세요</p><a href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie" target="_blank" class="btn_update">Internet Explorer 다운로드 받기</a></div>');
			</script>
		<![endif]-->
		
	</head>
		
	<body>
			<tiles:insertAttribute name="body" />
	</body>
</html>