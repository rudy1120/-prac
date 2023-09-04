<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>대표 홈페이지</title>
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/board.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/application.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/jquery-ui.css"/>

		<!--[if IE]>
			<link rel="stylesheet" type="text/css" href="/common/css/ie_common.css" />
		<![endif]-->
		<!--[if IE 8]>
			<link rel="stylesheet" type="text/css" href="/common/css/ie8_common.css" />
			<link rel="stylesheet" type="text/css" href="/portal/css/ie8_style.css" />
		<![endif]-->
		<!--[if lt IE 9]>
			<script type="text/javascript" src="/common/js/common/prefixfree.min.js"></script>
			<script type="text/javascript" src="/common/js/common/html5.js"></script>
		<![endif]-->
		<!--[if lt IE 8]>
			<link rel="stylesheet" type="text/css" href="/common/css/ie_access.css" />
			<script type="text/javascript">
				document.write('<div  class="ie_06"><p>본사이트는 IE8이상의 브라우저에 최적화가 되어있습니다.<br/>현재 브라우저 버전에서는 편리하게 이용 하실 수 없습니다. Windows Internet Explorer를 업데이트를 받으세요</p><a href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie" target="_blank" class="btn_update">Internet Explorer 다운로드 받기</a></div>');
			</script>
		<![endif]-->

		<script type="text/javascript" src="${_context}/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${_context}/common/js/jquery/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="${_context}/common/js/jquery/jquery.form.js"></script>

		<script type="text/javascript" src="${_context}/common/js/common/ie-checker.js"></script>
		<script type="text/javascript" src="${_context}/common/js/menu.js"></script>
		<script type="text/javascript" src="${_context}/common/js/common.js"></script>
		<script type="text/javascript" src="${_context}/common/js/sns.js"></script>
		<%@ include file="/common/jsp/component/default.jsp" %>
	</head>
	<body class="popup_wrap">
		<tiles:insertAttribute name="body" />
		<div class="btnboxR">
			<a href="#" onclick="window.close(); return false;" class="btn"><strong>현재창닫기 <span class="gray">X</span></strong></a>
		</div>
	</body>
	</body>
</html>