<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/bmc/jsp/common/taglib.jsp"%>

<c:forEach var="element" items="${bannerList}">
	<c:choose>
		<c:when test="${not empty element.prmtUrl}">
			<a href="${fn:escapeXml(element.prmtUrl)}" <c:if test="${element.blankYn == 'Y'}">target="_blank" </c:if> class="banner">
		</c:when>
		<c:otherwise>
			<a href="#mainEvent" class="banner">
		</c:otherwise>
	</c:choose>
			<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
				<c:param name="param_atchFileId" value="${element.attachId}"/>
				<c:param name="mode" value="origin"/>
			</c:import>
			</a>
</c:forEach>
