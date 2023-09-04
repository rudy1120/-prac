<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>

<header id="header">
	<%-- headerTop 영역 include --%>
	<jsp:include page="/bmc/jsp/layouts/component/headerTop.jsp" />
	<!-- headerBody 영역 BREAD CRUMBS -->
	<div class="headerBody">
		<div class="headerInnr" id="headerInnrDiv">
			<div class="logo">
				<h1>
					<a href="/bmc/main.do">
						<img src="/bmc/images/logo.png" alt="BMC 부산도시공사" id="headLogo">
					</a>
				</h1>
			</div>
			<button id="srchBtn"></button>
			<button id="engBtn"></button>
			<button id="navBtn">메뉴열기</button>
			<div class="gnb_wrap">
				<Nav id="topNav">
					<c:set var="p_elements" value="${menus}" scope="request" />
					<c:set var="p_curMid" value="${curMid}" scope="request" />
					<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
					<c:set var="p_maxDepth" value="${3}" scope="request" />
					<c:set var="p_depth" value="${1}" scope="request" />
					<jsp:include page="/bmc/jsp/layouts/component/headerBody.jsp" />
				</Nav>
			</div>
		</div>
	</div>
	<div class="topNavBack"></div>
</header>