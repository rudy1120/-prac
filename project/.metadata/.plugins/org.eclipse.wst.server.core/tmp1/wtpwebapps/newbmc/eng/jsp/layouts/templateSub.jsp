<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor" %>
<%@ include file="/bmc/jsp/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en" id="busanportal">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="author" content="BMC" />
	<meta name="keywords" content="BMC" />
	<meta name="description" content="BMC ENG Website" />
	<meta name="copyright" content="156 Sincheon-daero, Busanjin-gu, Busan, 47281, Republic of Korea" />
	<meta property="og:type" content="website">
	<meta property="og:title" content="BMC">
	<meta property="og:description" content="BMC EN Website">
	<meta property="og:image" content="">
	<meta property="og:url" content="">
	<title>BMC | Eng</title>
	
    <!-- css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/reset.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/layout.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/sub.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/mq.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/swiper-bundle.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/eng/css/jquery-ui.css">
	<link rel="shortcut icon" href="/eng/img/common/favicon.ico">

    <!-- js -->
	<script src="${pageContext.request.contextPath}/eng/js/jquery/jquery-1.11.3.min.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/jquery/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/swiper-bundle.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/jquery/ScrollMagic.min.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/jquery/TweenMax.min.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/animation.gsap.min.js"></script>
	<script src="${pageContext.request.contextPath}/eng/js/common/common.js"></script>	

	<!--[if lt IE 9]>
		<script src="js/html5shiv.min.js"></script>
		<script src="js/respond.min.js"></script>
		<![endif]-->
	<%@ include file="/eng/jsp/component/default.jsp" %>
</head>
<body>
	<div id="skipNav"><a href="#contents">본문내용 바로가기</a></div>
	<div id="wrap">
		<tiles:insertAttribute name="header" />	
		<script>
		  $(window).scroll(function(){
		    var sct = $(window).scrollTop();
		    
		    if(sct>=100){
		      $(".headWrap").addClass("hdopacity");
		    } else {
		      $(".headWrap").removeClass("hdopacity");
		    }        
		  });
		</script>
		<div id="contents">
			<tiles:insertAttribute name="subvisual"/>
			<tiles:insertAttribute name="subpath"/>			
			<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />
			<div id="conts" class="conts${fn:contains(headTitle[1], 'History') ? ' history' : fn:contains(headTitle[1], 'Awards') ? ' awards' : fn:contains(activeMenu.spotTitle, 'BMC Projects') ? ' conts100' : ''}">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<tiles:insertAttribute name="footer" />		
	</div>		
</body>

</html>
