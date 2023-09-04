<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>

<table class="tableSt_list row_over">
	<caption>게시글 목록</caption>
	<thead>
		<tr>
			<c:if test="${useBundleDel && !p_fromView}">
				<th scope="col" class="w2"><input type="checkbox" name="getCheckedAll" id="getCheckedAll"/></th>
			</c:if>
			<th scope="col" class="w5">번호</th>
			<c:if test="${useCategory && useCustom}">
				<th scope="col" class="w10">분류</th>
			</c:if>
			<th scope="col">제목</th>
			<c:if test="${useCategory && useDept}">
				<th scope="col" class="w10">담당부서</th>
			</c:if>
			<th scope="col" class="w8">작성자</th>
			<c:if test="${bbsConfigVO.ptPublicYn == 'Y'}">
				<th scope="col" class="w5">공개</th>
			</c:if>
			<th scope="col" class="w10">작성일</th>
			<th scope="col" class="w8">첨부</th>
		</tr>
	</thead>
	<tbody>
		<%-- ================= 공지사항 ================= --%>
		<c:set var="elements" value="${noti}" scope="request"/>
		<c:set var="isNoti" value="Y" scope="request"/>
		<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/basicBody.jsp"/>
		<%-- ================= 게시글 ================= --%>
		<c:set var="elements" value="${result}" scope="request"/>
		<c:set var="isNoti" value="N" scope="request"/>
		<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/basicBody.jsp"/>
	</tbody>
</table>

<c:if test="${empty noti && empty result}">
	<div class="no_data">등록된 게시물이 없습니다.</div>
</c:if>

<c:if test="${not empty result}">
	<div class="box_page">
		<ui:pagination paginationInfo="${paginationInfo}" type="sys" jsFunction="goPage"/>
	</div>
</c:if>
