<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 게시판 목록 상세 (블로그)
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

<table class="tableSt_write">
	<caption>게시글 상세</caption>
	<colgroup>
		<col span="1" style="width:10%;"/>
		<col span="1"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
		<col span="1" style="width:10%;"/>
		<col span="1" style="width:20%;"/>
	</colgroup>
	<tbody>
		<tr>
			<th scope="col" colspan="6" class="title">
				${bbsMngView.bTitle}
			</th>
		</tr>
		<tr>
			<th scope="row">작성일</th>
			<td><fmt:formatDate value="${bbsMngView.createDate}" pattern="yyyy-MM-dd"/></td>
			<th scope="row">작성자</th>
			<c:choose>
				<c:when test="${empty bbsMngView.bPrivatecode && not empty bbsMngView.bWriteDeptNm}">
					<td>[${bbsMngView.bWriteDeptNm}] ${bbsMngView.bWrite}<c:if test="${not empty bbsMngView.bWriteDeptTel}">(${bbsMngView.bWriteDeptTel})</c:if></td>
				</c:when>
				<c:otherwise>
					<td>${bbsMngView.rltdDeptNm}</td>
				</c:otherwise>
			</c:choose>
			<th scope="row">조회수</th>
			<td>${bbsMngView.bCnt}</td>
		</tr>
		<tr>
			<td colspan="6" class="cont_box">
				<c:if test="${showThumb && bbsMngView.fileCnt > 0}">
					<c:import url="/cmm/fms/selectFileViewer.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${bbsMngView.attachId}"/>
						<c:param name="mode" value="origin"/>
						<c:param name="insertBr" value="${true}"/>
					</c:import>
				</c:if>
				<div class="mL20">
					<c:set var="content" value="${fn:replace(bbsMngView.bContent, '  ', '&nbsp;&nbsp;')}"/>
					${yh:nl2br(content)}
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">첨부 파일</th>
			<td colspan="5" class="taL">
				<c:set var="hasFile" value="${not empty bbsMngView.fileCnt && bbsMngView.fileCnt != '0'}"/>
				<c:if test="${hasFile}">
					<c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
						<c:param name="param_atchFileId" value="${bbsMngView.attachId}"/>
						<c:param name="convertYn" value="Y"/>
					</c:import>
				</c:if>
				<c:if test="${!hasFile}">파일이 없습니다.</c:if>
			</td>
		</tr>
	</tbody>
</table>

<c:set var="p_formId" value="viewForm" scope="request"/>
<c:set var="p_article" value="${bbsMngView}" scope="request"/>
<jsp:include page="/sys/jsp/bbs/bbsMng/component/list/btn.jsp"/>
