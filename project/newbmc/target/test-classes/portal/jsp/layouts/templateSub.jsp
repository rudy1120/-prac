<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="format-detection" content="telephone=no">
		<title>${activeMenu.subHeadTitle2} | 홈페이지</title>

		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/jquery.bxslider.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/board.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/application.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/layout.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/sub.css" />
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_responsive.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/responsive.css" />

		<!--[if lt IE 9]>
			<script type="text/javascript" src="/common/js/common/html5.js"></script>
		<![endif]-->

		<!--[if lt IE 8]>
			<link rel="stylesheet" type="text/css" href="/common/css/ie_access.css" />
			<script type="text/javascript">
				document.write('<div  class="ie_06"><p>본사이트는 IE8이상의 브라우저에 최적화가 되어있습니다.<br/>현재 브라우저 버전에서는 편리하게 이용 하실 수 없습니다. Windows Internet Explorer를 업데이트를 받으세요</p><a href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie" target="_blank" class="btn_update">Internet Explorer 다운로드 받기</a></div>');
			</script>
		<![endif]-->

		<!-- common -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/prefixfree.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/menu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/sns.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/common.js"></script>

		<c:if test="${yh:getProperty('Globals.pageScroll') eq 'Y' }">
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/page_scroll_indicator.js"></script>
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/js/jquery/progress_bar.css" />
		<script type="text/javascript">
			$(document).ready(function() {
				PageScrollIndicator.createProgressBar("#wrap", "#container", '2px', '#00bcd4');
			});
		</script>
		</c:if>
		<%@ include file="/common/jsp/component/default.jsp" %>

	</head>
	<body>
		<div id="skipNav">
			<a href="#container">본문 바로가기</a>
			<a href="#lnbWrap">주메뉴 바로가기</a>
		</div>

		<div id="wrap">
			<tiles:insertAttribute name="header" />
			<div id="container" class="clFix">
				<tiles:insertAttribute name="left" />
				<section id="content">
					<tiles:insertAttribute name="spot" />
					<tiles:insertAttribute name="tab" />
					<div id="conts">
						<tiles:insertAttribute name="body" />
					</div>
					<!--<tiles:insertAttribute name="research" />-->
				</section>
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
		<tiles:insertAttribute name="menu" />
	</body>
</html>
