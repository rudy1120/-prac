<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * Common Tab Menu Frame
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.08.26		J.Ryeon Lee		최초 생성 및 코드 작성, 컴포넌트화
	 * 2015.09.01		J.Ryeon Lee		탭 메뉴 레벨을 선택할 수 있도록 수정
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.08.26
	 */
%>

<!-- 4 Depth Menu -->
<c:set var="t_dispLevel" value="3" scope="request" />
<c:set var="t_mId" value="${activeMenu.mId}" scope="request" />
<c:set var="targets" value="${menus}" scope="request" />
<jsp:include page="/common/jsp/layouts/component/extractTabs.jsp" />

<c:if test="${not empty t_tabMenus}">
	<nav class="tab_depth04">
		<c:set var="p_elements" value="${t_tabMenus}" scope="request" />
		<c:set var="p_class" value="list0${fn:length(t_tabMenus) < 5 ? fn:length(t_tabMenus) : '4'}" scope="request" />
		<jsp:include page="/common/jsp/layouts/component/tabBreadCrumb.jsp" />
		<div class="cleB"></div>
	</nav>
</c:if>

<!-- 5 Depth Menu -->
<c:set var="t_tabMenus" value="${null}" scope="request" />
<c:set var="t_dispLevel" value="4" scope="request" />
<c:set var="t_mId" value="${activeMenu.mId}" scope="request" />
<c:set var="targets" value="${menus}" scope="request" />
<jsp:include page="/common/jsp/layouts/component/extractTabs.jsp" />

<c:if test="${not empty t_tabMenus}">
	<div class="tab_depth05">
		<c:set var="p_elements" value="${t_tabMenus}" scope="request" />
		<c:set var="p_class" value="list0${fn:length(t_tabMenus) < 5 ? fn:length(t_tabMenus) : '4'}" scope="request" />
		<jsp:include page="/common/jsp/layouts/component/tabBreadCrumb.jsp" />
		<div class="cleB"></div>
	</div>
</c:if>

