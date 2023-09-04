<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko" >

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">

	<title>오시리아관광단지</title>

    <!-- css -->
   
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/osiria/css/swiper.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/slick/slick.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/slick/slick-theme.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/osiria/font/font.css">
	<%-- <link rel="shortcut icon" href="${pageContext.request.contextPath}/osiria/img/favicon.ico"> --%>

    <!-- js -->
	<script src="${pageContext.request.contextPath}/osiria/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/global.js"></script>
	<script src="${pageContext.request.contextPath}/osiria/js/swiper.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/osiria/slick/slick.min.js"></script>
	 
	<script type="text/javascript">
	$(document).ready(function(){
	  $('.Main_businessWrap_mobile').slick({
	    dots: true,
	    infinite: true
	  });
	});
	</script>
	<!--[if lt IE 9]>
		<script src="js/html5shiv.min.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
		<%@ include file="/osiria/jsp/component/default.jsp" %>
	</head>
	<body>
		<div class="skipToNav">
			<a href="#container" onclick="fn_skipNav();">본문 바로가기</a>
		</div>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
		<tiles:insertAttribute name="footer" />
	</body>
</html>
