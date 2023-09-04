<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 메뉴 리스트에서 탭 메뉴를 추출
	 * t_dispLevel는 현재 Left Side에 출력되는 메뉴의 레벨
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2015.09.01		J.Ryeon Lee		최초 생성 및 코드 작성, 컴포넌트화
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2015.09.01
	 */
%>

<c:set var="subsd_cmId" value="${fn:substring(t_mId, 0, t_dispLevel * 2)}" />
<c:if test="${empty t_tabMenus}">
	<c:forEach var="element" items="${targets}" varStatus="status">
		<c:choose>
			<c:when test="${element.menuLevel < t_dispLevel}">
				<c:set var="targets" value="${element.subMenu}" scope="request" />
				<jsp:include page="extractTabs.jsp" />
			</c:when>
			<c:when test="${element.menuLevel == t_dispLevel}">
				<c:set var="subsd_mId" value="${fn:substring(element.mId, 0, t_dispLevel * 2)}" />
				<c:if test="${subsd_mId == subsd_cmId}">
					<c:set var="t_tabMenus" value="${element.subMenu}" scope="request" />
				</c:if>
			</c:when>
			<c:otherwise>

			</c:otherwise>
		</c:choose>
	</c:forEach>
</c:if>
