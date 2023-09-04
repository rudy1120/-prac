<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<html lang="ko">
	<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${activeMenu.subHeadTitle2}| YH데이타베이스</title>
	
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/common.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/all_layout.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/board.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/common/css/application.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/layout.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/sub.css" />
	<link media="all" type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/portal/css/sub${fn:substring(menuVO.mId, 0, 2)}.css" />
	
	
	<!-- common -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery-ui-1.10.0.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/jquery/jquery.form.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/menu.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/sns.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/ie-checker.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/common/js/common/common.js"></script>
	
	<%@ include file="/common/jsp/component/default.jsp"%>
	</head>
	<body>
		<div id="jump_menu">
			<h1>바로가기 메뉴</h1>
			<ul>
				<li><a href="#conts">본문 바로가기</a></li>
				<li><a href="#lnb_wrap">주메뉴 바로가기</a></li>
			</ul>
		</div>
		<div id="wrap">
			<div id="container" class="cB">
			<section class="snb_wrap">
				<h2>단위 기능</h2>
				<nav>
					<ul class="snb">
						<li>
									<a href="/portal/contents.do?mId=0101000000">회원가입</a>
						</li>
						<li>
									<a href="/portal/contents.do?mId=0103000000">회원 로그인</a>
						</li>
						<li>
									<a href="/portal/contents.do?mId=0104000000">회원 수정</a>
						</li>
						<li>
									<a href="/portal/contents.do?mId=0105000000">회원 찾기/비번 설정</a>
						</li>
						<li>
									<a href="/portal/contents.do?mId=0106000000" class="on">YHDCalendar</a>
						</li>
					</ul>
				</nav>
			</section>
			<section id="content">
				<header id="tit_wrap" class="cB">
					<h3>YHDCalendar</h3>
					<ul>
						<li>단위 기능 </li>
						<li> YHDCalendar</li>
					</ul>
					<div class="sub_acc">
						<ul>
							<li><a href="#" onclick="window.open('/common/printPage.jsp', 'printPopup', 'width=730,height=600,top=100,left=100,scrollbars=yes,resizable=yes');" title="새창 열림">본문 인쇄</a></li>
							<li><a href="#" title="새창 열림" id="snsTwitter">트위터</a></li>
							<li><a href="#" title="새창 열림" id="snsFacebook">페이스북</a></li>
						</ul>
					</div>
				</header>
				<div id="conts">
