<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%--
*********************************************************************
* 파  일  명 : list.jsp
* 설      명 : 부서별소개정보 리스트
* 작  성  자 : 김장섭
* 작  성  일 : 2015.11.26
* ----------------------------------
* 2015.11.30	김현호		디자인퍼블리싱
* ----------------------------------
*********************************************************************
--%>

<c:forEach var="result" items="${result}" varStatus="status">
	<h4>${result.inJobname}</h4>
	<div class="dept_work">
		<edosiFmt:formatHtml value="${result.inJobcontent}" />
	</div>
</c:forEach>