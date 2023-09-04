<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 컨텐츠 화면
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.10.07		엄동건			최초 생성 및 코드 작성
	 * 2017.09.15		J.Ryeon Lee		공공누리 유형 및 CCL 마크 컴포넌트화
	 *
	 *
	 * @author 엄동건
	 * @since 2014.10.07
	 */
%>
<c:choose>
	<c:when test="${menuVO.menuType eq 'F'}">
		<jsp:include page="${menuVO.contentFilePath}" />
	</c:when>
	<c:when test="${menuVO.menuType eq 'C'}">
		<c:choose>
			<c:when test="${empty menuVO.cmsContentData}">
				<jsp:include page="/common/ready.jsp" />
			</c:when>
			<c:otherwise>
				${menuVO.cmsContentData}
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<jsp:include page="/common/ready.jsp" />
	</c:otherwise>
</c:choose>

<c:set var="p_display_ccl" value="${menuVO.cclType != '0'}" scope="request"/>
<c:set var="p_ccl_type" value="${menuVO.cclType}" scope="request"/>
<c:set var="p_display_nuri" value="${menuVO.menuType != 'B' && menuVO.nuriType != '0'}" scope="request"/>
<c:set var="p_nuriType" value="${menuVO.nuriType}" scope="request"/>
<%@include file="/common/jsp/component/articleUsePolicy.jsp"%>
