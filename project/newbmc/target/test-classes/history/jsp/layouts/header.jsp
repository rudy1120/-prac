<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<header id="header">
	<div class="header_inner">
		<div class="logo">
			<a href="/history/main.do"><img src="/history/img/common/logo.png" alt="부산도시공사 로고"></a>
        </div>
        <button id="top_nav_btn">메뉴열기
            <span></span>
            <span></span>
            <span></span>
        </button>
        <nav id="top_nav">
        	<c:set var="p_elements" value="${menus}" scope="request" />
			<c:set var="p_curMid"   value="${curMid}" scope="request" />
			<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
			<c:set var="p_maxDepth" value="${2}" scope="request" />
			<c:set var="p_depth"    value="${1}" scope="request" />
			<jsp:include page="/history/jsp/layouts/component/headerBody.jsp" />
        </nav>
    </div>
    <div id="back"></div>
</header>
