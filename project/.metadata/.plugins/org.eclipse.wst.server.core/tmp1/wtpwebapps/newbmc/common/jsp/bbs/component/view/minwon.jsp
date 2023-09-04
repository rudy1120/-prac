<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (민원)
	 *
	 * 수정일			수정자			수정내용
	 * -------------	------------	-----------------------------
	 * 2017.05.15		J.Ryeon Lee		컴포넌트화
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.05.15
	 */
%>

<%-- ============================== 민원글 ============================== --%>
<p class="tip red">※ 답변이 달린 후에는 민원글을 수정하실 수 없습니다.</p>
<div class="bod_wrap">
	<div class="bod_view">
		<h4>${bbsView.bTitle}</h4>
		<div class="view_info">
			<ul>
				<li class="view_write"><span>작성자</span> : ${bbsView.bWrite}</li>
				<li class="view_date"><span>등록일</span> : <fmt:formatDate value="${bbsView.createDate}"  pattern="yyyy-MM-dd"/></li>
				<li class="view_hit"><span>조회</span> : ${bbsView.bCnt}</li>
			</ul>
		</div>

		<div class="view_cont">
			<c:if test="${showThumb && bbsView.fileCnt > 0}">
				<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${bbsView.attachId}"/>
					<c:param name="mode" value="origin"/>
					<c:param name="insertBr" value="${true}"/>
				</c:import>
			</c:if>
			<div class="mT10">
				<c:set var="content" value="${fn:replace(bbsView.bContent, '  ', '&nbsp;&nbsp;')}"/>
				${yh:nl2br(content)}
			</div>
		</div>
		<dl class="view_file">
			<dt><span>첨부 파일</span></dt>
			<dd>
				<c:set var="hasFile" value="${not empty bbsView.fileCnt && bbsView.fileCnt != '0'}"/>
				<c:if test="${hasFile}">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${bbsView.attachId}"/>
						<c:param name="convertYn" value="Y"/>
					</c:import>
				</c:if>
				<c:if test="${!hasFile}">파일이 없습니다.</c:if>
			</dd>
		</dl>
	</div>

	<c:set var="p_formId" value="viewForm" scope="request"/>
	<c:set var="p_article" value="${bbsView}" scope="request"/>
	<jsp:include page="/common/jsp/bbs/component/list/btn.jsp"/>
</div>

<%-- ============================== 답변 ============================== --%>
<c:if test="${not empty replyList}">
	<c:set var="answer" value="${replyList[0]}"/>
	<div class="bod_wrap">
		<div class="bod_view bod_reply">
			<h4>[답변] ${answer.bTitle}</h4>
			<div class="view_info">
				<ul>
					<li class="view_write">
						<span>작성자</span> :
						<c:choose>
							<c:when test="${empty bbsView.bPrivatecode && not empty bbsView.bWriteDeptNm}">
								[${bbsView.bWriteDeptNm}] ${bbsView.bWrite}<c:if test="${not empty bbsView.bWriteDeptTel}">(${bbsView.bWriteDeptTel})</c:if>
							</c:when>
							<c:otherwise>
								${bbsView.bWrite}
							</c:otherwise>
						</c:choose>
					</li>
					<li class="view_date"><span>등록일</span> : <fmt:formatDate value="${answer.createDate}"  pattern="yyyy-MM-dd"/></li>
				</ul>
			</div>
			<div class="view_cont">
				<c:if test="${showThumb}">
					<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${answer.attachId}"/>
						<c:param name="mode" value="origin"/>
						<c:param name="insertBr" value="${true}"/>
					</c:import>
					<br />
				</c:if>
				<c:set var="r_content" value="${fn:replace(answer.bContent, ' ', '&nbsp;')}"/>
				${yh:nl2br(r_content)}
			</div>
			<dl class="view_file">
				<dt><span>첨부 파일</span></dt>
				<dd>
					<c:set var="hasFile" value="${not empty answer.fileCnt && answer.fileCnt != '0'}"/>
					<c:if test="${hasFile}">
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${answer.attachId}"/>
							<c:param name="convertYn" value="Y"/>
						</c:import>
					</c:if>
					<c:if test="${!hasFile}">파일이 없습니다.</c:if>
				</dd>
			</dl>
		</div>

		<c:set var="p_formId" value="viewForm" scope="request"/>
		<c:set var="p_article" value="${answer}" scope="request"/>
		<c:set var="p_parent" value="${bbsMngView}" scope="request"/>
		<jsp:include page="/common/jsp/bbs/component/list/btn.jsp"/>
	</div>
</c:if>
