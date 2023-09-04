<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<%
	/**
	 * MENU LIST BREAD CRUMB
	 * Tree 형태의 리스트 오브젝트를 ul, li 태그로 변환합니다.
	 * 화면에 출력하는 Tree의 Depth는 본 파일을 include하는 jsp에서 변수명 p_maxDepth에,
	 * Tree Object는 변수명 p_elements에 담아 넘겨주세요.
	 * p_maxDepth의 경우 설정하지 않으면 Tree의 마지막 Depth까지 출력합니다.
	 *
	 * 참고: /csc/jsp/layouts/left.jsp
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.08.25
	 * @version 1.0
	 * @see egovframework.portal.content.vo.MenuVO
	 */
%>

<c:if test="${empty p_depth}">
	<c:set var="p_depth" value="${1}" scope="request" />
</c:if>
<c:set var="isFirstDepth" value="${p_depth == 1}" />

<c:if test="${empty p_maxDepth || p_depth <= p_maxDepth}">

	<ul <c:if test="${p_depth == 1}">class="snb"</c:if>>
		<c:forEach var="element" items="${p_elements}" varStatus="status">
			<c:set var="subs_curMid" value="${fn:substring(p_curMid, 0, element.menuLevel * 2)}" />
			<c:set var="subs_mid" value="${fn:substring(element.mId, 0, element.menuLevel * 2)}" />
			<c:set var="isActiveMenu" value="${subs_mid == subs_curMid}" />
			<c:set var="isNotLoginFormMenu" value="${element.mId ne '0707010000'}" />
			<c:set var="isNotFindAccountMenu" value="${element.mId ne '0707020000'}" />
			<c:set var="isNotJoinMenu" value="${element.mId ne '0707040000'}" />
			<c:set var="anchor_class" value="" />
			<c:if test="${isActiveMenu}"><c:set var="anchor_class" value="on " /></c:if>
			<c:if test="${(element.menuLevel == '2' || (element.menuLevel == '1' && p_builtin )) && not empty element.subMenu}"><c:set var="anchor_class" value="${anchor_class}state" /></c:if>

			<c:if test="${empty sessionScope.userIdLogined || (isNotLoginFormMenu && isNotFindAccountMenu && isNotJoinMenu)}">
			<li>
				<c:choose>
					<c:when test="${element.menuType == 'L'}">
						<a href="${element.linkUrl}" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if> target="${element.target}">
							${element.menuName}
							<c:if test="${element.target == '_blank'}"><img src="/common/img/common/depth01_bul_blank.gif" alt="새창 열림" class="mL5"/></c:if>
						</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/${p_siteCode}/contents.do?mId=${element.mId}" <c:if test="${not empty anchor_class}">class="${anchor_class}"</c:if>>${element.menuName}</a>
					</c:otherwise>
				</c:choose>

				<c:if test="${isActiveMenu && not empty element.subMenu}">
					<c:set var="p_depth" value="${element.menuLevel + 1}" scope="request" />
					<c:set var="p_elements" value="${element.subMenu}" scope="request" />
					<jsp:include page="leftBreadCrumb.jsp" />
				</c:if>
			</li>
			</c:if>
		</c:forEach>
	</ul>
</c:if>
