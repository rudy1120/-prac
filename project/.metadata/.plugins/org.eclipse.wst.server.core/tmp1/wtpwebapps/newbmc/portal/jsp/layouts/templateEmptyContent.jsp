<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<title><tiles:getAsString name="title" /></title>

		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/base_style.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/board.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/application.css" />

		<!-- common -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/common.js"></script>

		<!--[if lt IE 9]>
			<script type="text/javascript" src="/common/js/html5.js"></script>
			<script type="text/javascript" src="/common/js/common/prefixfree.min.js"></script>
		<![endif]-->
	</head>
	<body>
		<section id="pop_container">
			<tiles:insertAttribute name="body" />

			<div class="btnboxR">
				<a href="javascript:window.close();" class="btn_white"><strong>현재창닫기 <span class="gray">X</span></strong></a>
			</div>
		</section>
	</body>
</html>