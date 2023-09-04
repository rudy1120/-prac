<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="description" content="Smart CMS"/>
		<meta name="keywords" content=""/>
		<meta name="author" content="YHDatabase Co., Ltd"/>
		<meta name="Title" content="Smart CMS"/>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<title>통합관리자</title>

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/layout.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/board.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/common/css/jquery-ui.1.12.1.min.css" />
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/sys/css/treeView/jquery.treeview.css" />

	    <script type="text/javascript" src="/dwr/engine.js"></script><!-- Info : Dwr js가 jquery js보다 뒤에오면 오류가 발생한다. 이상하다. -->
		<script type="text/javascript" src="/dwr/util.js"></script>
		<script type='text/javascript' src='/dwr/interface/DwrCommonService.js'></script>

	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/common.js" charset="UTF-8" ></script>

	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/treeView/jquery.cookie.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/treeView/jquery.treeview.js" charset="UTF-8"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/sys/js/prefixfree.min.js" ></script>
		<%@ include file="/sys/jsp/component/default.jsp" %>
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
			<div class="content_pop">
				<tiles:insertAttribute name="body" />
			</div>
			<div class="btn_boxR">
				<a href="javascript:window.close();" class="btn_white">현재창닫기 <span class="gray">X</span></a>
			</div>
	</body>
</html>

