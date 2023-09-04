<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * Left Side Menu
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.08.24
	 */
%>

<section id="snbWrap">
	<c:forEach var="menu" items="${menus}" varStatus="status">
		<c:if test="${fn:substring(curMid, 0, 2) == fn:substring(menu.mId, 0, 2)}">
			<h2>${menu.menuName}</h2>
			<c:set var="p_elements" value="${menu.subMenu}" scope="request" />
			<c:set var="p_curMid" value="${curMid}" scope="request" />
			<c:set var="p_siteCode" value="${siteCodeFull}" scope="request" />
			<c:set var="p_maxDepth" value="${3}" scope="request" />
			<c:set var="p_depth" value="${1}" scope="request" />
			<nav>
			<jsp:include page="/common/jsp/layouts/component/leftBreadCrumb.jsp" />
			</nav>
		</c:if>
	</c:forEach>
</section>
