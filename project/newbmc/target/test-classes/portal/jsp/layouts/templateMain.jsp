<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<compress:html compressJavaScript="${yh:isLiveMode()}" enabled="${yh:isLiveMode()}" jsCompressor="closure" closureOptLevel="simple">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="format-detection" content="telephone=no">
		<title>홈페이지에 방문하신 것을 환영 합니다.</title>
		
		<!-- css -->
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/jquery.bxslider.css">
		<link media="all" type="text/css" rel="stylesheet"  href="${pageContext.request.contextPath}/common/css/common.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/layout.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/main.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_responsive.css">
		<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/responsive.css">
	
		<!--[if lt IE 9]>
			<script type="text/javascript" src="/common/js/common/html5.js"></script>
		<![endif]-->

		<!--[if lt IE 8]>
			<link rel="stylesheet" type="text/css" href="/common/css/ie_access.css" />
			<script type="text/javascript">
				document.write('<div  class="ie_06"><p>본사이트는 IE8이상의 브라우저에 최적화가 되어있습니다.<br/>현재 브라우저 버전에서는 편리하게 이용 하실 수 없습니다. Windows Internet Explorer를 업데이트를 받으세요</p><a href="http://windows.microsoft.com/ko-KR/internet-explorer/downloads/ie" target="_blank" class="btn_update">Internet Explorer 다운로드 받기</a></div>');
			</script>
		<![endif]-->

		<!-- js -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/prefixfree.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.bxslider.min.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/common.js"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/menu.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/portal/js/common.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/portal/js/main.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/banner.js"></script>
		<%@ include file="/common/jsp/component/default.jsp" %>
	</head>
	<body>
		<div id="skipNav">
			<a href="#m_conts">본문 바로가기</a>
			<a href="#lnbWrap">주메뉴 바로가기</a>
		</div>

		<div id="wrap">
			<tiles:insertAttribute name="header" />
			<main id="m_conts" role="main">
				<tiles:insertAttribute name="body" />
			</main>
			<tiles:insertAttribute name="footer" />
		</div>
		<tiles:insertAttribute name="menu" />
	</body>
</html>
</compress:html>