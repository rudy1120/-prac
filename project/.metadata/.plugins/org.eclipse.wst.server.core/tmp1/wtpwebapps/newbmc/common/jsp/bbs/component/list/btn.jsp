<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
// 2023.07.19 모든 게시판의 다음글, 이전글 버튼 막기. 활성화시 아래의 a 태그 사용하면 됨
// <a href="#" onclick="goToPost('${bbsView.preIdx}','${bbsView.ptIdx}','${menuVO.mId}','pre');" title="이전 게시물 페이지로 이동">이전 글</a>
// <a href="#" onclick="goToPost('${bbsView.nextIdx}','${bbsView.ptIdx}','${menuVO.mId}','next');" title="다음 게시물 페이지로 이동">다음 글</a>
%>

<c:set var="isReplable" value="${bbsConfigVO.ptReplyYn == 'Y'}" />
<c:set var="isNotAnswered" value="${p_article.bLevel == 0 && p_article.replyCnt == 0}" />
<c:set var="isAnswerable" value="${isNotAnswered && fn:substring(bbsConfigVO.ptLevelReply, 1, 2) == 'Y'}" />

<div class="bod_foot">
	<div class="btn_wrap taR mT20">
		<c:if test="${p_article.bNotice == 'N'}">
			<c:if test="${isBasic && isReplable}"> <%-- 일반게시판 --%>
				<a href="#" onclick="goTo.reply('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 답글 작성하기">답글</a>
			</c:if>

			<c:if test="${isMinwon && isAnswerable}"> <%-- 민원형게시판 --%>
				<a href="#" onclick="goTo.reply('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 답변 작성하기">답변</a>
			</c:if>

			<c:if test="${allowedWriting}">
				<c:if test="${empty user}">
					<c:if test="${!isMinwon || isNotAnswered}">
						<a href="#" onclick="goTo.secured_view('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 수정용 비밀번호 입력 페이지로 이동">수정</a>
					</c:if>
					<a href="#" class="del" onclick="goTo.secured_view('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 삭제용 비밀번호 입력 페이지로 이동">삭제</a>
				</c:if>
				<c:if test="${not empty user && user.privatecode == p_article.bPrivatecode}">
					<c:if test="${!isMinwon || isNotAnswered}">
						<a href="#" onclick="goTo.modify('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 수정용 비밀번호 입력 페이지로 이동">수정</a>
					</c:if>
					<a href="#" class="del" onclick="goTo.delet('${p_formId}', '${p_article.bIdx}'); return false;" title="게시글 삭제용 비밀번호 입력 페이지로 이동">삭제</a>
				</c:if>
			</c:if>
		</c:if>

		<%-- 목록 --%>
		<c:if test="${isBasic || p_article.bLevel == 0}">
			<a href="#" onclick="document.getElementById('list').submit(); return false;" title="${activeMenu.menuName} 게시글 목록 페이지로 이동">목록</a>
		</c:if>
	</div>
</div>

<script type="text/javascript">
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.ptCategoryYn = "${bbsConfigVO.ptCategoryYn}";
	yh.mId = "${menuVO.mId}";
	yh.siteCodeFull = "${siteCodeFull}";
</script>
<script type="text/javascript" src="/common/js/bbs/pageMove.js"></script>
