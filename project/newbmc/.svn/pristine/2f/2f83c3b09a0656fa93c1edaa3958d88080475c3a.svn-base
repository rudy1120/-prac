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
	 * 참고: /common/jsp/layout/tab.jsp
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.08.25		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.08.25
	 * @version 1.0
	 * @see egovframework.portal.content.vo.MenuVO
	 */
%>

<ul class="${p_class}">
	<c:forEach var="element" items="${p_elements}" varStatus="status">
		<c:set var="isActiveMenu" value="${fn:substring(element.mId, 0, element.menuLevel * 2) == fn:substring(curMid, 0, element.menuLevel * 2)}" />
		<li>
			<c:choose>
				<c:when test="${element.menuType == menuType.LINK}">
					<a href="${element.linkUrl}" target="${element.target}" title="${element.target == '_blank' ? '새창으로열림' : ''}" <c:if test="${isActiveMenu}">class="on"</c:if>>${element.menuName}</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/${p_siteCode}/contents.do?mId=${element.mId}" <c:if test="${isActiveMenu}">class="on"</c:if>>${element.menuName}</a>
				</c:otherwise>
			</c:choose>
		</li>

		<c:if test="${element.menuLevel == 4 && isActiveMenu && empty finalDepthMenus && not empty element.subMenu}">
			<c:set var="finalDepthMenus" value="${element.subMenu}" scope="request" />
		</c:if>
	</c:forEach>
</ul>