<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<div id="side_nav">
	<div class="side_nav_inner">
		<ul><li class="home"><a href="/history/main.do">홈으로</a></li>
		<c:set var="p_elements" value="${menus}" scope="request" />
		<c:set var="p_curMid" value="${curMid}" scope="request" />
		<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
		<c:set var="p_maxDepth" value="${2}" scope="request" />
		<c:set var="p_depth" value="${1}" scope="request" />
		<jsp:include page="/history/jsp/layouts/component/subpathBody.jsp" />
		</ul>
	</div>
</div>
