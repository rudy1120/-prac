<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/osiria/jsp/common/taglib.jsp"%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:set var="isFirstDepth"  value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<c:set var="ul_class" value="" />
	<c:if test="${isFirstDepth}">
		<c:set var="ul_class" value="topNavMenus" />
	</c:if>
	<c:if test="${isSecondDepth}">
		<c:set var="ul_class" value="topNavSub" />
	</c:if>
	
	<c:if test="${isFirstDepth}">
	<ul <c:if test="${not empty ul_class}">class="${ul_class}"</c:if>
	>
	</c:if>
	
	<c:if test="${isSecondDepth}">
	<div <c:if test="${not empty ul_class}">class="${ul_class}"</c:if>
	><ul>
	</c:if>
		
		<c:forEach var="element" items="${p_elements}" varStatus="status">
		<li>
			<c:choose>
				<c:when test="${element.menuType == 'L'}">
					<a href="${element.linkUrl}" target="${element.target}" title="새창열림" ><span>${element.menuName}</span><i class="blank">새창</i></a>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${isFirstDepth}">
						<a href="/${p_siteCode}/contents.do?mId=${element.mId}"><span>
						<strong>${element.menuName}</strong></span></a>
					</c:when>
					<c:otherwise>
						<a href="/${p_siteCode}/contents.do?mId=${element.mId}"><span>
						${element.menuName}</span></a>
					</c:otherwise>
					
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty element.subMenu}">
				<button type="button" class="btnMenuDropDown">하위메뉴 축소됨</button>
				<c:set var="p_depth"     value="${element.menuLevel + 1}" scope="request" />
				<c:set var="p_elements"  value="${element.subMenu}"       scope="request" />
				<c:set var="p_headTitle" value="${element.menuName}"      scope="request" />
				<jsp:include page="headerBody.jsp" />
			</c:if>
		</li>
		</c:forEach>
	</ul>
	
	<c:if test="${isSecondDepth}">
	</div>
	</c:if>
</c:if>
