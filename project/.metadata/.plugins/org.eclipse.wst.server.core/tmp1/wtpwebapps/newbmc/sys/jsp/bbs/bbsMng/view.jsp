<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/sys/jsp/bbs/bbsMng/component/variables.jsp" %>
<%
	/**
	 * 게시판 목록 상세
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.14		김혜민			최초 생성 및 코드 작성
	 * 2015.07.29		J.Ryeon Lee		공지글 및 삭제된 글 답글 기능 숨김, 공지글은 수정/삭제만 가능
	 * 2015.08.03		J.Ryeon Lee		목록 출력 코드 외부 파일화
	 * 2017.05.15		J.Ryeon Lee		script 분리
	 *
	 *
	 * @author 김혜민
	 * @since 2014.10.15
	 */
%>
<c:set var="formId" value="viewForm"/>
<c:set var="isMovable" value="${!useCategory && bbsMngView.isDel == 'N' && replyCnt == 0}"/>

<h2>${bbsConfigVO.ptTitle}</h2>
<form:form commandName="searchVO" id="list" name="list" action="/sys/${siteCode}/bbs/bbsMng/list.do?mId=${menuVO.mId}" method="post">
	<form:hidden path="ptIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
</form:form>
<form:form commandName="searchVO" id="${formId}" name="${formId}" action="/sys/${siteCode}/bbs/bbsMng/view.do?mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="ptIdx"/>
	<form:hidden path="bIdx"/>
	<form:hidden path="page"/>
	<form:hidden path="searchType"/>
	<form:hidden path="searchTxt"/>
	<form:hidden path="searchDelete"/>
	<form:hidden path="searchCategory"/>
	<form:hidden path="searchDept"/>
	<input type="hidden" name="commentPage" value="${commentPage}"/>
</form:form>

	<%-- ============================== VIEW ============================== --%>
	<c:choose>
		<c:when test="${isPhoto}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/view/photo.jsp"/>
		</c:when>
		<c:when test="${isMovie}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/view/movie.jsp"/>
		</c:when>
		<c:when test="${isBlog}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/view/blog.jsp"/>
		</c:when>
		<c:when test="${isMinwon}">
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/view/minwon.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="/sys/jsp/bbs/bbsMng/component/view/basic.jsp"/>
		</c:otherwise>
	</c:choose>

	<%-- ============================== COMMENT LIST ============================== --%>
	<c:if test="${bbsConfigVO.ptCommentYn == 'Y'}">
		<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/comment.jsp"/>
	</c:if>

	<%-- ============================== BOARD LIST ============================== --%>
	<c:if test="${fn:substring(bbsConfigVO.ptOutFields, 0, 1) == 'Y'}">
		<c:set var="p_formId" value="${formId}" scope="request"/>
		<c:set var="p_fromView" value="${true}" scope="request"/>
		<c:choose>
			<c:when test="${isPhoto}">
				<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/photo.jsp"/>
			</c:when>
			<c:when test="${isMovie}">
				<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/movie.jsp"/>
			</c:when>
			<c:when test="${isBlog}">
				<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/blog.jsp"/>
			</c:when>
			<c:when test="${isMinwon}">
				<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/minwon.jsp"/>
			</c:when>
			<c:otherwise>
				<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/basic.jsp"/>
			</c:otherwise>
		</c:choose>
		<script type="text/javascript">
			yh.formId = "viewForm";
			yh.isNew = eval("${isNew}");
		</script>
		<script type="text/javascript" src="/common/js/commonProcess.js"></script>
	</c:if>
