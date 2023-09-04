<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (동영상)
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
<script>
if (document.location.protocol == 'http:') {
    document.location.href = document.location.href.replace('http:', 'https:');
}
</script>
<div class="bod_wrap">
	<div class="bod_view">
		<h4>${bbsView.bTitle}</h4>
		<div class="view_info">
			<ul>
				<li class="view_write">
					<span>작성자</span> :
					<c:choose>
						<c:when test="${empty bbsView.bPrivatecode && not empty bbsView.bWriteDeptNm}">
							<%-- [${bbsView.bWriteDeptNm}] --%> ${bbsView.bWrite}<c:if test="${not empty bbsView.bWriteDeptTel}">(${bbsView.bWriteDeptTel})</c:if>
						</c:when>
						<c:otherwise>
							${bbsView.rltdDeptNm}
						</c:otherwise>
					</c:choose>
				</li>
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
			<div id="movie">
				<c:forEach var="movieData" items="${movieDatas}">
					<video poster="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${movieData.streFileNm}.jpg" width="640" height="360" controls="controls">
						<c:choose>
							<c:when test="${fn:indexOf(movieData.streFileNm, '.') > -1 }">
								<source src="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${fn:substring(movieData.streFileNm, 0, fn:indexOf(movieData.streFileNm, '.') ) }.mp4" type="video/mp4" />
							</c:when>
							<c:otherwise>
								<source src="/video/${fn:replace(movieData.fileStreCours, '/home/webdata/egov_uploadFile/BBS/', '')}${movieData.streFileNm}.mp4" type="video/mp4" />
							</c:otherwise>
						</c:choose>
					</video>
				</c:forEach>
			</div>
			<div class="mCont">
				<c:set var="content" value="${fn:replace(bbsView.bContent, '  ', '&nbsp;&nbsp;')}"/>
				${yh:nl2br(content)}
			</div>
		</div>

		<c:if test="${not empty bbsView.attachId}">
			<dl class="view_file">
				<dt><span>첨부 파일</span></dt>
				<dd>
					<c:set var="hasFile" value="${not empty bbsView.fileCnt && bbsView.fileCnt != '0'}"/>
					<c:if test="${hasFile}">
						<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
							<c:param name="param_atchFileId" value="${bbsView.attachId}"/>
						</c:import>
					</c:if>
					<c:if test="${!hasFile}">파일이 없습니다.</c:if>
				</dd>
			</dl>
		</c:if>
		<%-- ##### 공공누리 이용 안내 ##### --%>
		<c:set var="p_display_nuri" value="${empty bbsView.bPrivatecode && not empty bbsView.bNuriType && bbsView.bNuriType != '0'}" scope="request"/>
		<c:set var="p_nuriType" value="${bbsView.bNuriType}" scope="request"/>
		<jsp:include page="/common/jsp/component/articleUsePolicy.jsp"/>
	</div>

	<c:set var="p_formId" value="viewForm" scope="request"/>
	<c:set var="p_article" value="${bbsView}" scope="request"/>
	<jsp:include page="/common/jsp/bbs/component/list/btn.jsp"/>
</div>
