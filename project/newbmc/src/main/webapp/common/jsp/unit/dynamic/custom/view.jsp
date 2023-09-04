<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 목록(사용자) / 커스텀
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.05.09		J.Ryeon Lee		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2016.05.09
	 */
%>

<jsp:include page="${tableDef.viewPath}">
	<jsp:param value="${siteCode }" name="siteCode"/>
	<jsp:param value="${tableDef }" name="tableDef"/>
	<jsp:param value="${entity }" name="entity"/>
	<jsp:param value="${searchVO }" name="searchVO"/>
</jsp:include>