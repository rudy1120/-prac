<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<div class="nav_wrap">
	<ul class="navbar lnb-1depth">
		<li class="lnb-1depth_item home">
			<a class="nav_home" href="/osiria/main.do"><span class="home">처음으로</span></a>
		</li>
			<c:set var="p_elements" value="${menus}" scope="request" />
			<c:set var="p_curMid" value="${curMid}" scope="request" />
			<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
			<c:set var="p_maxDepth" value="${2}" scope="request" />
			<c:set var="p_depth" value="${1}" scope="request" />
			<jsp:include page="/osiria/jsp/layouts/component/subpathBody.jsp" />
	</ul>
</div>
			    
			    


