<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ include file="/common/jsp/bbs/component/variables.jsp" %>

<%
	/**
	 * 게시판 목록 상세
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2014.11.14		김혜민			최초 생성 및 코드 작성
	 * 2015.07.29		J.Ryeon Lee		공지글 및 삭제된 글 답글 기능 숨김, 공지글은 수정/삭제만 가능
	 * 2015.08.03		J.Ryeon Lee		목록 출력 코드 외부 파일화
	 * 2015.08.13		김현호			퍼블리싱 작업
	 * 2015.08.20		J.Ryeon Lee		스크립틀릿 코드 jstl 대체, 미사용 자바스크립트 함수 삭제, 공통 코드 외부 파일화, 스크립트 파일 공통화
	 * 2017.05.15		J.Ryeon Lee		script 분리, 타입별 뷰 통합
	 *
	 *
	 * @author 김혜민
	 * @since 2014.10.15
	 */
%>
<div class="gap40"></div>
<div id="contents">
	<div class="content">
	
<c:set var="formId" value="viewForm" />
<form:form commandName="searchVO" name="list" id="list" action="/${siteCodeFull}/bbs/list.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" method="POST">
	<form:hidden path="page" />
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />
	<form:hidden path="searchCategory" />
	<form:hidden path="searchDept" />
</form:form>
<%--
20.09.10 웹호환성으로 주석처리
<form:form commandName="searchVO" name="viewForm" id="viewForm" action="/${siteCodeFull}/bbs/view.do?ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" methodParam="post">
	<form:hidden path="page" />
	<form:hidden path="bIdx" />
	<form:hidden path="searchTxt" />
	<form:hidden path="searchType" />
	<form:hidden path="searchCategory" />
	<form:hidden path="searchDept" />
	<input type="hidden" name="bbsMode"/>
	<input type="hidden" name="cancelUrl" id="cancelUrl" value="/${siteCodeFull}/bbs/view.do?bIdx=${bbsView.bIdx}&ptIdx=${bbsConfigVO.ptIdx}&mId=${menuVO.mId}" />
</form:form> --%>

	<%-- ============================== VIEW ============================== --%>
	<h4 class="blind">${activeMenu.menuName} 게시판 내용 보기</h4>
	<c:choose>
		<c:when test="${isPhoto}">
			<jsp:include page="/common/jsp/bbs/component/view/photo.jsp"/>
		</c:when>
		<c:when test="${isMovie}">
			<jsp:include page="/common/jsp/bbs/component/view/movie.jsp" />
		</c:when>
		<c:when test="${isBlog}">
			<jsp:include page="/common/jsp/bbs/component/view/blog.jsp" />
		</c:when>
		<c:when test="${isMinwon}">
			<jsp:include page="/common/jsp/bbs/component/view/minwon.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="/common/jsp/bbs/component/view/basic.jsp" />
		</c:otherwise>
	</c:choose>

	<c:if test="${bbsConfigVO.ptCommentYn == 'Y'}"> <%-- 코멘트 --%>
		<jsp:include page="/common/jsp/bbs/component/list/comment.jsp" />
	</c:if>

	<%-- ============================== BOARD LIST ============================== --%>
	<c:if test="${fn:substring(bbsConfigVO.ptOutFields, 0, 1) == 'Y'}">
		<c:set var="p_formId" value="${formId}" scope="request" />
		<c:set var="p_fromView" value="${true}" scope="request" />
		<c:choose>
			<c:when test="${isPhoto}">
				<jsp:include page="/common/jsp/bbs/component/list/photo.jsp"/>
			</c:when>
			<c:when test="${isMovie}">
				<jsp:include page="/common/jsp/bbs/component/list/movie.jsp"/>
			</c:when>
			<c:when test="${isBlog}">
				<jsp:include page="/common/jsp/bbs/component/list/blog.jsp"/>
			</c:when>
			<c:when test="${isMinwon}">
				<jsp:include page="/common/jsp/bbs/component/list/minwon.jsp"/>
			</c:when>
			<c:when test="${isFAQ}">
				<jsp:include page="/common/jsp/bbs/component/list/faq.jsp"/>
			</c:when>
			<c:otherwise>
				<jsp:include page="/common/jsp/bbs/component/list/basic.jsp"/>
			</c:otherwise>
		</c:choose>
		<script type="text/javascript">
			yh.formId = "viewForm";
			yh.isNew = eval("viewForm");
		</script>
		<script type="text/javascript" src="/common/js/commonProcess.js"></script>
	</c:if>
</div>
</div>
<script>
	$(function (){
		$('.mT10 table').wrap('<div class="mobileTable" />');
		$('.mT10 ul').addClass('list_style');
		$('.mT10 ol').addClass('list_style');
		$('.mT10 li').addClass('list_style');
	});
	
	function goToPost(bIdx, ptIdx, mId, flag){
		
		if(bIdx == ''){
			if(flag == 'pre'){
				alert("이동할 이전글이 없습니다.");
				return;
			}else if(flag == 'next'){
				alert("이동할 다음글이 없습니다.");
				return;
			}
		}
		window.location.href = "/bmc/bbs/view.do?bIdx=" + bIdx + "&ptIdx="+ ptIdx +"&mId=" + mId
		/* /bmc/bbs/view.do?bIdx=${bbsView.preIdx}&ptIdx=${bbsView.ptIdx}&mId=${menuVO.mId}" */
	}
</script>
