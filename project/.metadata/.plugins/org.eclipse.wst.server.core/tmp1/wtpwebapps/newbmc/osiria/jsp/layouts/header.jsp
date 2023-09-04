<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/osiria/jsp/common/taglib.jsp"%>

<header id="header">
	<div class="headerBody">
		<div class="headerInnr">
			<h1 class="logo">
				<a href="/osiria/main.do"><img src="/osiria/img/logo.png" alt="BMC 오시리아관광단지"></a>
			</h1>
			<button type="button" class="btnNav">
				메뉴보기
				<span>
					<span class="btnBar"></span>
					<span class="btnBar"></span>
					<span class="btnBar"></span>
				</span>
			</button>
			
			<nav id="topNav">					
				<div class="topNavInnr">
					
						<c:set var="p_elements" value="${menus}" scope="request" />
						<c:set var="p_curMid"   value="${curMid}" scope="request" />
						<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
						<c:set var="p_maxDepth" value="${2}" scope="request" />
						<c:set var="p_depth"    value="${1}" scope="request" />
						<jsp:include page="/osiria/jsp/layouts/component/headerBody.jsp" />
					
				</div>
			</nav>
		</div>
	</div>		
</header>