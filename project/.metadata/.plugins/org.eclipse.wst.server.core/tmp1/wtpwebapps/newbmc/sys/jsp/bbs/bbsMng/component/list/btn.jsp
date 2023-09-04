<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp"%>
<%
	/**
	 * 게시판 버튼
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.16		J.Ryeon Lee		컴포넌트화, script 분리
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.16
	 */
%>
<c:if test="${not empty bbsConfigVO && not empty p_article}">
	<c:set var="deletedComplaintParent" value="${isMinwon && p_article.bLevel > 0 && p_parent.isDel == 'Y'}"/>
	<c:set var="isRepliedComplaint" value="${isMinwon && p_article.bLevel == 0 && p_article.replyCnt != 0}"/>
	<c:if test="${!isRepliedComplaint}">
		<div class="btn_boxR">
			<%-- ==================== [답글] ==================== --%>
			<c:if test="${p_article.isDel == 'N'}">
				<c:choose>
					<c:when test="${isMinwon}"> <%-- 민원 게시판 --%>
						<c:if test="${p_article.bNotice != 'Y' && p_article.bLevel == 0 && p_article.replyCnt == 0}" >
							<a href="#" onclick="goTo.reply('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_cyan">글답변</a>
						</c:if>
					</c:when>
					<c:when test="${isBasic}"> <%-- 일반 게시판 --%>
						<c:if test="${bbsConfigVO.ptReplyYn == 'Y' && p_article.bNotice != 'Y'}" >
							<a href="#" onclick="goTo.reply('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_cyan">답글</a>
						</c:if>
					</c:when>
					<c:otherwise> <%-- 다른 게시판 --%>

					</c:otherwise>
				</c:choose>
			</c:if>
			<%-- ==================== [답글] 외 ==================== --%>
			<c:if test="${!deletedComplaintParent}">
				<%-- [수정] [삭제] [복원] --%>
				<c:choose>
					<c:when test="${p_article.isDel == 'N'}">
						<c:choose>
							<c:when test="${p_article.bLevel == 0}">
								<a href="#" onclick="goTo.modify('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_cyan">수정</a>
							</c:when>
							<c:otherwise>
								<a href="#" onclick="goTo.reply_modify('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_cyan">수정</a>
							</c:otherwise>
						</c:choose>
						<a href="#" onclick="goTo.delet('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_dblue">삭제</a>
					</c:when>
					<c:otherwise>
						<a href="#" onclick="goTo.restore('${p_formId}', '${p_article.bIdx}'); return false;" class="btn_dblue">복원</a>
					</c:otherwise>
				</c:choose>
				<%-- [게시물 이동]: 외부관리자는 이용 불가 --%>
				<c:if test="${not empty ADMIN_LOGIN_INFO && p_article.isDel == 'N' && p_article.bLevel == 0}">
					<a href="#" onclick="goTo.move('${p_formId}', '${p_article.bIdx}', '${p_article.bNotice}'); return false;" class="btn_dblue">게시글이동</a>
				</c:if>
				<%-- [목록] --%>
				<a href="#" onclick="goTo.list('${p_formId}'); return false;" class="btn_dblue">목록</a>
			</c:if>
		</div>
	</c:if>
</c:if>
<script type="text/javascript">
	yh.ptIdx = "${bbsConfigVO.ptIdx}";
	yh.ptCategoryYn = "${bbsConfigVO.ptCategoryYn}";
	yh.mId = "${menuVO.mId}";
	yh.siteCodeFull = "${siteCode}";
</script>
<script type="text/javascript" src="/sys/js/bbs/pageMove.js"></script>
