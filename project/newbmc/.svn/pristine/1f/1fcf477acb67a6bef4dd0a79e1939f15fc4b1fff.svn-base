<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<table class="bod_list bod_minwon">
	<caption class="hidden">
		${activeMenu.menuName} 게시판 목록을 번호,
		<c:if test="${useCategory && useCustom}">분류,</c:if>
		제목, 파일,
		<c:if test="${useCategory && useDept}">담당부서,</c:if>
		작성자, 답변, 작성일, 조회수 항목별 순서대로 제공하는 표입니다.
	</caption>
	<thead>
		<tr>
			<c:set var="colLength" value="7"/>
			<th scope="col" class="list_num">번호</th>
			<c:if test="${useCategory && useCustom}">
				<th scope="col" class="wp13">분류</th>
				<c:set var="colLength" value="${colLength + 1}"/>
			</c:if>
			<th scope="col" class="list_tit">제목</th>
			<th scope="col" class="list_file">파일</th>
			<c:if test="${useCategory && useDept}">
				<th scope="col" class="wp13">담당부서</th>
				<c:set var="colLength" value="${colLength + 1}"/>
			</c:if>
			<th scope="col" class="list_write">작성자</th>
			<th scope="col" class="list_answer">답변</th>
			<th scope="col" class="list_date">작성일</th>
			<th scope="col" class="list_hit">조회수</th>
		</tr>
	</thead>
	<tbody>
		<%-- 공지 --%>
		<c:set var="elements" value="${noti}" scope="request"/>
		<jsp:include page="./minwonBody.jsp"/>
		<%-- 게시글 --%>
		<c:set var="elements" value="${result}" scope="request"/>
		<jsp:include page="./minwonBody.jsp"/>
		<c:if test="${empty noti && empty result}">
			<tr>
				<td colspan="${colLength}">등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
	</tbody>
</table>

<c:if test="${not empty noti || not empty result}">
	<div class="bod_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="base" jsFunction="goPage"/>
	</div>
</c:if>
