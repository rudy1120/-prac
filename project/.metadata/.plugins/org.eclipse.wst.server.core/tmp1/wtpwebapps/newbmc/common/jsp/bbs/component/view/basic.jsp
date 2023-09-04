<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (일반)
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

<div class="bod_wrap">
	<div class="bod_view">
		<h4>${bbsView.bTitle}</h4>

		<div class="view_info">
			<ul class="" style="position:absolute;">
				<li class="view_write">
					<span>작성자</span> :
					<c:choose>
						<c:when test="${empty bbsView.bPrivatecode && not empty bbsView.bWriteDeptNm}">
							<%-- [${bbsView.bWriteDeptNm}] --%> ${bbsView.rltdDeptNm}<c:if test="${not empty bbsView.bWriteDeptTel}">(${bbsView.bWriteDeptTel})</c:if>
						</c:when>
						<c:otherwise>
							${bbsView.rltdDeptNm}
						</c:otherwise>
					</c:choose>
				</li>
				<c:if test="${!empty bbsView.bEnquiry}">
				<li class="view_date">
					<span>문의처</span> :
						${bbsView.bEnquiry}
				</li>
				</c:if>
			</ul>
			<ul class="clFix">
				<li class="view_date"><span>등록일</span> : 
				<c:if test="${empty bbsView.bTermSdate}">
				<fmt:formatDate value="${bbsView.createDate}" pattern="yyyy-MM-dd"/>
			</c:if>
			<c:if test="${not empty bbsView.bTermSdate}">
				<c:if test="${fn:length(bbsView.bTermSdate) >= 10}">
					${fn:substring(bbsView.bTermSdate, 0 ,10)}
				</c:if>
			</c:if>
				</li>
				<li class="view_hit"><span>조회</span> : ${bbsView.bCnt}</li>
			</ul>
		</div>

		<div class="view_cont">
			<%-- <c:if test="${showThumb && bbsView.fileCnt > 0}">
				<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
					<c:param name="param_atchFileId" value="${bbsView.attachId}"/>
					<c:param name="mode" value="origin"/>
					<c:param name="insertBr" value="${true}"/>
				</c:import>
			</c:if> --%>
			<c:if test="${bbsView.bIdx <= 703796}">
				<div style="text-align:center" class="mT10">
			</c:if>
			<c:if test="${bbsView.bIdx > 703796}">
				<div class="mT10">
			</c:if>
				<c:set var="content" value="${fn:replace(bbsView.bContent, '  ', '&nbsp;&nbsp;')}"/>
				${content}
		</div>
		</div>
		<dl class="view_file clFix">
			<dt><span>첨부 파일</span></dt>
			<dd>
				<span style="font-size: 17px;">※미리보기 시 실제문서와 형식상 차이가 있을수 있습니다.</span>
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
		<%-- ##### 공공누리 이용 안내 ##### --%>
		<c:set var="p_display_nuri" value="${empty bbsView.bPrivatecode && not empty bbsView.bNuriType && bbsView.bNuriType != '0'}" scope="request"/>
		<c:set var="p_nuriType" value="${bbsView.bNuriType}" scope="request"/>
		<jsp:include page="/common/jsp/component/articleUsePolicy.jsp"/>
	</div>

	<c:set var="p_formId" value="viewForm" scope="request"/>
	<c:set var="p_article" value="${bbsView}" scope="request"/>
	<jsp:include page="/common/jsp/bbs/component/list/btn.jsp"/>
</div>


