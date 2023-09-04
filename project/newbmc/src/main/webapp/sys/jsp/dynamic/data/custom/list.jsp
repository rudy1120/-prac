<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 동적 현황관리 목록(사용자) / 커스텀
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2016.11.18		권태성		최초 생성 및 코드 작성
	 *
	 *
	 *
	 * @author 권태성
	 * @since 2016.11.18
	 */
%>

<jsp:include page="${tableDef.sysListPath}">
	<jsp:param value="${tableDef }" name="tableDef"/>
	<jsp:param value="${listOrder }" name="listOrder"/>
	<jsp:param value="${searchVO }" name="searchVO"/>
	<jsp:param value="${resultCnt }" name="resultCnt"/>
	<jsp:param value="${result }" name="result"/>
	<jsp:param value="${page }" name="page"/>
	<jsp:param value="${paginationInfo }" name="paginationInfo"/>
</jsp:include>
