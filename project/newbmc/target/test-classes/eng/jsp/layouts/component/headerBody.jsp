<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request"/>
</c:if>
<c:set var="isFirstDepth"  value="${p_depth == 1}" />
<c:set var="isSecondDepth" value="${p_depth == 2}" />
<c:set var="isThirdDepth" value="${p_depth == 3}" />

<%-- <c:set var="isSide"    value="${isSide == 1}" />
<c:set var="isGnb"    value="${isGnb == 1}" /> --%>

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">
	<c:set var="ul_class" value="" />
	<c:if test="${isFirstDepth}">
		<c:set var="ul_class" value="gnb-1depth" />
	</c:if>
	<c:if test="${isSecondDepth}">
		<c:set var="ul_class" value="gnb-2depth" />
	</c:if>
	<c:if test="${isThirdDepth and isSide eq 'Y'}">
		<c:set var="ul_class" value="gnb-3depth" />
	</c:if>
 	<c:if test="${isThirdDepth and isSide eq 'N'}">
		<c:set var="ul_class" value="depth03" />
	</c:if>

	<c:if test="${isFirstDepth}">
		<ul <c:if test="${not empty ul_class}">class="${ul_class}"</c:if>>
	</c:if>
	
	<c:if test="${isSecondDepth and isSide eq 'Y'}">
		<ul <c:if test="${not empty ul_class}">class="${ul_class}"</c:if> style="display: none;">		
	</c:if>
	<c:if test="${isSecondDepth and isSide eq 'N'}">
		<ul <c:if test="${not empty ul_class}">class="${ul_class}"</c:if> style="display: none; height: 320px; padding-top: 0px; margin-top: 0px; padding-bottom: 0px; margin-bottom: 0px;">
		<li>
			<div class="left-bg"></div>
			<c:choose>
				<c:when test="${p_mId == '0100000000'}">
					<p class="left-title"><span>About BMC</span></p>
            		<p class="left-subtitle">We think of the future of Busan</p>
				</c:when>
				<c:when test="${p_mId == '0200000000'}">
		            <p class="left-title"><span>Achievements</span></p>
		            <p class="left-subtitle">We Connect the City and People</p>
				</c:when>
				<c:when test="${p_mId == '0300000000'}">
		            <p class="left-title"><span>BMC Projects</span></p>
		            <p class="left-subtitle">My Home, Our Workplace, Everyone's City</p>
				</c:when>
				<c:when test="${p_mId == '0400000000'}">
		            <p class="left-title"><span>ESG Management of BMC</span></p>
		            <p class="left-subtitle">Sustainable Management of BMC</p>
				</c:when>												
			</c:choose>
		</li>			
	</c:if>

	<c:if test="${isThirdDepth}">
		<ul <c:if test="${not empty ul_class}">class="${ul_class}"</c:if>>
	</c:if>
	
	<c:forEach var="element" items="${p_elements}" varStatus="status">
		<li>
			<c:choose>
				<c:when test="${element.menuType == 'L'}">
					<a href="${element.linkUrl}" target="${element.target}" title="새창열림" ><span>${element.menuName}</span><i class="blank">새창</i></a>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${isFirstDepth and isSide eq 'Y'}">						
						<a href="/${p_siteCode}/contents.do?mId=${element.mId}">
						<i class="highlight">${element.menuName}</i><span class="plus-btn"></span></a>																		
					</c:when>					
					<c:otherwise>					
						<a href="/${p_siteCode}/contents.do?mId=${element.mId}" title="${element.menuName}">${element.menuName}</a>
					</c:otherwise>
					
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:if test="${not empty element.subMenu}">
				<c:set var="p_depth"     value="${element.menuLevel + 1}" scope="request" />
				<c:set var="p_elements"  value="${element.subMenu}"       scope="request" />
				<c:set var="p_headTitle" value="${element.menuName}"      scope="request" />
				<c:set var="p_mId" value="${element.mId}" scope="request" />
				<jsp:include page="headerBody.jsp" />
			</c:if>			
		</li>
		</c:forEach>
	</ul>
</c:if>