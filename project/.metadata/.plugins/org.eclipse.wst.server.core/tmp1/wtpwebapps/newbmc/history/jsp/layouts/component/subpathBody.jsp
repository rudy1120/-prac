<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:set var="isFirstDepth"  value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />

<c:set var="subyn" value="N" />
<c:set var="headTitle" value="${fn:split(activeMenu.subHeadTitle, '>')}" />
<c:if test="${isFirstDepth}">
	<c:set var="headTitle" value="${headTitle[0]}"/>
</c:if>
<c:if test="${isSecondDepth}">
	<c:set var="headTitle" value="${headTitle[1]}"/>
</c:if>

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">	
	<li>
		<button>${headTitle}</button>
		<ul class="side_nav_sub">
			<c:forEach var="element" items="${p_elements}" varStatus="status">
				<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
				<c:set var="subs_mid" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
				<c:set var="isActiveMenu" value="${subs_mid == subs_curMid}" />
			<li <c:if test="${isActiveMenu}">class="active"</c:if>>
				<c:choose>
					<c:when test="${element.menuType == 'L'}">
						<a <c:if test="${isActiveMenu}">class="active"</c:if> href="${element.linkUrl}" target="${element.target}">
							${element.menuName}
							<c:if test="${element.target == '_blank'}"><i class="blank">새창</i></c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a <c:if test="${isActiveMenu}">class="active"</c:if> href="${pageContext.request.contextPath}/${p_siteCode}/contents.do?mId=${element.mId}">${element.menuName}</a>
					</c:otherwise>
				</c:choose>
				<c:if test="${isActiveMenu && not empty element.subMenu}">
					<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
					<c:set var="p_elements" value="${element.subMenu}" scope="request" />
					<c:set var="subyn" value="Y" />
				</c:if>
			</li>
			</c:forEach>
		
		
		</ul>
	</li>
	
</c:if>
<c:if test="${not empty element.subMenu}">
	<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
	<c:set var="p_elements" value="${element.subMenu}" scope="request" />
	<c:set var="subyn" value="Y" />
</c:if>
<c:if test="${subyn eq 'Y' }">
	<jsp:include page="subpathBody.jsp" />
</c:if>